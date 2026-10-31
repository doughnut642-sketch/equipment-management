
package com.example.app.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.app.model.User;

/**
 * ユーザー情報のデータアクセスクラス
 *
 * SQLiteからユーザー情報を取得する。
 */
@Repository
public class UserRepository {

	// SQLiteデータベースの接続先
	private static final String DB_URL = "jdbc:sqlite:C:/dev/pleiades/workspace/"
			+ "equipment-management/database/"
			+ "equipment-management/equipment-management.db";

	// 全ユーザー取得SQL
	private static final String SQL_FIND_ALL = "SELECT "
			+ "user_id, "
			+ "display_name, "
			+ "role, "
			+ "enabled, "
			+ "created_at, "
			+ "updated_at "
			+ "FROM users "
			+ "ORDER BY user_id";

	// ユーザーIDによる検索SQL
	private static final String SQL_FIND_BY_USER_ID = "SELECT "
			+ "user_id, "
			+ "password_hash, "
			+ "display_name, "
			+ "role, "
			+ "enabled, "
			+ "created_at, "
			+ "updated_at "
			+ "FROM users "
			+ "WHERE user_id = ?";

	/**
	 * 全ユーザー情報を取得する
	 *
	 * @return ユーザー情報のリスト
	 * @throws SQLException SQL実行時の例外
	 */
	public List<User> findAll() throws SQLException {

		List<User> users = new ArrayList<User>();

		try (
				Connection connection = DriverManager.getConnection(DB_URL);

				PreparedStatement statement = connection.prepareStatement(SQL_FIND_ALL);

				ResultSet resultSet = statement.executeQuery()) {

			while (resultSet.next()) {

				User user = new User();

				user.setUserId(
						resultSet.getString("user_id"));

				user.setDisplayName(
						resultSet.getString("display_name"));

				user.setRole(
						resultSet.getString("role"));

				user.setEnabled(
						resultSet.getInt("enabled") == 1);

				user.setCreatedAt(
						resultSet.getString("created_at"));

				user.setUpdatedAt(
						resultSet.getString("updated_at"));

				users.add(user);
			}
		}

		return users;
	}

	/**
	 * ユーザーIDを指定してユーザー情報を取得する
	 *
	 * @param userId ユーザーID
	 * @return ユーザー情報。該当ユーザーが存在しない場合はnull
	 * @throws SQLException SQL実行時の例外
	 */
	public User findByUserId(String userId)
			throws SQLException {

		try (
				Connection connection = DriverManager.getConnection(DB_URL);

				PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_USER_ID)) {

			statement.setString(1, userId);

			try (
					ResultSet resultSet = statement.executeQuery()) {

				if (resultSet.next()) {

					User user = new User();

					user.setUserId(
							resultSet.getString("user_id"));

					user.setPasswordHash(
							resultSet.getString("password_hash"));

					user.setDisplayName(
							resultSet.getString("display_name"));

					user.setRole(
							resultSet.getString("role"));

					user.setEnabled(
							resultSet.getInt("enabled") == 1);

					user.setCreatedAt(
							resultSet.getString("created_at"));

					user.setUpdatedAt(
							resultSet.getString("updated_at"));

					return user;
				}
			}
		}

		return null;
	}
}
