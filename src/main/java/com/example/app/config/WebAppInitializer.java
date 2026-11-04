package com.example.app.config;

import org.springframework.web.servlet.support
        .AbstractAnnotationConfigDispatcherServletInitializer;

public class WebAppInitializer
        extends AbstractAnnotationConfigDispatcherServletInitializer {

    // ルートアプリケーションの設定
    @Override
    protected Class<?>[] getRootConfigClasses() {

        System.out.println("[DEBUG] WebAppInitializer: getRootConfigClasses() 開始");

        System.out.println("[DEBUG] WebAppInitializer: RootConfig = null");

        return null;
    }

    // Spring MVCの設定クラス
    @Override
    protected Class<?>[] getServletConfigClasses() {

        System.out.println("[DEBUG] WebAppInitializer: getServletConfigClasses() 開始");

        Class<?>[] configClasses = new Class<?>[] {
            WebMvcConfig.class
        };

        System.out.println("[DEBUG] WebAppInitializer: ServletConfig = WebMvcConfig.class");

        return configClasses;
    }

    // DispatcherServletが処理するURL
    @Override
    protected String[] getServletMappings() {

        System.out.println("[DEBUG] WebAppInitializer: getServletMappings() 開始");

        String[] mappings = new String[] { "/" };

        System.out.println("[DEBUG] WebAppInitializer: ServletMapping = /");

        return mappings;
    }
}