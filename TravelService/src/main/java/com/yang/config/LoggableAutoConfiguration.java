package com.yang.config;


import jdk.nashorn.internal.runtime.logging.Loggable;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

//@Configuration
//@EnableAspectJAutoProxy
//@ComponentScan(basePackages = "com.example.myservice.aspect")
//public class LoggableAutoConfiguration {
//    // ...
//}
@Configuration
@EnableAspectJAutoProxy
@ComponentScan(basePackages = {"com.mbs.mclient.aspect","com.mbs.mclient.core"})
@ConditionalOnClass(Loggable.class)
public class LoggableAutoConfiguration {

    // ...
}