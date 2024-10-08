package com.asaas.hackaton.util;

import com.asaas.hackaton.exception.BusinessException;
import jakarta.servlet.http.HttpServletRequest;

public class UserIpUtils {

    public static String getUserIp(HttpServletRequest request) {
        final String headerIp = "remote-ip";

        String ip = request.getHeader(headerIp);

        if(ip == null) {
            throw new BusinessException("ip_error","User Ip Not Found");
        }

        return ip;
    }
}
