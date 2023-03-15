package org.gallant.account.config;

//import org.eclipse.jetty.util.BlockingArrayQueue;
//import org.eclipse.jetty.util.thread.QueuedThreadPool;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.embedded.jetty.JettyServletWebServerFactory;
import org.springframework.boot.web.server.Compression;
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

//    @Bean
//    public ServletWebServerFactory servletContainer() {
//        JettyServletWebServerFactory jetty = new JettyServletWebServerFactory();
//        QueuedThreadPool tp = new QueuedThreadPool(200,8, 60000, new BlockingArrayQueue<>(1));
//        jetty.setThreadPool(tp);
//        jetty.setPort(Integer.parseInt(serverPort.trim()));
//        MyJettyServerCustomizer myJettyServerCustomizer = new MyJettyServerCustomizer();
//        jetty.addServerCustomizers(myJettyServerCustomizer);
//        Compression compression = new Compression();
//        compression.setEnabled(true);
//        // 注意不生效，会被org.springframework.boot.autoconfigure.web.servlet.ServletWebServerFactoryCustomizer#customize重写
//        jetty.setCompression(compression);
//        jetty.setServerHeader("my-server-header-value");
//        return jetty;
//    }
}