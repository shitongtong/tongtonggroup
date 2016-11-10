package cn.stt.magnetic.controller;

import cn.stt.magnetic.po.Magnetic;
import cn.stt.magnetic.po.VideoInfo;
import cn.stt.magnetic.service.MagneticService;
import cn.stt.magnetic.service.VideoInfoService;
import cn.stt.magnetic.utils.HttpUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2016-11-08.
 */
@Controller
@RequestMapping("/analyzemagnetic")
public class AnalyzeController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private String PHPSESSID;
    private String skey = "@7oRsN1Q65";
    private String g_tk = "919094206";
    private String uin = "o0742740116";//用户qq号 旋风会员账号2047331293密800444777

    @Autowired
    private MagneticService magneticService;
    @Autowired
    private VideoInfoService videoInfoService;

    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public String index(){
        System.out.println("aaa");
        return "index";
    }

    @RequestMapping("/analyze")
    public void analyze(HttpServletRequest request, HttpServletResponse response
                        , @RequestParam(value = "pageNo",defaultValue = "1") int pageNo
                        , @RequestParam(value = "pageSize",defaultValue = "100") int pageSize) {
//        String hash = request.getParameter("hash");
//        String strindex = request.getParameter("index");

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("status", 0);
        int count = 0;
        for (pageNo = 1; pageNo <= 10000; pageNo++) {
            ++count;
//            System.out.println("正在处理第" + (pageNo + 1) + "页");
//            Page page = new Page(pageNo, pageSize);
//            page = magneticService.selectByParams(paramMap, page);
//            List<Magnetic> magneticList = page.getList();
            paramMap.put("offset", 0);
            paramMap.put("limit", 100);
            List<Magnetic> magneticList = magneticService.selectListByParams(paramMap);
            if (magneticList == null || magneticList.size() == 0){
                logger.info("解析完成,程序结束！");
                System.exit(0);
            }
            for (Magnetic magnetic : magneticList) {
                String torrent_hash = magnetic.getHash();   //种子（磁力+索引）
                String sha = magnetic.getSha1();   //密钥
                //分差torrent_hash
                String[] sourceStrArray = torrent_hash.split("/");
                String hash = sourceStrArray[0];
                String strindex = sourceStrArray[1];
                int ciliindex = -1;
                if (strindex != null) {
                    ciliindex = Integer.valueOf(strindex);
                }

                hash = hash.replace("magnet:?xt=urn:btih:", "");
                if (hash != null) {
                    String url = "http://i.vod.xunlei.com/req_subBT/info_hash/" + hash + "/req_num/1000/req_offset/0";
                    String xunleiUrl = HttpUtil.doGet(url);
                    logger.info("xunleiUrl={}",xunleiUrl);
                    JSONObject magnetJson = null;
                    String record_num = "";
                    try {
                        magnetJson = JSONObject.fromObject(xunleiUrl).getJSONObject("resp");
                        record_num = magnetJson.getString("record_num");

                        if (magnetJson != null && StringUtils.isNotEmpty(record_num)) {
                            int num = Integer.valueOf(record_num);
                            if (num > 0) {
                                JSONArray listArray = new JSONArray();
                                JSONArray array = magnetJson.getJSONArray("subfile_list");
                                JSONObject avlist = new JSONObject();
                                for (int i = 0; i < num; i++) {
                                    JSONObject filelist = (JSONObject) array.get(i);
                                    int index = filelist.getInt("index");
                                    long file_size = filelist.getLong("file_size");
                                    if (ciliindex != -1) {
                                        if (index != ciliindex) {
                                            magneticService.updateStatus(torrent_hash,sha,2);
                                            continue;
                                        }
                                    }
                                    if (file_size == 0) {
                                        magneticService.updateStatus(torrent_hash,sha,2);
                                        logger.info("警告错误：1" + hash + "_" + index + "_" + file_size + "_" + file_size);
                                        continue;
                                    }
                                    String fileName = URLDecoder.decode(filelist.getString("name"), "UTF-8");
                                    fileName = response.encodeURL(new String(fileName.getBytes("UTF-8"), "ISO-8859-1"));
                                    String file_hash = sha;
                                    String getxuanfengurl = "http://fenxiang.qq.com/upload/index.php/share/handler_c/getComUrl";
                                    Map<String, Object> params = new HashMap<String, Object>();
                                    params.put("filehash", file_hash);
                                    params.put("filename", "m.mkv");
                                    String get_http_url = HttpUtil.doPost(getxuanfengurl, params);
                                    logger.info("get_http_url={}",get_http_url);

                                    String com_cookie = null;
                                    String patCookie = "\"com_cookie\":\"(.*?)\"";
                                    Pattern com_cookiepattern = Pattern.compile(patCookie);
                                    Matcher com_cookiematcher = com_cookiepattern.matcher(get_http_url);
                                    StringBuffer com_cookiebuffer = new StringBuffer();
                                    while (com_cookiematcher.find()) {
                                        com_cookiebuffer.append(com_cookiematcher.group(1));
                                        com_cookie = com_cookiebuffer.toString();
                                    }
                                    Pattern pattern = Pattern.compile("com_url\":\"(.*?)/m.mkv");
                                    Matcher matcher = pattern.matcher(get_http_url);
                                    StringBuffer buffer = new StringBuffer();
                                    String code = null;
                                    while (matcher.find()) {
                                        buffer.append(matcher.group(1));
                                        code = buffer.toString();
                                    }
                                    if (code == null || code.isEmpty()) {
                                        magneticService.updateStatus(torrent_hash,sha,2);
                                        logger.error(hash + "_" + index + "寻找真实地址出错" + get_http_url);
                                        continue;
                                    }

                                    if (com_cookie.compareTo("00000000") == 0) {
                                        magneticService.updateStatus(torrent_hash,sha,2);
                                        logger.error("FTN5K=00000000" + hash + "_" + index + get_http_url);
                                        continue;
                                    }
                                    code = code.replace("xflx.store.cd.qq.com:443", "xfcd.ctfs.ftn.qq.com");
                                    code = code.replace("xflxsrc.store.qq.com:443", "xfxa.ctfs.ftn.qq.com");
                                    code = code.replace("xflx.cd.ftn.qq.com:80", "cd.ctfs.ftn.qq.com");
                                    code = code.replace("xflx.store.sh.qq.com:443", "xfsh.ctfs.ftn.qq.com");
                                    code = code.replace("xflx.sh.ftn.qq.com:80", "sh.ctfs.ftn.qq.com");
                                    code = code.replace("xflx.xabtfs.ftn.qq.com:80", "xa.btfs.ftn.qq.com");
                                    code = code.replace("xflx.sz.ftn.qq.com:80", "sz.ctfs.ftn.qq.com");
                                    code = code.replace("xflx.hz.ftn.qq.com:80", "hz.ftn.qq.com");
                                    code = code.replace("xflx.tjctfs.ftn.qq.com:80", "tj.ctfs.ftn.qq.com");
                                    code = code.replace("xflx.shbtfs.ftn.qq.com:80", "sh.btfs.ftn.qq.com");
                                    code = code.replace("xflx.szmail.ftn.qq.com:80", "szmail.tfs.ftn.qq.com");
                                    code = code.replace("xflx.xa.ftn.qq.com:80", "xa.ctfs.ftn.qq.com");
                                    code = code.replace("xflx.xabtfs.ftn.qq.com:80", "xflx.xabtfs.ftn.qq.com");
                                    code = code.replace("xflx.cdbtfs.ftn.qq.com:80", "cd.btfs.ftn.qq.com");
                                    code = code.replace("xflx.szbtfs.ftn.qq.com:80", "sz.btfs.ftn.qq.com");
                                    code = code.replace("xflx.xatfs.ftn.qq.com:80", "xa.tfs.ftn.qq.com");
                                    code = code.replace("xflx.tjmail.ftn.qq.com:80", "tjmail.tfs.ftn.qq.com");
                                    code = code.replace("xflx.tjbtfs.ftn.qq.com:80", "tj.btfs.ftn.qq.com");
                                    code = code.replace("182.131.9.221:80", "xfcd.ctfs.ftn.qq.com");
                                    /*long sizes = FileUtil.getFileSize(code, com_cookie);
                                    if (sizes == 0) {
                                        System.out.println("警告错误：1" + hash + "_" + index + "_" + sizes + "_" + code);
                                        continue;
                                    }

                                    avlist.put("url", code);
                                    avlist.put("cookie", "FTN5K=" + com_cookie);
                                    avlist.put("name", fileName);
                                    listArray.add(avlist);

                                    int ins = code.indexOf("ftn_handler/");
                                    code = code.substring(0, ins);*/

                                    VideoInfo videoInfo = new VideoInfo();
                                    videoInfo.setTorrentHash(hash);
                                    videoInfo.setTorrentIndex(index);
                                    videoInfo.setHash(file_hash);
                                    videoInfo.setUrl(code+"/m.mkv");
                                    videoInfo.setCookie(com_cookie);
                                    String name = URLDecoder.decode(filelist.getString("name"), "UTF-8");
                                    videoInfo.setName(name);
                                    videoInfo.setSize(file_size+"");
                                    if (name.contains("【无效链接】")){
                                        videoInfo.setValid(0);
                                    }else{
                                        videoInfo.setValid(1);
                                    }

                                    int i1 = videoInfoService.addVideoInfo(videoInfo);
                                    if (i1 > 0){
                                        magneticService.updateStatus(torrent_hash,sha,1);
                                        logger.info("插入数据库" + hash + "_" + index);
                                    }
                                }
                            }
                        }
                    } catch (Exception e) {
                        logger.error(e.getMessage(),e);
                        logger.error("count=="+count);
                        System.exit(0);
                    }
                }
            }
            logger.info("count=="+count);
        }
    }

    @RequestMapping("/play")
    public void play(HttpServletRequest request, HttpServletResponse response){
        Cookie cookie = new Cookie("com_cookie","db0c0890");
        response.addCookie(cookie);

    }
}
