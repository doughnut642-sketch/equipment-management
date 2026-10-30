<%@ page language="java"
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>Equipment Management - Login</title>
</head>
<body>

    <h1>Equipment Management System</h1>

    <h2>ログイン</h2>

    <form action="${pageContext.request.contextPath}/login"
          method="post">

        <div>
            <label for="username">ユーザーID</label>
            <input type="text" id="username" name="username">
        </div>

        <div>
            <label for="password">パスワード</label>
            <input type="password" id="password" name="password">
        </div>

        <button type="submit">ログイン</button>

    </form>

</body>
</html>