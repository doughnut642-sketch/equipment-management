package com.example.app.config;

import org.springframework.web.servlet.support
        .AbstractAnnotationConfigDispatcherServletInitializer;

public class WebAppInitializer
        extends AbstractAnnotationConfigDispatcherServletInitializer {

    // ルートアプリケーションの設定
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return null;
    }

    // Spring MVCの設定クラス
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[] {
            WebMvcConfig.class
        };
    }

    // DispatcherServletが処理するURL
    @Override
    protected String[] getServletMappings() {
        return new String[] { "/" };
    }
}