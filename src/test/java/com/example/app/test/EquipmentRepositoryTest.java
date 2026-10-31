
package com.example.app.test;

import static org.junit.Assert.*;

import java.util.List;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.example.app.model.Equipment;
import com.example.app.repository.EquipmentRepository;

public class EquipmentRepositoryTest {

    private EquipmentRepository equipmentRepository;

    /**
     * テスト実行前の初期設定
     *
     * SQLiteのDataSourceとJdbcTemplateを生成し、
     * EquipmentRepositoryに渡す。
     */
    @Before
    public void setUp() {

        DataSource dataSource = new DriverManagerDataSource();

        ((DriverManagerDataSource) dataSource)
                .setDriverClassName("org.sqlite.JDBC");

        ((DriverManagerDataSource) dataSource)
                .setUrl(
                    "jdbc:sqlite:C:/dev/pleiades/workspace/"
                    + "equipment-management/database/"
                    + "equipment-management/equipment-management.db"
                );

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        equipmentRepository =
                new EquipmentRepository(jdbcTemplate);
    }

    /**
     * 全装置情報の取得テスト
     */
    @Test
    public void testFindAll() {

        List<Equipment> equipmentList =
                equipmentRepository.findAll();

        assertNotNull(equipmentList);
        assertEquals(5, equipmentList.size());

        Equipment equipment = equipmentList.get(0);

        assertEquals("EQ-001", equipment.getEquipmentId());
        assertEquals("CVD装置01", equipment.getEquipmentName());
        assertEquals("CVDエリア", equipment.getArea());
        assertEquals("第1工場 1F", equipment.getLocation());
        assertEquals("RUNNING", equipment.getStatus());

        System.out.println("全装置情報取得テスト成功");
        System.out.println("取得件数：" + equipmentList.size());
    }

    /**
     * ステータス別の装置情報取得テスト
     */
    @Test
    public void testFindByStatus() {

        List<Equipment> equipmentList =
                equipmentRepository.findByStatus("RUNNING");

        assertNotNull(equipmentList);
        assertEquals(2, equipmentList.size());

        for (Equipment equipment : equipmentList) {
            assertEquals("RUNNING", equipment.getStatus());
        }

        System.out.println("ステータス別取得テスト成功");
        System.out.println("稼働中の装置数：" + equipmentList.size());
    }

    /**
     * 全装置件数の取得テスト
     */
    @Test
    public void testCountAll() {

        int count = equipmentRepository.countAll();

        assertEquals(5, count);

        System.out.println("全装置件数取得テスト成功");
        System.out.println("装置総数：" + count);
    }

    /**
     * ステータス別の装置件数取得テスト
     */
    @Test
    public void testCountByStatus() {

        int runningCount =
                equipmentRepository.countByStatus("RUNNING");

        int standbyCount =
                equipmentRepository.countByStatus("STANDBY");

        int stoppedCount =
                equipmentRepository.countByStatus("STOPPED");

        int errorCount =
                equipmentRepository.countByStatus("ERROR");

        assertEquals(2, runningCount);
        assertEquals(1, standbyCount);
        assertEquals(1, stoppedCount);
        assertEquals(1, errorCount);

        System.out.println("ステータス別件数取得テスト成功");
        System.out.println("稼働中：" + runningCount);
        System.out.println("待機中：" + standbyCount);
        System.out.println("停止中：" + stoppedCount);
        System.out.println("異常：" + errorCount);
    }

    /**
     * 更新日時順の装置情報取得テスト
     */
    @Test
    public void testFindRecent() {

        List<Equipment> equipmentList =
                equipmentRepository.findRecent(3);

        assertNotNull(equipmentList);
        assertEquals(3, equipmentList.size());

        for (Equipment equipment : equipmentList) {
            assertNotNull(equipment.getEquipmentId());
            assertNotNull(equipment.getEquipmentName());
            assertNotNull(equipment.getUpdatedAt());
        }

        System.out.println("最近更新された装置情報取得テスト成功");
        System.out.println("取得件数：" + equipmentList.size());
    }

    /**
     * 不正な取得件数の指定テスト
     */
    @Test(expected = IllegalArgumentException.class)
    public void testFindRecentWithInvalidLimit() {

        equipmentRepository.findRecent(0);
    }
}
