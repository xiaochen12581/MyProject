package cn.chen.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;

@Configuration
@EnableAuthorizationServer
public class QqAuth2ServerConfig extends AuthorizationServerConfigurerAdapter {
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    UserDetailsService userDetailsService;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) {
        System.out.println("checkTokenAccess(\"permitAll()\")");
        oauthServer.tokenKeyAccess("permitAll()")
                .checkTokenAccess("permitAll()") //allow check token
                .allowFormAuthenticationForClients();//允许表单访问
    }

    // 配置哪些 client_id client_secret
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        System.out.println("configure(ClientDetailsServiceConfigurer clients)");
        clients.inMemory()
                .withClient("youdao").secret(passwordEncoder.encode("223344"))
                .authorizedGrantTypes("password", "authorization_code",
                        "client_credentials", "implicit", "refresh_token")
                .scopes("all")
                .autoApprove(true)
                .redirectUris("http://127.0.0.1:8080/#/fore/home/home")    // 认证成功重定向URL
                .and()
                .withClient("sobook").secret(passwordEncoder.encode("223344"))
                .authorizedGrantTypes("password", "authorization_code",
                        "client_credentials", "implicit", "refresh_token")
                .scopes("all")
                .autoApprove(true)
                .redirectUris("http://127.0.0.1:8080/#/fore/home/home");

    }
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        System.out.println("configure(AuthorizationServerEndpointsConfigurer endpoints)");
        endpoints.authenticationManager(authenticationManager)
                .userDetailsService(userDetailsService);//用户来自于哪里
    }
}
