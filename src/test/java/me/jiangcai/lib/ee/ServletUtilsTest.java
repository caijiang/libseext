package me.jiangcai.lib.ee;

import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author CJ
 */
public class ServletUtilsTest {
    @Test
    public void contextUrlBuilder() throws Exception {
        MockHttpServletRequest request = createMockHttpServletRequest();
        request.setServerPort(80);
        request.setSecure(false);
        assertThat(ServletUtils.contextUrlBuilder(request).toString())
                .isEqualTo("http://www.baidu.com");

        request.setServerPort(81);
        request.setSecure(false);
        assertThat(ServletUtils.contextUrlBuilder(request).toString())
                .isEqualTo("http://www.baidu.com:81");

        request.setServerPort(443);
        request.setSecure(false);
        assertThat(ServletUtils.contextUrlBuilder(request).toString())
                .isEqualTo("http://www.baidu.com:443");

        request.setServerPort(443);
        request.setSecure(true);
        request.setScheme("https");
        assertThat(ServletUtils.contextUrlBuilder(request).toString())
                .isEqualTo("https://www.baidu.com");

        request.setServerPort(449);
        request.setSecure(true);
        request.setScheme("https");
        assertThat(ServletUtils.contextUrlBuilder(request).toString())
                .isEqualTo("https://www.baidu.com:449");

    }

    private MockHttpServletRequest createMockHttpServletRequest() {
        final MockHttpServletRequest request = new MockHttpServletRequest();
        request.setServerName("www.baidu.com");
        request.setContextPath("");
        request.addHeader("X-Forwarded-For", "125.119.83.76, 10.255.0.2");
        return request;
    }

    @Test
    public void clientIpAddress() {
        MockHttpServletRequest request = createMockHttpServletRequest();
        request.addHeader("X-Forwarded-For", "125.119.83.76, 10.255.0.2");
        assertThat(ServletUtils.clientIpAddress(request))
                .isEqualTo("125.119.83.76");
    }

    @Test
    public void isPrivateIPAddress() {
        assertThat(
                ServletUtils.isPrivateIPAddress("127.0.0.1")
        )
                .isTrue();

        assertThat(
                ServletUtils.isPrivateIPAddress("127.0.0.2")
        )
                .isFalse();

        assertThat(
                ServletUtils.isPrivateIPAddress("80.0.0.2")
        )
                .isFalse();

        assertThat(
                ServletUtils.isPrivateIPAddress("10.0.0.2")
        )
                .isTrue();


        assertThat(
                ServletUtils.isPrivateIPAddress("172.15.0.2")
        )
                .isFalse();
        assertThat(
                ServletUtils.isPrivateIPAddress("172.16.0.2")
        )
                .isTrue();
        assertThat(
                ServletUtils.isPrivateIPAddress("172.31.0.2")
        )
                .isTrue();
        assertThat(
                ServletUtils.isPrivateIPAddress("172.32.0.2")
        )
                .isFalse();

        assertThat(
                ServletUtils.isPrivateIPAddress("192.167.0.2")
        )
                .isFalse();

        assertThat(
                ServletUtils.isPrivateIPAddress("192.168.0.2")
        )
                .isTrue();

    }

}