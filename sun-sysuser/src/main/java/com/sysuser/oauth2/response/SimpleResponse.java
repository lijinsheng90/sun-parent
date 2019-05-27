package com.sysuser.oauth2.response;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @author lijinsheng
 * @date  2018/3/30 20:37
 *
 */
@Getter
@Setter
public class SimpleResponse extends BaseResponse {

    private Object item;

    protected SimpleResponse() {
    }

    protected SimpleResponse(int status, String msg, Object item) {
        super(status, msg);
        this.item = item;
    }

}
