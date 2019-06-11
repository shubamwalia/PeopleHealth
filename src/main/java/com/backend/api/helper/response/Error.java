package com.backend.api.helper.response;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

public class Error {

    private Integer status;
    private List<Map<String, Object>> error;
    private String timestamp;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<Map<String, Object>> getError() {
        return error;
    }

    public void setError(List<Map<String, Object>> error) {
        this.error = error;
    }

    public String getTimestamp() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-M-yyyy hh:mm:ss a");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Kolkata"));
        Date dateobj = new Date();
        this.timestamp = simpleDateFormat.format(dateobj);
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
