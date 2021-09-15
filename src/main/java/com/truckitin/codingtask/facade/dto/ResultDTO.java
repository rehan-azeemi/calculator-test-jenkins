package com.truckitin.codingtask.facade.dto;

import com.truckitin.codingtask.model.Result;

import java.time.ZoneOffset;

public class ResultDTO {
    private String postFix;
    private Integer result;

    public String getPostFix() {
        return postFix;
    }

    public void setPostFix(String postFix) {
        this.postFix = postFix;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public static ResultDTO fromResult(final Result r) {
        final ResultDTO dto = new ResultDTO();
        dto.setPostFix(r.getPostFix());
        dto.setResult(r.getResult());
        return dto;
    }
}
