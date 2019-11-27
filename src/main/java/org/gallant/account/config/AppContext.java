package org.gallant.account.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.embedded.jetty.JettyServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.context.request.RequestContextListener;

/**
 * @author : 会灰翔的灰机
 * @date : 2019/11/30
 */
@Configuration
@ImportResource(locations = { "classpath:spring/*" })
@PropertySource("classpath:config/datasource.properties")
@ComponentScan(basePackages = "org.gallant.account")
@MapperScan("org.gallant.account.mapper")
@EnableTransactionManagement
public class AppContext {

    @Value("${server.port:8000}")
    private String serverPort;

    @Bean
    public RequestContextListener requestContextListener() {
        return new RequestContextListener();
    }

    @Bean
    public ServletWebServerFactory servletContainer() {
        JettyServletWebServerFactory jetty = new JettyServletWebServerFactory();
        jetty.setPort(Integer.parseInt(serverPort.trim()));
        return jetty;
    }

}