package com.example.app.controller;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.app.model.User;
import com.example.app.service.LoginService;

/**
 * ログイン画面の表示とログイン処理を担当するController
 */
@Controller
@RequestMapping("/login")
public class LoginController {

    private static final Logger LOGGER =
            Logger.getLogger(LoginController.class.getName());

    private final LoginService loginService;

    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    /**
     * ログイン画面を表示する
     */
    @RequestMapping(method = RequestMethod.GET)
    public String showLogin() {
        return "Login";
    }

    /**
     * ログイン認証を実行する
     */
    @RequestMapping(method = RequestMethod.POST)
    public String login(
            @RequestParam("username") String userId,
            @RequestParam("password") String password,
            HttpServletRequest request,
            Model model) {

        try {
            User user = loginService.authenticate(userId, password);

            if (user == null) {
                model.addAttribute(
                        "errorMessage",
                        "ユーザーIDまたはパスワードが正しくありません。"
                );
                return "Login";
            }

            HttpSession oldSession = request.getSession(false);

            if (oldSession != null) {
                oldSession.invalidate();
            }

            HttpSession session = request.getSession(true);

            session.setAttribute("loginUserId", user.getUserId());
            session.setAttribute("displayName", user.getDisplayName());
            session.setAttribute("role", user.getRole());

            return "redirect:/dashboard";

        } catch (SQLException e) {

            LOGGER.log(
                    Level.SEVERE,
                    "ログイン認証中にデータベースエラーが発生しました。",
                    e
            );

            model.addAttribute(
                    "errorMessage",
                    "現在ログインできません。時間をおいて再度お試しください。"
            );

            return "Login";
        }
    }
}