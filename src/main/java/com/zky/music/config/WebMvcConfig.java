package com.zky.music.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 解决跨域问题
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("*")
                .allowCredentials(true);
    }
}
/**
 * //高版本的SpringBoot跨域配置 @Configuration
 * public class WebMvcConfig implements WebMvcConfigurer {
 *
 *         @Override
 *         public void addCorsMappings(CorsRegistry registry) {
 *             // 设置允许跨域的路由
 *             registry.addMapping("/**")
 *                     // 设置允许跨域请求的域名
 *                     .allowedOriginPatterns("*")
 *                     // 是否允许证书（cookies）
 *                     .allowCredentials(true)
 *                     // 设置允许的方法
 *                     .allowedMethods("*")
 *                     // 跨域允许时间
 *                     .maxAge(3600);
 *         }
 * }
 */