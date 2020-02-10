package com.nenadm.JwtProject.Filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfiguration {


    @Autowired
    private CustomFilter customFilter;

    @Bean
    public FilterRegistrationBean< CustomFilter > filterRegistrationBean() {
        FilterRegistrationBean< CustomFilter > filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(customFilter);
        filterRegistrationBean.addUrlPatterns("/api/*");
        return filterRegistrationBean;
    }
}
