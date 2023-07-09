package t3h.project.java.shop.CustomerSecurity.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;

public class CustomerSecurityConfig {



    @Bean
    public AuthenticationManager authenticationManager1(AuthenticationConfiguration configuration) throws Exception{
        return configuration.getAuthenticationManager();

    }




}
