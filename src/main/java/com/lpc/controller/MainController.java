package com.lpc.controller;

import com.lpc.pojo.SmbmsUser;
import com.lpc.service.SmbmsUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @User lpc/李金超
 * @ClassName LoginController  类名
 * @Author AUSUA  作者
 * @Date 2020/7/10 18:20  时间
 * @Version 1.0 版本
 */
@Controller
public class MainController {

    @Autowired
    private SmbmsUserService smbmsUserService;

    @Autowired
    private HttpSession session;

    @Autowired
    private HttpServletRequest request;

    /**
     * 登录
     * @return
     */
    @GetMapping("/login")
    public String login(){
        session.removeAttribute("userOnLogin");
        return "login";
    }

    /**
     * 登录时提交的表单
     * @param smbmsUser  存储的用户名和密码
     * @return
     */
    @PostMapping("/dologin")
    public String dologin(SmbmsUser smbmsUser){
        smbmsUser = smbmsUserService.login(smbmsUser);
        if(null!=smbmsUser){
            request.removeAttribute("error");
            session.setAttribute("userOnLogin",smbmsUser);
            return "frame";
        }else {
            session.setAttribute("error", "用户名或密码错误");
            session.removeAttribute("successSavePwd");
            return "redirect:/login";
        }
    }
}
