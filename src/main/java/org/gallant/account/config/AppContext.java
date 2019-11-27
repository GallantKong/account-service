package org.gallant.account.config;

import java.util.EnumSet;
import javax.servlet.DispatcherType;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.jetty.JettyEmbeddedServletContainerFactory;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.filter.CharacterEncodingFilter;

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
    public FilterRegistrationBean characterEncodingFilter() {
        FilterRegistrationBean filterRegistration = new FilterRegistrationBean();
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceEncoding(true);
        filterRegistration.setFilter(characterEncodingFilter);
        filterRegistration.setDispatcherTypes(EnumSet
                .of(DispatcherType.FORWARD, DispatcherType.REQUEST, DispatcherType.INCLUDE));
        filterRegistration.addUrlPatterns("/*");
        return filterRegistration;
    }

    @Bean
    public RequestContextListener requestContextListener() {
        return new RequestContextListener();
    }

    @Bean
    public EmbeddedServletContainerFactory servletContainer() {
        JettyEmbeddedServletContainerFactory jetty =
                new JettyEmbeddedServletContainerFactory();
        jetty.setPort(Integer.parseInt(serverPort.trim()));
        return jetty;
    }

}