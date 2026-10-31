
package com.example.app.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

public class UserSelectTest {

	// SQLiteデータベースの保存先
	private static final String DB_URL = "jdbc:sqlite:C:/dev/pleiades/workspace/"
			+ "equipment-management/database/"
			+ "equipment-management/equipment-management.db";

	// ユーザー情報取得SQL
	private static final String SQL = "SELECT "
			+ "user_id, "
			+ "display_name, "
			+ "role, "
			+ "enabled, "
			+ "created_at, "
			+ "updated_at "
			+ "FROM users "
			+ "ORDER BY user_id";

	@Test
	public void testSelectUsers() {

		System.out.println("================================");
		System.out.println("ユーザー情報取得テスト開始");
		System.out.println("================================");

		System.out.println("実行SQL：");
		System.out.println(SQL);

		try (
				Connection connection = DriverManager.getConnection(DB_URL)) {

			System.out.println("[1] SQLite接続成功");
			System.out.println("接続先DB："
					+ connection.getMetaData().getURL());

			try (
					PreparedStatement tableStatement = connection.prepareStatement(
							"SELECT name FROM sqlite_master "
									+ "WHERE type = 'table' ORDER BY name");

					ResultSet tableResult = tableStatement.executeQuery()) {
				System.out.println("----- Java側のテーブル一覧 -----");

				while (tableResult.next()) {
					System.out.println(
							tableResult.getString("name"));
				}
			}

			try (
					PreparedStatement statement = connection.prepareStatement(SQL);

					ResultSet resultSet = statement.executeQuery()) {

				System.out.println("[2] SQL実行成功");

				int count = 0;

				while (resultSet.next()) {

					count++;

					System.out.println("------------------------------");
					System.out.println("ユーザーID："
							+ resultSet.getString("user_id"));

					System.out.println("表示名："
							+ resultSet.getString("display_name"));

					System.out.println("権限："
							+ resultSet.getString("role"));

					System.out.println("有効状態："
							+ resultSet.getInt("enabled"));

					System.out.println("登録日時："
							+ resultSet.getString("created_at"));

					System.out.println("更新日時："
							+ resultSet.getString("updated_at"));
				}

				System.out.println("------------------------------");
				System.out.println("取得件数：" + count + "件");
			}

		} catch (SQLException e) {

			System.out.println("[エラー] SQLite処理に失敗しました");
			e.printStackTrace();

		}

		System.out.println("================================");
		System.out.println("ユーザー情報取得テスト終了");
		System.out.println("================================");
	}
}
