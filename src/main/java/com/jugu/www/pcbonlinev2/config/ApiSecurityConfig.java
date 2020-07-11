package com.jugu.www.pcbonlinev2.config;

import com.jugu.www.pcbonlinev2.filter.JwtTokenFilter;
import com.jugu.www.pcbonlinev2.utils.SHA256Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Slf4j
@Configuration
@EnableWebSecurity
public class ApiSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

        //校验用户
        auth.userDetailsService( userDetailsService ).passwordEncoder( new PasswordEncoder() {
            //对密码进行加密
            @Override
            public String encode(CharSequence charSequence) {
                log.info(charSequence.toString());
//                return DigestUtils.md5DigestAsHex(charSequence.toString().getBytes());
                return SHA256Util.getSHA256StrJava(charSequence.toString()+"password");
            }
            //对密码进行判断匹配
            @Override
            public boolean matches(CharSequence charSequence, String s) {
                log.info("charSequence:", charSequence.toString());
                //String encode = DigestUtils.md5DigestAsHex(charSequence.toString().getBytes());
                String encode = SHA256Util.getSHA256StrJava(charSequence.toString()+"password");
                return s.equals( encode );
            }
        } );

    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .antMatcher("/api/**")
//                .httpBasic().and()
//                .csrf().disable()
//                .authorizeRequests()
////                .antMatchers(HttpMethod.POST,"/api/login").permitAll()
//                .anyRequest().authenticated();
        http.csrf().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                //OPTIONS请求全部放行
                .antMatchers(HttpMethod.OPTIONS,"/**").permitAll()
                ////登录接口放行
                .antMatchers("/api/auth/login").permitAll()
                //其他接口全部接受验证
                .anyRequest().authenticated();

        //使用自定义的 Token过滤器 验证请求的Token是否合法
        http.addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);
        http.headers().cacheControl();
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


}
