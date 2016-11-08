package cn.stt.magnetic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLDecoder;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2016-11-08.
 */
@Controller
@RequestMapping("analyzemagnetic")
public class AnalyzeController {

    private String PHPSESSID;
    private String skey = "@7oRsN1Q65";
    private String g_tk = "919094206";
    private String uin = "o0742740116";//用户qq号 旋风会员账号2047331293密800444777

    @RequestMapping("analyze")
    public void analyze(HttpServletRequest request, HttpServletResponse response){
        String hash = request.getParameter("hash");
        String strindex = request.getParameter("index");
        /*for (int page = 9717; page < 10000; page++) {
            System.out.println("正在处理第+" + (page + 1) + "页");
            Statement stmt1;
            try {
                stmt1 = cnn.createStatement();
                String querysql = "select torrent_hash,hash from iiplayer_sha limit " + (page + 1) * 100 + "," + 100;
                ResultSet rs = stmt1.executeQuery(querysql);
                int count = 1;
                while (rs.next()) {
                    System.out.println("正在处理第" + (count + page * 100) + "记录");
                    String sha = rs.getString(2);
                    String torrent_hash = rs.getString(1);
                    //分差torrent_hash
                    String[] sourceStrArray = torrent_hash.split("/");
                    hash = sourceStrArray[0];
                    strindex = sourceStrArray[1];

                    int ciliindex = -1;
                    if (strindex != null) {
                        ciliindex = Integer.valueOf(strindex);
                    }

                    String sha1 = null;
                    String ftd = null;
                    try {
                        Statement stmt11;
                        stmt11 = conn1.createStatement();
                        String querysql1 = "select url,hash from iiplayer_sha where torrent_hash='" + hash + "' and torrent_index=" + strindex;
                        ResultSet rs1 = stmt11.executeQuery(querysql1);
                        if (rs1.next()) {
                            sha1 = rs1.getString(2);
                            ftd = rs1.getString(1);
                        }

                    } catch (SQLException e1) {
                        System.out.println("数据库查询异常！");
                        e1.printStackTrace();
                    }

                    count++;

                    if (sha1 != null) {
                        System.out.println("已经存在");
                        continue;
                    }


                    hash = hash.replace("magnet:?xt=urn:btih:", "");
                    if (hash != null) {
                        String url = "http://i.vod.xunlei.com/req_subBT/info_hash/" + hash + "/req_num/1000/req_offset/0";
                        String xunleiUrl = HttpUtil.doGet(url);
                        JSONObject magnetJson;
                        String record_num;
                        try {
                            magnetJson = JSONObject.fromObject(xunleiUrl).getJSONObject("resp");
                            record_num = magnetJson.getString("record_num");
                        } catch (JSONException e1) {
                            continue;
                        }


                        if (!record_num.isEmpty()) {
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
                                            continue;
                                        }
                                    }
                                    String fileName = URLDecoder.decode(filelist.getString("name"), "UTF-8");
                                    fileName = response.encodeURL(new String(fileName.getBytes("UTF-8"), "ISO-8859-1"));
                                    String file_hash = sha;
                                    String getxuanfengurl = "http://fenxiang.qq.com/upload/index.php/share/handler_c/getComUrl";
                                    String getxuanfengParm = "";
                                    getxuanfengParm = getxuanfengParm + "filehash=" + file_hash + "&filename=m.mkv";
                                    String get_http_url = "";
                                    Map<String, Object> params = new HashMap<String, Object>();
                                    params.put("filehash", file_hash);
                                    params.put("filename", "m.mkv");
                                    get_http_url = HttpUtil.doPost(getxuanfengurl, params);

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
                                        System.out.println(hash + "_" + index + "寻找真实地址出错" + get_http_url);
                                        continue;
                                    }

                                    if (com_cookie.compareTo("00000000") == 0) {
                                        System.out.println("FTN5K=00000000" + hash + "_" + index + get_http_url);
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
                                    long sizes = Getlens(code, com_cookie);
                                    if (sizes == 0) {
                                        System.out.println("警告错误：1" + hash + "_" + index + "_" + sizes + "_" + code);
                                        continue;
                                    }


                                    avlist.put("url", code);
                                    avlist.put("cookie", "FTN5K=" + com_cookie);
                                    avlist.put("name", fileName);
                                    listArray.add(avlist);

                                    try {
                                        int ins = code.indexOf("ftn_handler/");
                                        code = code.substring(0, ins);
                                        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
                                        String insertsql;
                                        insertsql = "insert into iiplayer_sha(torrent_hash,torrent_index,hash,url,add_time,name,size) value('";
                                        insertsql = insertsql + hash + "',";
                                        insertsql = insertsql + index + ",'";
                                        insertsql = insertsql + file_hash + "','";
                                        insertsql = insertsql + code + "','";
                                        insertsql = insertsql + df.format(new Date()) + "','";
                                        insertsql = insertsql + URLDecoder.decode(filelist.getString("name"), "UTF-8") + "','";
                                        insertsql = insertsql + file_size + "')";
                                        PreparedStatement statement = conn1.prepareStatement(insertsql);
                                        statement.executeUpdate();

                                        System.out.println(df.format(new Date()) + "_" + "插入数据库" + hash + "_" + index);
                                    } catch (SQLException e) {
                                        System.out.println("插入数据库时出错：");
                                        e.printStackTrace();
                                    }

                                }

                            }

                        }
                    }
                }
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }*/
    }
}
