adminP
=== BCryptハッシュ生成ツール ===
対象ユーザー: admin または user001
ユーザーIDを入力してください: admin
設定するパスワードを入力してください: adminP

=== 生成結果 ===
ユーザーID: admin
ハッシュ: $2a$10$TDYqf74G2HfGGaZmfygQQe2t98stOXGOuIuxyBQwhRtWT3azLhnDq
照合テスト: true

=== SQLite実行用SQL ===
UPDATE users SET password_hash = '$2a$10$TDYqf74G2HfGGaZmfygQQe2t98stOXGOuIuxyBQwhRtWT3azLhnDq' WHERE user_id = 'admin';


user1P
=== BCryptハッシュ生成ツール ===
対象ユーザー: admin または user001
ユーザーIDを入力してください: user001
設定するパスワードを入力してください: user1P

=== 生成結果 ===
ユーザーID: user001
ハッシュ: $2a$10$uQmI8ByE3xUdjwmWcoUub.LN3KzA00kfCdChBc7tp3D9FDwWLLH7e
照合テスト: true

=== SQLite実行用SQL ===
UPDATE users SET password_hash = '$2a$10$uQmI8ByE3xUdjwmWcoUub.LN3KzA00kfCdChBc7tp3D9FDwWLLH7e' WHERE user_id = 'user001';

SELECT
    user_id,
    display_name,
    role,
    enabled,
    length(password_hash) AS hash_length
FROM users
ORDER BY user_id;