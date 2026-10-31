
-- =========================================
-- 装置情報テーブル作成
-- =========================================

CREATE TABLE IF NOT EXISTS equipment (

    -- 装置ID（主キー）
    id INTEGER PRIMARY KEY AUTOINCREMENT,

    -- 装置管理番号
    equipment_code TEXT NOT NULL UNIQUE,

    -- 装置名
    equipment_name TEXT NOT NULL,

    -- 製造エリア
    area TEXT NOT NULL,

    -- 設置場所
    location TEXT NOT NULL,

    -- 装置ステータス
    status TEXT NOT NULL
        CHECK (
            status IN (
                'RUNNING',
                'STANDBY',
                'STOPPED',
                'ERROR'
            )
        ),

    -- 登録日時
    created_at TEXT NOT NULL
        DEFAULT (
            strftime('%Y-%m-%dT%H:%M:%fZ', 'now')
        ),

    -- 更新日時
    updated_at TEXT NOT NULL
        DEFAULT (
            strftime('%Y-%m-%dT%H:%M:%fZ', 'now')
        )
);

-- =========================================
-- 検索用インデックス
-- =========================================

-- ステータス検索用
CREATE INDEX IF NOT EXISTS idx_equipment_status
ON equipment(status);

-- 更新日時による並び替え用
CREATE INDEX IF NOT EXISTS idx_equipment_updated_at
ON equipment(updated_at);
