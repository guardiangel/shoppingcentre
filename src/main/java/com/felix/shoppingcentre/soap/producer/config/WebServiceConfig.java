package com.felix.shoppingcentre.soap.producer.config;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.ws.wsdl.wsdl11.Wsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

/**
 * Spring WS uses MessageDispatcherServlet to process SOAP messages,
 * so creating a Web Service configuration requires creating a new instance of MessageDispatcherServlet and injecting the application context ApplicationContext into the instance.
 * The MessageDispatcherServlet instance named messageDispatcherServlet does not replace the Spring Boot default DispatcherServlet bean.
 * DefaultWsdl11Definition exposes standard WSDL 1.1 using XSD Schema.
 * Note: The MessageDispatcherServlet and DefaultWsdl11Definition instances must be given names,
 * which determine the WSDL URL. In this example, the MessageDispatcherServlet instance name is ws,
 * and the DefaultWsdl11Definition instance name is user, so the WSDL URL is http://<host>:<port>/ws/user.wsdl.
 */
@EnableWs
@Configuration
public class WebServiceConfig {

    @Bean
    public ServletRegistrationBean messageDispatcherServlet(ApplicationContext applicationContext) {
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(applicationContext);
        servlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean(servlet, "/ws/*");
    }

    @Bean(name = "user")
    public Wsdl11Definition defaultWsdl11Definition(XsdSchema schema) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("UserPort");
        wsdl11Definition.setLocationUri("/ws");
        wsdl11Definition.setTargetNamespace("http://tutorial.spring.boot/soap/produce/user");
        wsdl11Definition.setSchema(schema);
        return wsdl11Definition;
    }

    @Bean
    public XsdSchema userSchema() {
        return new SimpleXsdSchema(new ClassPathResource("xsd/user.xsd"));
    }
}
