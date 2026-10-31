

INSERT INTO users (
    user_id,
    password_hash,
    display_name,
    role,
    enabled
)
VALUES

-- ① 管理者アカウント
(
    'admin',
    '$2a$10$REPLACE_WITH_VALID_BCRYPT_HASH',
    'システム管理者',
    'ADMIN',
    1
),

-- ② 一般ユーザーアカウント
(
    'user001',
    '$2a$10$REPLACE_WITH_VALID_BCRYPT_HASH',
    '製造担当者A',
    'USER',
    1
),

-- ③ 無効ユーザーアカウント
(
    'user002',
    '$2a$10$REPLACE_WITH_VALID_BCRYPT_HASH',
    '製造担当者B',
    'USER',
    0
);

-- ==========================================
-- 登録結果確認
-- ==========================================

SELECT
    user_id,
    display_name,
    role,
    enabled,
    created_at,
    updated_at
FROM users
ORDER BY user_id;
