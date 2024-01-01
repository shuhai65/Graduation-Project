package edu.scau.common.utils;

import lombok.extern.slf4j.Slf4j;

import java.util.Date;

@Slf4j
public class CheckTimeExpiresUtil {
    public static boolean isExpired(String expire) {
        Date now = new Date();
        Date expiration = new Date();
        expiration.setTime(Long.parseLong(expire));
        log.info(now.toString());
        log.info(expiration.toString());
        return now.compareTo(expiration) >= 0;
    }
}
