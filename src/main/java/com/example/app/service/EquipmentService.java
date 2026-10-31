
package com.example.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.app.model.Equipment;
import com.example.app.repository.EquipmentRepository;

/**
 * 装置情報サービス
 *
 * EquipmentRepositoryを利用して装置情報を取得し、
 * ダッシュボードなどの画面で利用するデータを提供する。
 */
@Service
public class EquipmentService {

    // 装置情報リポジトリ
    private final EquipmentRepository equipmentRepository;

    // 装置ステータス
    private static final String STATUS_RUNNING = "RUNNING";
    private static final String STATUS_STANDBY = "STANDBY";
    private static final String STATUS_STOPPED = "STOPPED";
    private static final String STATUS_ERROR = "ERROR";

    /**
     * コンストラクタ
     *
     * SpringのDIによってEquipmentRepositoryを受け取る。
     */
    @Autowired
    public EquipmentService(EquipmentRepository equipmentRepository) {
        this.equipmentRepository = equipmentRepository;
    }

    /**
     * 全装置情報を取得する。
     *
     * @return 全装置情報のリスト
     */
    public List<Equipment> getAllEquipment() {

        return equipmentRepository.findAll();
    }

    /**
     * ステータスを指定して装置情報を取得する。
     *
     * @param status 装置ステータス
     * @return 該当する装置情報のリスト
     */
    public List<Equipment> getEquipmentByStatus(String status) {

        return equipmentRepository.findByStatus(status);
    }

    /**
     * 装置総数を取得する。
     *
     * @return 装置総数
     */
    public int getTotalCount() {

        return equipmentRepository.countAll();
    }

    /**
     * 稼働中の装置数を取得する。
     *
     * @return 稼働中の装置数
     */
    public int getRunningCount() {

        return equipmentRepository.countByStatus(STATUS_RUNNING);
    }

    /**
     * 待機中の装置数を取得する。
     *
     * @return 待機中の装置数
     */
    public int getStandbyCount() {

        return equipmentRepository.countByStatus(STATUS_STANDBY);
    }

    /**
     * 停止中の装置数を取得する。
     *
     * @return 停止中の装置数
     */
    public int getStoppedCount() {

        return equipmentRepository.countByStatus(STATUS_STOPPED);
    }

    /**
     * 異常が発生している装置数を取得する。
     *
     * @return 異常装置数
     */
    public int getErrorCount() {

        return equipmentRepository.countByStatus(STATUS_ERROR);
    }

    /**
     * 最近更新された装置情報を取得する。
     *
     * @param limit 取得件数
     * @return 最近更新された装置情報のリスト
     */
    public List<Equipment> getRecentEquipment(int limit) {

        return equipmentRepository.findRecent(limit);
    }
}
