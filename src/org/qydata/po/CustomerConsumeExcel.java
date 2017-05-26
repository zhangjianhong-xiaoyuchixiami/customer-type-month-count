package org.qydata.po;

import java.io.Serializable;

/**
 * Created by jonhn on 2017/5/26.
 */
public class CustomerConsumeExcel implements Serializable {

    private Integer id;
    private byte [] excelCode;
    private Integer customerId;
    private String consuTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public byte[] getExcelCode() {
        return excelCode;
    }

    public void setExcelCode(byte[] excelCode) {
        this.excelCode = excelCode;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getConsuTime() {
        return consuTime;
    }

    public void setConsuTime(String consuTime) {
        this.consuTime = consuTime;
    }
}
