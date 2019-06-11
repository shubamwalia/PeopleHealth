package com.backend.api.helper.response;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.TimeZone;

public class Success {

    private Integer status;
    private Map<String, Object> payload;
    private String timestamp;

    public Success() {
    }

    public Success(Map<String, Object> payload) {
        this.payload = payload;
    }

    public String getTimestamp() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-M-yyyy hh:mm:ss a");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Kolkata"));
        Date dateobject = new Date();

        this.timestamp = simpleDateFormat.format(dateobject);
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Map<String, Object> getPayload() {
        return payload;
    }

    public void setPayload(Map<String, Object> payload) {
        this.payload = payload;
    }
}
