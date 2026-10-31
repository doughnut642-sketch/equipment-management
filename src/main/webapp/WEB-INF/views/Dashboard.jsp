
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="ja">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>装置管理システム | ダッシュボード</title>

    <!-- 共通CSS -->
    <link rel="stylesheet"
          href="<c:url value='/resources/css/common.css' />">

    <!-- ダッシュボード専用CSS -->
    <link rel="stylesheet"
          href="<c:url value='/resources/css/dashboard.css' />">

</head>

<body>

    <!-- =====================================
         ヘッダー
    ====================================== -->
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

            <!-- ログアウトボタン -->
            <form class="logout-form"
                  action="<c:url value='/logout' />"
                  method="post">

                <button type="submit" class="logout-button">
                    ログアウト
                </button>

            </form>

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
