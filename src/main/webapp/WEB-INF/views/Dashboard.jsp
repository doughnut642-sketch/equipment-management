<%@ page language="java"
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c"
    uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0">

    <title>Equipment Management - Dashboard</title>

    <style>
        * {
            box-sizing: border-box;
        }

        body {
            margin: 0;
            font-family: "Meiryo", sans-serif;
            background-color: #f4f6f9;
            color: #333;
        }

        /* ヘッダー */
        .header {
            height: 64px;
            background-color: #ffffff;
            border-bottom: 1px solid #e5e7eb;
            display: flex;
            align-items: center;
            justify-content: space-between;
            padding: 0 32px;
        }

        .logo {
            font-size: 20px;
            font-weight: bold;
            color: #2563eb;
        }

        .user-info {
            display: flex;
            align-items: center;
            gap: 16px;
            font-size: 14px;
        }

        .role {
            background-color: #e8efff;
            color: #2563eb;
            padding: 4px 10px;
            border-radius: 12px;
            font-size: 12px;
        }

        /* メインコンテンツ */
        .container {
            max-width: 1400px;
            margin: 0 auto;
            padding: 32px;
        }

        .page-title {
            margin-bottom: 8px;
            font-size: 26px;
        }

        .welcome-message {
            color: #6b7280;
            margin-bottom: 32px;
        }

        /* 集計カード */
        .summary-grid {
            display: grid;
            grid-template-columns: repeat(4, 1fr);
            gap: 20px;
            margin-bottom: 32px;
        }

        .summary-card {
            background-color: #ffffff;
            border: 1px solid #e5e7eb;
            border-radius: 8px;
            padding: 24px;
        }

        .summary-label {
            color: #6b7280;
            font-size: 14px;
            margin-bottom: 12px;
        }

        .summary-value {
            font-size: 32px;
            font-weight: bold;
        }

        .summary-description {
            color: #9ca3af;
            font-size: 12px;
            margin-top: 8px;
        }

        /* コンテンツパネル */
        .panel {
            background-color: #ffffff;
            border: 1px solid #e5e7eb;
            border-radius: 8px;
            padding: 24px;
            margin-bottom: 24px;
        }

        .panel-title {
            font-size: 18px;
            font-weight: bold;
            margin-bottom: 20px;
        }

        .empty-message {
            color: #9ca3af;
            text-align: center;
            padding: 32px 0;
        }

        /* テーブル */
        table {
            width: 100%;
            border-collapse: collapse;
        }

        th, td {
            padding: 14px;
            text-align: left;
            border-bottom: 1px solid #e5e7eb;
            font-size: 14px;
        }

        th {
            background-color: #f9fafb;
            color: #6b7280;
            font-weight: normal;
        }

        /* レスポンシブ */
        @media (max-width: 900px) {
            .summary-grid {
                grid-template-columns: repeat(2, 1fr);
            }

            .container {
                padding: 20px;
            }
        }

        @media (max-width: 600px) {
            .header {
                padding: 0 16px;
            }

            .summary-grid {
                grid-template-columns: 1fr;
            }

            .user-info {
                gap: 8px;
                font-size: 12px;
            }
        }
    </style>
</head>

<body>

    <!-- ヘッダー -->
    <header class="header">

        <div class="logo">
            Equipment Management
        </div>

        <div class="user-info">

            <span>
                <c:out value="${displayName}" />
            </span>

            <span class="role">
                <c:out value="${role}" />
            </span>

        </div>

    </header>


    <!-- メインコンテンツ -->
    <main class="container">

        <h1 class="page-title">
            ダッシュボード
        </h1>

        <p class="welcome-message">
            <c:out value="${displayName}" /> さん、ようこそ。
        </p>


        <!-- 装置稼働状況 -->
        <section class="summary-grid">

            <div class="summary-card">
                <div class="summary-label">
                    装置総数
                </div>

                <div class="summary-value">
                    -
                </div>

                <div class="summary-description">
                    登録されている装置数
                </div>
            </div>


            <div class="summary-card">
                <div class="summary-label">
                    稼働中
                </div>

                <div class="summary-value">
                    -
                </div>

                <div class="summary-description">
                    現在稼働している装置数
                </div>
            </div>


            <div class="summary-card">
                <div class="summary-label">
                    停止中
                </div>

                <div class="summary-value">
                    -
                </div>

                <div class="summary-description">
                    停止状態の装置数
                </div>
            </div>


            <div class="summary-card">
                <div class="summary-label">
                    異常
                </div>

                <div class="summary-value">
                    -
                </div>

                <div class="summary-description">
                    異常が発生している装置数
                </div>
            </div>

        </section>


        <!-- 装置一覧 -->
        <section class="panel">

            <h2 class="panel-title">
                装置一覧
            </h2>

            <table>

                <thead>
                    <tr>
                        <th>装置ID</th>
                        <th>装置名</th>
                        <th>設置エリア</th>
                        <th>ステータス</th>
                        <th>最終更新日時</th>
                    </tr>
                </thead>

                <tbody>
                    <tr>
                        <td colspan="5" class="empty-message">
                            装置情報はまだ登録されていません。
                        </td>
                    </tr>
                </tbody>

            </table>

        </section>


        <!-- ログイン情報 -->
        <section class="panel">

            <h2 class="panel-title">
                ログイン情報
            </h2>

            <p>
                ユーザーID：
                <c:out value="${loginUserId}" />
            </p>

            <p>
                表示名：
                <c:out value="${displayName}" />
            </p>

            <p>
                権限：
                <c:out value="${role}" />
            </p>

        </section>

    </main>

</body>
</html>