
-- =========================================
-- 装置情報テストデータ登録
-- =========================================

INSERT OR IGNORE INTO equipment (
    equipment_code,
    equipment_name,
    area,
    location,
    status
)
VALUES
(
    'EQ-001',
    'CVD装置01',
    'CVDエリア',
    '第1工場 1F',
    'RUNNING'
),
(
    'EQ-002',
    'CVD装置02',
    'CVDエリア',
    '第1工場 2F',
    'RUNNING'
),
(
    'EQ-003',
    'CMP装置01',
    'CMPエリア',
    '第2工場 1F',
    'STANDBY'
),
(
    'EQ-004',
    'ETCH装置01',
    'ETCHエリア',
    '第2工場 2F',
    'STOPPED'
),
(
    'EQ-005',
    'ETCH装置02',
    'ETCHエリア',
    '第2工場 2F',
    'ERROR'
);

-- =========================================
-- 登録結果確認
-- =========================================

SELECT
    equipment_code,
    equipment_name,
    area,
    location,
    status,
    created_at,
    updated_at
FROM equipment
ORDER BY equipment_code;
