package com.example.app.test;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;
import org.mindrot.jbcrypt.BCrypt;

import com.example.app.model.User;
import com.example.app.repository.UserRepository;
import com.example.app.service.LoginService;

/**
 * LoginServiceのログイン認証テスト
 */
public class LoginServiceTest {

    private LoginService loginService;
    private TestUserRepository userRepository;

    /**
     * 各テストの実行前にテスト環境を準備する
     */
    @Before
    public void setUp() {

        userRepository = new TestUserRepository();

        loginService = new LoginService(userRepository);

        // 有効なユーザー
        User admin = createUser(
                "admin",
                "システム管理者",
                "ADMIN",
                true,
                "password123"
        );

        // 無効化されたユーザー
        User disabledUser = createUser(
                "user002",
                "製造担当者B",
                "USER",
                false,
                "password123"
        );

        userRepository.addUser(admin);
        userRepository.addUser(disabledUser);
    }

    /**
     * 正しいユーザーIDとパスワードで認証成功すること
     */
    @Test
    public void testAuthenticateSuccess() throws SQLException {

        User user = loginService.authenticate(
                "admin",
                "password123"
        );

        assertNotNull(user);
        assertEquals("admin", user.getUserId());
        assertEquals("システム管理者", user.getDisplayName());
        assertEquals("ADMIN", user.getRole());

        // パスワードハッシュが返却されないこと
        assertNull(user.getPasswordHash());
    }

    /**
     * パスワードが間違っている場合に認証失敗すること
     */
    @Test
    public void testAuthenticateWrongPassword() throws SQLException {

        User user = loginService.authenticate(
                "admin",
                "wrongpassword"
        );

        assertNull(user);
    }

    /**
     * 存在しないユーザーIDの場合に認証失敗すること
     */
    @Test
    public void testAuthenticateUnknownUser() throws SQLException {

        User user = loginService.authenticate(
                "unknown",
                "password123"
        );

        assertNull(user);
    }

    /**
     * 無効化されたユーザーは認証できないこと
     */
    @Test
    public void testAuthenticateDisabledUser() throws SQLException {

        User user = loginService.authenticate(
                "user002",
                "password123"
        );

        assertNull(user);
    }

    /**
     * ユーザーIDまたはパスワードが空の場合に認証失敗すること
     */
    @Test
    public void testAuthenticateEmptyInput() throws SQLException {

        // ユーザーIDが空
        assertNull(
                loginService.authenticate("", "password123")
        );

        // パスワードが空
        assertNull(
                loginService.authenticate("admin", "")
        );

        // 両方が空
        assertNull(
                loginService.authenticate("", "")
        );
    }

    /**
     * テスト用ユーザーを作成する
     */
    private User createUser(
            String userId,
            String displayName,
            String role,
            boolean enabled,
            String password) {

        User user = new User();

        user.setUserId(userId);
        user.setDisplayName(displayName);
        user.setRole(role);
        user.setEnabled(enabled);

        // BCryptでテスト用パスワードをハッシュ化
        String passwordHash = BCrypt.hashpw(
                password,
                BCrypt.gensalt()
        );

        user.setPasswordHash(passwordHash);

        return user;
    }

    /**
     * SQLiteに接続せず、テスト用ユーザーを返すRepository
     */
    private static class TestUserRepository extends UserRepository {

        private java.util.Map<String, User> users =
                new java.util.HashMap<String, User>();

        /**
         * テスト用ユーザーを登録する
         */
        public void addUser(User user) {
            users.put(user.getUserId(), user);
        }

        /**
         * ユーザーIDからテスト用ユーザーを取得する
         */
        @Override
        public User findByUserId(String userId) {

            return users.get(userId);
        }
    }
}