
package com.example.app.service;

import java.nio.charset.StandardCharsets;
import java.sql.SQLException;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.app.model.User;
import com.example.app.repository.UserRepository;

/**
 * ログイン認証の業務ロジックを担当するクラス
 */
@Service
public class LoginService {

    // ユーザー情報の取得
    private final UserRepository userRepository;

    /**
     * コンストラクタ
     *
     * @param userRepository ユーザー情報のデータアクセスクラス
     */
    @Autowired
    public LoginService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * ログイン認証を行う
     *
     * @param userId ユーザーID
     * @param password 入力されたパスワード
     * @return 認証成功：Userオブジェクト
     *         認証失敗：null
     * @throws SQLException データベース処理時の例外
     */
    public User authenticate(String userId, String password)
            throws SQLException {

        // 1. 入力チェック
        if (userId == null || userId.trim().isEmpty()) {
            return null;
        }

        if (password == null || password.isEmpty()) {
            return null;
        }

        // ユーザーIDの前後の空白を除去
        String normalizedUserId = userId.trim();

        // ユーザーIDの文字数チェック
        if (normalizedUserId.length() > 50) {
            return null;
        }

        // BCryptのパスワード上限は72バイト
        int passwordBytes =
            password.getBytes(StandardCharsets.UTF_8).length;

        if (passwordBytes > 72) {
            return null;
        }

        // 2. ユーザー情報を取得
        User user =
            userRepository.findByUserId(normalizedUserId);

        // 3. ユーザーの存在確認
        if (user == null) {
            return null;
        }

        // 4. アカウントの有効状態を確認
        if (!user.isEnabled()) {
            return null;
        }

        // 5. パスワードハッシュを取得
        String passwordHash = user.getPasswordHash();

        if (passwordHash == null || passwordHash.isEmpty()) {
            return null;
        }

        // 6. BCryptによるパスワード照合
        boolean passwordMatches;

        try {
            passwordMatches =
                BCrypt.checkpw(password, passwordHash);

        } catch (IllegalArgumentException e) {
            // ハッシュ形式が不正な場合は認証失敗
            return null;
        }

        if (!passwordMatches) {
            return null;
        }

        // 7. 認証成功
        // パスワードハッシュを後続処理に渡さない
        user.setPasswordHash(null);

        return user;
    }
}
