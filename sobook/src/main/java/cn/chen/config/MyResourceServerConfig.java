package cn.chen.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
@EnableResourceServer
public class MyResourceServerConfig extends ResourceServerConfigurerAdapter {
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.requestMatchers().antMatchers("/api/**")
                .and()
                .authorizeRequests()
                .antMatchers("/api/adm/**").hasAuthority("G")
                .antMatchers("/api/fore/**").hasAuthority("P")
                .antMatchers("/api/public/**").permitAll()
                .antMatchers("/api/info/**").permitAll()
                .antMatchers("/oauth/token/**").permitAll()
                .anyRequest().authenticated();
    }
}
