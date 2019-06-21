package com.sysuser.oauth2.logout;

import static com.sysuser.oauth2.response.HttpResponse.baseResponse;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import com.sysuser.utils.HttpUtils;

public class MyLogoutSuccessHandler implements LogoutSuccessHandler{

	
	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		String loginOutUrl=request.getParameter("loginOutUrl");
		if (StringUtils.isBlank(loginOutUrl)) {
			response.setContentType("application/json;charset=UTF-8");
			HttpUtils.writerError(baseResponse(200,"注销成功"), response);
		} else {
			response.sendRedirect(loginOutUrl);
		}
	}

}
