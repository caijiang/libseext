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

    /**
     * 根据当前请求获取基于context的URLBuilder
     * 直接加入/开头的 uri即可形成完整路径
     *
     * @param request 请求
     * @return 比如 https://www.a.com http://www.b.com:8080/app
     */
    public static StringBuilder contextUrlBuilder(HttpServletRequest request) {
        StringBuilder urlBuilder = new StringBuilder();
        urlBuilder.append(request.getScheme()).append("://");
        urlBuilder.append(request.getServerName()); // localName?
        if (request.getServerPort() > 0
                // 而且不是http 80
                && !(!request.isSecure() && request.getServerPort() == 80)
                // 而且也不是443+https
                && !(request.isSecure() && request.getServerPort() == 443)
                )
            urlBuilder.append(":").append(request.getServerPort());

        urlBuilder.append(request.getContextPath());
        return urlBuilder;
    }

}
