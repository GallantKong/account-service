//package org.gallant.account.config;
//
//import org.eclipse.jetty.server.LowResourceMonitor;
//import org.eclipse.jetty.server.LowResourceMonitor.LowResourceCheck;
//import org.eclipse.jetty.server.Server;
//import org.springframework.boot.web.embedded.jetty.JettyServerCustomizer;
//
///**
// * @author gallant
// */
//public class MyJettyServerCustomizer implements JettyServerCustomizer {
//
//  @Override
//  public void customize(Server server) {
//    LowResourceMonitor lowResourceMonitor = new LowResourceMonitor(server);
//    lowResourceMonitor.setAcceptingInLowResources(false);
//    lowResourceMonitor.setMaxLowResourcesTime(1);
//    LowResourceCheck lowResourceCheck = lowResourceMonitor.new MainThreadPoolLowResourceCheck();
//    lowResourceMonitor.addLowResourceCheck(lowResourceCheck);
//    server.addBean(lowResourceMonitor);
//  }
//}
