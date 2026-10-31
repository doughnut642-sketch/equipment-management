
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>装置管理システム | ダッシュボード</title>

<style>
* {
    box-sizing: border-box;
}

body {
    margin: 0;
    font-family: "Yu Gothic", "Meiryo", sans-serif;
    background: #f4f6f9;
    color: #333;
}

/* ヘッダー */
.header {
    display: flex;
    justify-content: space-between;
    align-items: center;
    padding: 18px 30px;
    background: #ffffff;
    border-bottom: 1px solid #e5e7eb;
}

.header h1 {
    margin: 0;
    font-size: 21px;
    font-weight: 600;
}

.user-info {
    display: flex;
    align-items: center;
    gap: 15px;
    font-size: 13px;
    color: #555;
}

.role {
    padding: 4px 10px;
    background: #e8eef8;
    color: #315b91;
    border-radius: 12px;
}

/* メインコンテンツ */
.container {
    max-width: 1200px;
    margin: 30px auto;
    padding: 0 24px;
}

.page-title {
    margin-bottom: 22px;
}

.page-title h2 {
    margin: 0 0 6px;
    font-size: 24px;
}

.page-title p {
    margin: 0;
    color: #777;
    font-size: 13px;
}

/* 集計カード */
.summary-grid {
    display: grid;
    grid-template-columns: repeat(5, 1fr);
    gap: 16px;
    margin-bottom: 30px;
}

.summary-card {
    background: #ffffff;
    border: 1px solid #e5e7eb;
    border-radius: 10px;
    padding: 20px;
    min-height: 115px;
}

.summary-label {
    color: #777;
    font-size: 13px;
    margin-bottom: 12px;
}

.summary-value {
    font-size: 30px;
    font-weight: 700;
}

.total {
    color: #334155;
}

.running {
    color: #16a34a;
}

.standby {
    color: #2563eb;
}

.stopped {
    color: #64748b;
}

.error {
    color: #dc2626;
}

/* 一覧エリア */
.section {
    background: #ffffff;
    border: 1px solid #e5e7eb;
    border-radius: 10px;
    margin-bottom: 24px;
    overflow: hidden;
}

.section-header {
    padding: 18px 20px;
    border-bottom: 1px solid #e5e7eb;
}

.section-header h3 {
    margin: 0;
    font-size: 17px;
}

/* テーブル */
.table-wrapper {
    overflow-x: auto;
}

table {
    width: 100%;
    border-collapse: collapse;
    min-width: 650px;
}

th, td {
    padding: 14px 18px;
    text-align: left;
    border-bottom: 1px solid #edf0f3;
    font-size: 13px;
}

th {
    background: #f8fafc;
    color: #666;
    font-weight: 600;
}

tbody tr:hover {
    background: #f8fafc;
}

tbody tr:last-child td {
    border-bottom: none;
}

/* ステータス表示 */
.status {
    display: inline-block;
    padding: 5px 10px;
    border-radius: 12px;
    font-size: 12px;
    font-weight: 600;
}

.status-running {
    color: #15803d;
    background: #dcfce7;
}

.status-standby {
    color: #1d4ed8;
    background: #dbeafe;
}

.status-stopped {
    color: #475569;
    background: #e2e8f0;
}

.status-error {
    color: #b91c1c;
    background: #fee2e2;
}

.empty-message {
    padding: 35px;
    text-align: center;
    color: #888;
}

/* ログイン情報 */
.login-info {
    padding: 18px 20px;
    background: #ffffff;
    border: 1px solid #e5e7eb;
    border-radius: 10px;
    font-size: 13px;
    color: #666;
}

.login-info p {
    margin: 5px 0;
}

/* レスポンシブ */
@media (max-width: 900px) {
    .summary-grid {
        grid-template-columns: repeat(3, 1fr);
    }
}

@media (max-width: 600px) {
    .header {
        padding: 15px;
        align-items: flex-start;
        flex-direction: column;
        gap: 10px;
    }

    .container {
        margin: 20px auto;
        padding: 0 14px;
    }

    .summary-grid {
        grid-template-columns: repeat(2, 1fr);
        gap: 10px;
    }

    .summary-card {
        padding: 15px;
    }

    .summary-value {
        font-size: 25px;
    }
}
</style>
</head>

<body>

<!-- =====================================
     ヘッダー
===================================== -->
<header class="header">

    <h1>装置管理システム</h1>

    <div class="user-info">
        <span>
            ログインユーザー：
            <strong><c:out value="${displayName}" /></strong>
        </span>

        <span class="role">
            <c:out value="${role}" />
        </span>
    </div>

</header>


<main class="container">

    <!-- =====================================
         ページタイトル
    ====================================== -->
    <div class="page-title">
        <h2>ダッシュボード</h2>
        <p>半導体製造装置の稼働状況を確認できます。</p>
    </div>


    <!-- =====================================
         装置稼働状況の集計
    ====================================== -->
    <div class="summary-grid">

        <!-- 装置総数 -->
        <div class="summary-card">
            <div class="summary-label">装置総数</div>
            <div class="summary-value total">
                <c:out value="${totalCount}" />
            </div>
        </div>

        <!-- 稼働中 -->
        <div class="summary-card">
            <div class="summary-label">稼働中</div>
            <div class="summary-value running">
                <c:out value="${runningCount}" />
            </div>
        </div>

        <!-- 待機中 -->
        <div class="summary-card">
            <div class="summary-label">待機中</div>
            <div class="summary-value standby">
                <c:out value="${standbyCount}" />
            </div>
        </div>

        <!-- 停止中 -->
        <div class="summary-card">
            <div class="summary-label">停止中</div>
            <div class="summary-value stopped">
                <c:out value="${stoppedCount}" />
            </div>
        </div>

        <!-- 異常 -->
        <div class="summary-card">
            <div class="summary-label">異常</div>
            <div class="summary-value error">
                <c:out value="${errorCount}" />
            </div>
        </div>

    </div>


    <!-- =====================================
         最近更新された装置一覧
    ====================================== -->
    <section class="section">

        <div class="section-header">
            <h3>最近更新された装置一覧</h3>
        </div>

        <div class="table-wrapper">

            <table>

                <thead>
                    <tr>
                        <th>装置管理番号</th>
                        <th>装置名</th>
                        <th>製造エリア</th>
                        <th>設置場所</th>
                        <th>ステータス</th>
                        <th>最終更新日時</th>
                    </tr>
                </thead>

                <tbody>

                    <c:choose>

                        <c:when test="${not empty equipmentList}">

                            <c:forEach var="equipment"
                                items="${equipmentList}">

                                <tr>

                                    <td>
                                        <c:out value="${equipment.equipmentId}" />
                                    </td>

                                    <td>
                                        <c:out value="${equipment.equipmentName}" />
                                    </td>

                                    <td>
                                        <c:out value="${equipment.area}" />
                                    </td>

                                    <td>
                                        <c:out value="${equipment.location}" />
                                    </td>

                                    <td>

                                        <c:choose>

                                            <c:when test="${equipment.status == 'RUNNING'}">
                                                <span class="status status-running">
                                                    稼働中
                                                </span>
                                            </c:when>

                                            <c:when test="${equipment.status == 'STANDBY'}">
                                                <span class="status status-standby">
                                                    待機中
                                                </span>
                                            </c:when>

                                            <c:when test="${equipment.status == 'STOPPED'}">
                                                <span class="status status-stopped">
                                                    停止中
                                                </span>
                                            </c:when>

                                            <c:when test="${equipment.status == 'ERROR'}">
                                                <span class="status status-error">
                                                    異常
                                                </span>
                                            </c:when>

                                            <c:otherwise>
                                                <span class="status">
                                                    <c:out value="${equipment.status}" />
                                                </span>
                                            </c:otherwise>

                                        </c:choose>

                                    </td>

                                    <td>
                                        <c:out value="${equipment.updatedAt}" />
                                    </td>

                                </tr>

                            </c:forEach>

                        </c:when>

                        <c:otherwise>

                            <tr>
                                <td colspan="6" class="empty-message">
                                    登録されている装置情報はありません。
                                </td>
                            </tr>

                        </c:otherwise>

                    </c:choose>

                </tbody>

            </table>

        </div>

    </section>


    <!-- =====================================
         ログイン情報
    ====================================== -->
    <section class="login-info">

        <p>
            ログインID：
            <strong><c:out value="${loginUserId}" /></strong>
        </p>

        <p>
            表示名：
            <strong><c:out value="${displayName}" /></strong>
        </p>

        <p>
            権限：
            <strong><c:out value="${role}" /></strong>
        </p>

    </section>

</main>

</body>
</html>
