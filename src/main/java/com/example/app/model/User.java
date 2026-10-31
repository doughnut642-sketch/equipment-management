
package com.example.app.model;

/**
 * ユーザー情報を保持するモデルクラス
 */
public class User {

    // ユーザーID
    private String userId;

    // パスワードハッシュ
    private String passwordHash;

    // 表示名
    private String displayName;

    // ユーザー権限
    private String role;

    // アカウント有効状態
    private boolean enabled;

    // 登録日時
    private String createdAt;

    // 更新日時
    private String updatedAt;

    // デフォルトコンストラクタ
    public User() {
    }

    // ユーザーIDの取得
    public String getUserId() {
        return userId;
    }

    // ユーザーIDの設定
    public void setUserId(String userId) {
        this.userId = userId;
    }

    // パスワードハッシュの取得
    public String getPasswordHash() {
        return passwordHash;
    }

    // パスワードハッシュの設定
    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    // 表示名の取得
    public String getDisplayName() {
        return displayName;
    }

    // 表示名の設定
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    // 権限の取得
    public String getRole() {
        return role;
    }

    // 権限の設定
    public void setRole(String role) {
        this.role = role;
    }

    // 有効状態の取得
    public boolean isEnabled() {
        return enabled;
    }

    // 有効状態の設定
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    // 登録日時の取得
    public String getCreatedAt() {
        return createdAt;
    }

    // 登録日時の設定
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    // 更新日時の取得
    public String getUpdatedAt() {
        return updatedAt;
    }

    // 更新日時の設定
    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }
}
