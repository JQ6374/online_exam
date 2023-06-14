package com.zz.filter;

import com.zz.bean.User;
import com.zz.utils.Code;
import com.zz.utils.JwtTokenUtil;
import com.zz.utils.result.ApiResult;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    @Override
    protected void doFilterInternal(@NotNull HttpServletRequest request,
                                    @NotNull HttpServletResponse response,
                                    @NotNull FilterChain filterChain) throws ServletException, IOException {
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        response.setCharacterEncoding("UTF-8");
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        String token = request.getHeader("token");
        if (!StringUtils.hasText(token)) {
            String[] doFilterUrls = {"/api/user/login", "/api/user/register",
                    "/api/auth/send_email", "/api/user/updatePassword"};
            for (String doFilterUrl : doFilterUrls) {
                if (request.getRequestURI().contains(doFilterUrl)) {
                    filterChain.doFilter(request, response);
                    return;
                }
            }
            response.getWriter().write(new ApiResult<>(Code.TOKEN_EMPTY_ERROR, null, "令牌为空!").toString());
            return;
        }
        String userId;
        try {
            userId = jwtTokenUtil.parserToken(token);
        } catch (ExpiredJwtException e) {
            response.getWriter().write(new ApiResult<>(Code.TOKEN_TIMEOUT_ERROR, null, "登录会话过期，请重新登录!").toString());
            return;
        } catch (Exception e) {
            response.getWriter().write(new ApiResult<>(Code.TOKEN_VALIDATE_ERROR, null, "非法令牌!").toString());
            return;
        }

        String redisKey = "userId:" + userId;
        User loginUser = (User) redisTemplate.opsForValue().get(redisKey);
        if (Objects.isNull(loginUser)) {
            response.getWriter().write(new ApiResult<>(Code.TOKEN_VALIDATE_ERROR, null, "非法令牌!").toString());
        } else {
            filterChain.doFilter(request, response);
        }
    }
}
