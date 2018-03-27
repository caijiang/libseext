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

}