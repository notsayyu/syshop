package com.dsy.syshop.core.common.filter;

import com.dsy.syshop.core.common.constant.RegexPattern;
import com.dsy.syshop.core.web.response.BaseResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Charsets;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @description: 入参emoji表情统一拦截
 * @author: dsy
 * @date: 2019/11/15 11:40
 */
@Component
public class EmojiFilter extends OncePerRequestFilter {
    private static final Pattern EMOJI_PATTERN = Pattern.compile(RegexPattern.EMOJI_REGEX, Pattern.UNICODE_CASE | Pattern.CASE_INSENSITIVE);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        HttpServletRequest req = request;
        StringBuilder content = new StringBuilder();
        if (HttpMethod.POST.name().equalsIgnoreCase(request.getMethod())
                || HttpMethod.PATCH.name().equalsIgnoreCase(request.getMethod())
                || HttpMethod.PUT.name().equalsIgnoreCase(request.getMethod())) {
            MultiReadHttpServletRequestWrapper requestWrapper = new MultiReadHttpServletRequestWrapper(request);
            content.append(new String(requestWrapper.getBody()));

            req = requestWrapper;
        }

        if (StringUtils.isNotBlank(req.getQueryString()) && HttpMethod.DELETE.name().equalsIgnoreCase(request.getMethod())) {
            List<NameValuePair> list = URLEncodedUtils.parse(req.getQueryString(), Charsets.UTF_8);
            content.append(list.stream().map(p -> p.getName() + p.getValue()).collect(Collectors.joining()));
        }
        if (EMOJI_PATTERN.matcher(content).find()) {
            ObjectMapper mapper = new ObjectMapper();
            String body = mapper.writeValueAsString(BaseResponse.with(HttpStatus.BAD_REQUEST.value(), "不支持 emoji"));
            response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            response.getWriter().println(body);
            return;
        }
        filterChain.doFilter(req, response);
    }
}
