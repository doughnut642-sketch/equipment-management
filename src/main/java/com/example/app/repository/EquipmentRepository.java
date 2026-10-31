package com.example.app.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.example.app.model.Equipment;

/**
 * 装置情報のデータベースアクセスを担当するRepository
 */
@Repository
public class EquipmentRepository {

    private final JdbcTemplate jdbcTemplate;

    /**
     * Equipmentの検索結果をマッピングする
     */
    private final RowMapper<Equipment> equipmentRowMapper =
            new RowMapper<Equipment>() {

        @Override
        public Equipment mapRow(
                java.sql.ResultSet rs,
                int rowNum) throws java.sql.SQLException {

            Equipment equipment = new Equipment();

            equipment.setEquipmentId(
                    rs.getString("equipment_code"));

            equipment.setEquipmentName(
                    rs.getString("equipment_name"));

            equipment.setArea(
                    rs.getString("area"));

            equipment.setLocation(
                    rs.getString("location"));

            equipment.setStatus(
                    rs.getString("status"));

            equipment.setCreatedAt(
                    rs.getString("created_at"));

            equipment.setUpdatedAt(
                    rs.getString("updated_at"));

            return equipment;
        }
    };

    /**
     * JdbcTemplateをDIする
     */
    @Autowired
    public EquipmentRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * 装置情報をすべて取得する
     */
    public List<Equipment> findAll() {

        String sql =
                "SELECT "
                + "equipment_code, "
                + "equipment_name, "
                + "area, "
                + "location, "
                + "status, "
                + "created_at, "
                + "updated_at "
                + "FROM equipment "
                + "ORDER BY equipment_code";

        return jdbcTemplate.query(sql, equipmentRowMapper);
    }

    /**
     * ステータスを指定して装置情報を取得する
     */
    public List<Equipment> findByStatus(String status) {

        String sql =
                "SELECT "
                + "equipment_code, "
                + "equipment_name, "
                + "area, "
                + "location, "
                + "status, "
                + "created_at, "
                + "updated_at "
                + "FROM equipment "
                + "WHERE status = ? "
                + "ORDER BY equipment_code";

        return jdbcTemplate.query(
                sql,
                equipmentRowMapper,
                status
        );
    }

    /**
     * 装置の総数を取得する
     */
    public int countAll() {

        String sql =
                "SELECT COUNT(*) FROM equipment";

        Integer count = jdbcTemplate.queryForObject(
                sql,
                Integer.class
        );

        return count != null ? count.intValue() : 0;
    }

    /**
     * ステータス別の装置数を取得する
     */
    public int countByStatus(String status) {

        String sql =
                "SELECT COUNT(*) "
                + "FROM equipment "
                + "WHERE status = ?";

        Integer count = jdbcTemplate.queryForObject(
                sql,
                new Object[] { status },
                Integer.class
        );

        return count != null ? count.intValue() : 0;
    }

    /**
     * 更新日時が新しい装置情報を取得する
     */
    public List<Equipment> findRecent(int limit) {

        if (limit <= 0) {
            throw new IllegalArgumentException(
                    "limitは1以上を指定してください。");
        }

        String sql =
                "SELECT "
                + "equipment_code, "
                + "equipment_name, "
                + "area, "
                + "location, "
                + "status, "
                + "created_at, "
                + "updated_at "
                + "FROM equipment "
                + "ORDER BY updated_at DESC, equipment_code "
                + "LIMIT ?";

        return jdbcTemplate.query(
                sql,
                equipmentRowMapper,
                limit
        );
    }
}