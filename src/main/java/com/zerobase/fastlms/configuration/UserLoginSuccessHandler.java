package com.zerobase.fastlms.configuration;

import com.zerobase.fastlms.history.entity.History;
import com.zerobase.fastlms.history.service.HistoryService;
import com.zerobase.fastlms.member.service.MemberService;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;


public class UserLoginSuccessHandler implements AuthenticationSuccessHandler {

    @Resource
    private HistoryService loginService;

    @Resource
    private MemberService memberService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
    HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        String clientIp = request.getRemoteAddr();
        String userAgent = request.getHeader("user-agent");
        String username = request.getParameter("username");

        History history = History.builder()
                .ip(clientIp)
                .loginDt(LocalDateTime.now())
                .loginId(username)
                .userAgent(userAgent)
                .build();
        loginService.saveLoginHistory(history);
        memberService.updateLastLogin(username);


        response.sendRedirect("/");
    }
}
