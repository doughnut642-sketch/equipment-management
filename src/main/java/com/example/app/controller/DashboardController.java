
package com.example.app.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.app.service.EquipmentService;

/**
 * ダッシュボード画面コントローラー
 *
 * ログインユーザー情報と装置情報を取得し、
 * Dashboard.jspに渡す。
 */
@Controller
public class DashboardController {

    // 装置情報サービス
    private final EquipmentService equipmentService;

    /**
     * コンストラクタ
     *
     * SpringのDIによってEquipmentServiceを受け取る。
     */
    @Autowired
    public DashboardController(EquipmentService equipmentService) {
        this.equipmentService = equipmentService;
    }

    /**
     * ダッシュボード画面表示
     *
     * @param request HTTPリクエスト
     * @param model 画面に渡すデータ
     * @return Dashboard.jsp
     */
    @RequestMapping(value = "/dashboard", method = RequestMethod.GET)
    public String showDashboard(
            HttpServletRequest request,
            Model model) {

        // ログインセッションを取得
        HttpSession session = request.getSession(false);

        // セッションが存在しない場合はログイン画面へ戻す
        if (session == null
                || session.getAttribute("loginUserId") == null) {

            return "redirect:/login";
        }

        // =====================================
        // ログインユーザー情報
        // =====================================

        String loginUserId =
                (String) session.getAttribute("loginUserId");

        String displayName =
                (String) session.getAttribute("displayName");

        String role =
                (String) session.getAttribute("role");

        model.addAttribute("loginUserId", loginUserId);
        model.addAttribute("displayName", displayName);
        model.addAttribute("role", role);

        // =====================================
        // 装置情報の集計結果
        // =====================================

        // 装置総数
        model.addAttribute(
                "totalCount",
                equipmentService.getTotalCount()
        );

        // 稼働中の装置数
        model.addAttribute(
                "runningCount",
                equipmentService.getRunningCount()
        );

        // 待機中の装置数
        model.addAttribute(
                "standbyCount",
                equipmentService.getStandbyCount()
        );

        // 停止中の装置数
        model.addAttribute(
                "stoppedCount",
                equipmentService.getStoppedCount()
        );

        // 異常の装置数
        model.addAttribute(
                "errorCount",
                equipmentService.getErrorCount()
        );

        // =====================================
        // 最近更新された装置一覧
        // =====================================

        model.addAttribute(
                "equipmentList",
                equipmentService.getRecentEquipment(10)
        );

        // ダッシュボード画面を表示
        return "Dashboard";
    }
}
