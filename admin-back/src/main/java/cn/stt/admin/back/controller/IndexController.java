package cn.stt.admin.back.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author shitongtong
 * <p>
 * Created by shitongtong on 2017/7/31.
 */
@Controller
@RequestMapping("/")
public class IndexController {

    @RequestMapping("/")
    public String indexPage(){
        return "index";
    }
}
