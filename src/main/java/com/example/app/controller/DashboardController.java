package com.example.app.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * ダッシュボード画面の表示を制御するController
 */
@Controller
public class DashboardController {

    /**
     * ダッシュボード画面を表示する
     */
    @RequestMapping(
            value = "/dashboard",
            method = RequestMethod.GET
    )
    public String showDashboard(
            HttpServletRequest request,
            Model model) {

        // 現在のセッションを取得する
        HttpSession session = request.getSession(false);

        // セッションが存在しない場合はログイン画面へ戻す
        if (session == null) {
            return "redirect:/login";
        }

        // ログインユーザーIDを取得する
        String loginUserId =
                (String) session.getAttribute("loginUserId");

        // ログイン情報が存在しない場合はログイン画面へ戻す
        if (loginUserId == null) {
            return "redirect:/login";
        }

        // セッションからログインユーザー情報を取得する
        String displayName =
                (String) session.getAttribute("displayName");

        String role =
                (String) session.getAttribute("role");

        // JSPに表示する情報を渡す
        model.addAttribute("loginUserId", loginUserId);
        model.addAttribute("displayName", displayName);
        model.addAttribute("role", role);

        // ダッシュボード画面を表示する
        return "Dashboard";
    }
}