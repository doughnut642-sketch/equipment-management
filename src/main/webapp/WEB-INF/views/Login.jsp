
<%@ page language="java"
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ja">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>Equipment Management - Login</title>

    <!-- 共通CSS -->
    <link rel="stylesheet"
          href="<c:url value='/resources/css/common.css' />">

    <!-- ログイン画面専用CSS -->
    <link rel="stylesheet"
          href="<c:url value='/resources/css/login.css' />">

</head>

<body>

    <main class="login-container">

        <div class="login-card">

            <!-- システム名 -->
            <div class="login-header">
                <h1>Equipment Management System</h1>
                <p>半導体製造装置管理システム</p>
            </div>

            <!-- ログインフォーム -->
            <div class="login-content">

                <h2>ログイン</h2>

                <form action="<c:url value='/login' />"
                      method="post">

                    <div class="form-group">

                        <label for="username">
                            ユーザーID
                        </label>

                        <input type="text"
                               id="username"
                               name="username"
                               placeholder="ユーザーIDを入力してください"
                               autocomplete="username"
                               required>

                    </div>

                    <div class="form-group">

                        <label for="password">
                            パスワード
                        </label>

                        <input type="password"
                               id="password"
                               name="password"
                               placeholder="パスワードを入力してください"
                               autocomplete="current-password"
                               required>

                    </div>

                    <button type="submit" class="login-button">
                        ログイン
                    </button>

                </form>

            </div>

            <!-- フッター -->
            <div class="login-footer">
                <p>Equipment Management System</p>
            </div>

        </div>

    </main>

</body>
</html>
