package cn.stt.admin.back.controller;

import cn.stt.admin.back.dao.AppInfoDao;
import cn.stt.admin.back.po.AppInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author shitongtong
 * <p>
 * Created by shitongtong on 2017/7/31.
 */
@Controller
@RequestMapping("/appVersion")
public class AppVersionController {

    @Autowired
    private AppInfoDao appInfoDao;

    @RequestMapping("/list")
    public String list(Model model, HttpServletRequest request) {
        List<AppInfo> list = appInfoDao.findAll();
        model.addAttribute("pageParam", list);
        return "/appversion/list";
    }

    @RequestMapping("/edit")
    public String edit(Model model, Long id, HttpServletRequest request) {
        AppInfo appInfo = appInfoDao.getOne(id);
        model.addAttribute("vo", appInfo);
        return "/appversion/edit";
    }

    @RequestMapping("/update")
    public String update(Model model, Long id, String url, String version, String deviceType) {
        appInfoDao.update(url, version, deviceType, id);
        return "redirect:list";
    }
}
