package org.github.poo0054.flow.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.EnableWebFlux;

/**
 * @author zhangzhi
 */
@EnableWebFlux
@Configuration(proxyBeanMethods = false)
public class FlowConfig {
}
