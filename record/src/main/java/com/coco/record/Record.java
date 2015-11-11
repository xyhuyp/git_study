package com.coco.record;

import java.io.Serializable;

/**
 * Created by huyanpeng on 15-11-10.
 */
public class Record implements Serializable{

    private String localDate;

    private String tutor;

    private String remarks;

    public String getLocalDate() {
        return localDate;
    }

    public void setLocalDate(String localDate) {
        this.localDate = localDate;
    }

    public String getTutor() {
        return tutor;
    }

    public void setTutor(String tutor) {
        this.tutor = tutor;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
