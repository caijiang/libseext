package me.jiangcai.lib.ee;

import javax.servlet.http.HttpServletRequest;
import java.util.stream.Stream;

/**
 * @author CJ
 * @since 1.3.5
 */
public class ServletUtils {

    /**
     * @param httpRequest 请求
     * @return 当前请求所在的contextUrl Builder；绝不是/结尾的
     * @since 1.3.8
     */
    public static StringBuilder buildContextUrl(HttpServletRequest httpRequest) {
        StringBuilder sb = new StringBuilder();
        sb.append(httpRequest.getScheme());
        sb.append("://");
        sb.append(httpRequest.getServerName());
        // 负数 或者433 或者80 就省略
        if (httpRequest.getServerPort() <= 0
                || (httpRequest.isSecure() && httpRequest.getServerPort() == 433)
                || (!httpRequest.isSecure() && httpRequest.getServerPort() == 80)
                )
            ;
        else {
            sb.append(":").append(httpRequest.getServerPort());
        }
        sb.append(httpRequest.getContextPath());
        return sb;
    }

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
        // TODO: 如何选择相信，请求X-Real-IP 必须跟最后一位保持一致。
        // 125.119.83.76, 10.255.0.2
        // x-real-ip 10.255.0.2
        String xff = request.getHeader("X-Forwarded-For");
        if (xff != null && xff.length() > 0) {
            String[] ips = xff.trim().split(",");
            // 应该确保最后一个 等同于 request.getRemoteAddr();
            return ips[0].trim();
        }
        return request.getRemoteAddr();
    }

    /**
     * @param ipAddress ip地址
     * @return 是否为私有ip地址，或者说内网地址
     * @since 1.4.1
     */
    public static boolean isPrivateIPAddress(String ipAddress) {
        if ("localhost".equals(ipAddress))
            return true;
        if ("127.0.0.1".equals(ipAddress))
            return true;
        Integer[] i = Stream.of(ipAddress.split("\\."))
                .map(Integer::parseInt)
                .toArray(Integer[]::new);
        if (i[0] == 10)
            return true;
        else if (i[0] == 172) {
            return i[1] <= 31 && i[1] >= 16;
        } else return i[0] == 192 && i[1] == 168;
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
