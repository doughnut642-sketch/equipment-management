
-- ==========================================
-- usersテーブル作成
-- ==========================================

CREATE TABLE IF NOT EXISTS users (

    -- ユーザーID（主キー）
    user_id TEXT NOT NULL COLLATE NOCASE
        PRIMARY KEY
        CHECK (length(trim(user_id)) BETWEEN 1 AND 50),

    -- BCryptパスワードハッシュ
    password_hash TEXT NOT NULL,

    -- ユーザー表示名
    display_name TEXT NOT NULL
        CHECK (length(trim(display_name)) BETWEEN 1 AND 100),

    -- ユーザー権限
    -- ADMIN：管理者
    -- USER ：一般ユーザー
    role TEXT NOT NULL DEFAULT 'USER'
        CHECK (role IN ('ADMIN', 'USER')),

    -- アカウント有効状態
    -- 1：有効
    -- 0：無効
    enabled INTEGER NOT NULL DEFAULT 1
        CHECK (enabled IN (0, 1)),

    -- 登録日時（UTC）
    created_at TEXT NOT NULL
        DEFAULT (strftime('%Y-%m-%dT%H:%M:%fZ', 'now')),

    -- 更新日時（UTC）
    updated_at TEXT NOT NULL
        DEFAULT (strftime('%Y-%m-%dT%H:%M:%fZ', 'now'))

);
