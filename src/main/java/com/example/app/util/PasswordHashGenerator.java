package com.example.app.util;

import java.util.Scanner;

import org.mindrot.jbcrypt.BCrypt;

/**
 * 開発用ユーザーのパスワードハッシュを生成するクラス
 *
 * 生成したハッシュをSQLiteのUPDATE文として出力する。
 */
public class PasswordHashGenerator {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("=== BCryptハッシュ生成ツール ===");
        System.out.println("対象ユーザー: admin または user001");
        System.out.print("ユーザーIDを入力してください: ");

        String userId = scanner.nextLine().trim();

        if (!"admin".equals(userId)
                && !"user001".equals(userId)) {
            System.out.println("対象外のユーザーIDです。");
            scanner.close();
            return;
        }

        System.out.print("設定するパスワードを入力してください: ");
        String password = scanner.nextLine();

        if (password.trim().isEmpty()) {
            System.out.println("パスワードを入力してください。");
            scanner.close();
            return;
        }

        // BCryptのコスト係数10でハッシュ化
        String hashedPassword = BCrypt.hashpw(
            password,
            BCrypt.gensalt(10)
        );

        // 生成したハッシュを確認
        boolean verified = BCrypt.checkpw(
            password,
            hashedPassword
        );

        System.out.println();
        System.out.println("=== 生成結果 ===");
        System.out.println("ユーザーID: " + userId);
        System.out.println("ハッシュ: " + hashedPassword);
        System.out.println("照合テスト: " + verified);

        // SQLiteで実行するUPDATE文を出力
        System.out.println();
        System.out.println("=== SQLite実行用SQL ===");
        System.out.println(
            "UPDATE users "
            + "SET password_hash = '" + hashedPassword + "' "
            + "WHERE user_id = '" + userId + "';"
        );

        scanner.close();
    }
}