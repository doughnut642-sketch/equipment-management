package com.example.app.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LogoutController {

    /**
     * ログアウト処理
     */
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public String logout(HttpSession session) {

        // セッションを破棄する
        session.invalidate();

        // ログイン画面へリダイレクト
        return "redirect:/login";
    }
}