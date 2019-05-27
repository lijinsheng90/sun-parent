package com.sysuser.oauth2.exception;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.provider.error.OAuth2AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import com.sysuser.oauth2.response.HttpResponse;
import com.sysuser.utils.HttpUtils;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author lijinsheng
 * @date 2018/11/2 10:48
 *自定义OAuth2AuthenticationEntryPoint 该接口处理Token相关的异常，处理类如下
 */
@Component
public class MyOAuth2AuthExceptionEntryPoint extends OAuth2AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        HttpUtils.writerError(HttpResponse.baseResponse(HttpStatus.UNAUTHORIZED.value(),e.getMessage()),response);
    }

}
