package me.jiangcai.lib.ee;

import javax.servlet.http.HttpServletRequest;

/**
 * @author CJ
 * @since 1.3.5
 */
public class ServletUtils {

    /**
     * 从请求中获取客户端IP地址
     * https://zh.wikipedia.org/wiki/X-Forwarded-For
     *
     * @param request 请求
     * @return ip
     */
    public static String clientIpAddress(HttpServletRequest request) {
// X-Forwarded-For: client1, proxy1, proxy2
        // 暂时表示信任这个来源
        String xff = request.getHeader("X-Forwarded-For");
        if (xff != null && xff.length() > 0) {
            String[] ips = xff.trim().split(",");
            // 应该确保最后一个 等同于 request.getRemoteAddr();
            return ips[0].trim();
        }
        return request.getRemoteAddr();
    }

}
