package com.sysuser.oauth2.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author lijinsheng
 * @date   2018/3/30 20:37
 *
 */
@Getter
@Setter
public class ListResponse extends BaseResponse {

    private long count;
    private List items;

    protected ListResponse(){

    }

}
