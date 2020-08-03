package com.jugu.www.pcbonlinev2.config;

import com.jugu.www.pcbonlinev2.filter.JwtTokenFilter;
import com.jugu.www.pcbonlinev2.state.core.Initialization;
import com.jugu.www.pcbonlinev2.state.core.OrderStateManager;
import com.jugu.www.pcbonlinev2.utils.RestAccessDeniedHandler;
import com.jugu.www.pcbonlinev2.utils.RestAuthenticationEntryPoint;
import com.jugu.www.pcbonlinev2.utils.SHA256Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Slf4j
@Configuration
@EnableWebSecurity
public class ApiSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    private static final String PASSWORD_KEY = "password";

    private static final String[] AUTH_WHITELIST = {
            // -- swagger ui druid ui
            "/swagger-resources/**",
            "/swagger-ui.html",
            "/v2/api-docs",
            "/webjars/**",
            "/druid/**",
    };

    private static final String[] STATIC_WHITELIST = {
            "/webjars/**/*.css",
            "/webjars/**/*.js",
            "/webjars/**/*.jpg",
            "/webjars/**/*.png",
            "/druid/**/*.js",
            "/druid/**/*.css",
            "/druid/**/*.jpg",
            "/druid/**/*.png",
            "/druid/**/*.ico",
    };

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

        //校验用户
        auth.userDetailsService(userDetailsService).passwordEncoder(new PasswordEncoder() {
            //对密码进行加密
            @Override
            public String encode(CharSequence charSequence) {
                log.info(charSequence.toString());
                return SHA256Util.getSHA256StrJava(charSequence.toString() + PASSWORD_KEY);
            }

            //对密码进行判断匹配
            @Override
            public boolean matches(CharSequence charSequence, String s) {
                log.info("charSequence:", charSequence.toString());
                String encode = SHA256Util.getSHA256StrJava(charSequence.toString() + PASSWORD_KEY);
                return s.equals(encode);
            }
        });

    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(AUTH_WHITELIST).permitAll()
                //OPTIONS请求全部放行
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                ////登录接口放行
                .antMatchers("/api/auth/login", "/api/auth/register").permitAll()
                //其他接口全部接受验证
                .anyRequest().authenticated();

        //使用自定义的 Token过滤器 验证请求的Token是否合法
        http.addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);

        //添加自定义的异常处理
        http.exceptionHandling()
                .accessDeniedHandler(accessDeniedHandlerBean())
                .authenticationEntryPoint(unauthorizedHandlerBean());
        http.headers().cacheControl();
    }

    @Bean
    public AuthenticationEntryPoint unauthorizedHandlerBean() {
        return new RestAccessDeniedHandler();
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandlerBean() {
        return new RestAuthenticationEntryPoint();
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers(STATIC_WHITELIST);
    }

    @Bean
    public JwtTokenFilter authenticationTokenFilterBean() {
        return new JwtTokenFilter();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public Initialization initialization() {
        return new Initialization();
    }

    @Bean
    public OrderStateManager orderStateManager() {
        return new OrderStateManager();
    }


}
