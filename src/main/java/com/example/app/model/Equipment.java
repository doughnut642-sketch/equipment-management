package com.example.app.model;

/**
 * 半導体製造装置の情報を保持するモデルクラス
 */
public class Equipment {

    /** 装置ID */
    private String equipmentId;

    /** 装置名 */
    private String equipmentName;

    /** 装置種別 */
    private String equipmentType;

    /** 設置エリア */
    private String area;

    /** 装置ステータス */
    private String status;

    /** メーカー */
    private String manufacturer;

    /** 型式 */
    private String modelNumber;

    /** 導入日 */
    private String installedAt;

    /** 最終メンテナンス日 */
    private String lastMaintenanceAt;

    /** 登録日時 */
    private String createdAt;

    /** 更新日時 */
    private String updatedAt;


    /**
     * コンストラクタ
     */
    public Equipment() {
    }


    // equipmentId

    public String getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(String equipmentId) {
        this.equipmentId = equipmentId;
    }


    // equipmentName

    public String getEquipmentName() {
        return equipmentName;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }


    // equipmentType

    public String getEquipmentType() {
        return equipmentType;
    }

    public void setEquipmentType(String equipmentType) {
        this.equipmentType = equipmentType;
    }


    // area

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }


    // status

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    // manufacturer

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }


    // modelNumber

    public String getModelNumber() {
        return modelNumber;
    }

    public void setModelNumber(String modelNumber) {
        this.modelNumber = modelNumber;
    }


    // installedAt

    public String getInstalledAt() {
        return installedAt;
    }

    public void setInstalledAt(String installedAt) {
        this.installedAt = installedAt;
    }


    // lastMaintenanceAt

    public String getLastMaintenanceAt() {
        return lastMaintenanceAt;
    }

    public void setLastMaintenanceAt(String lastMaintenanceAt) {
        this.lastMaintenanceAt = lastMaintenanceAt;
    }


    // createdAt

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }


    // updatedAt

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }
}