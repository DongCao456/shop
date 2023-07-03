package t3h.project.java.shop.CustomerSecurity.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import t3h.project.java.shop.CustomerSecurity.Service.CustomerDetailsServiceImpl;

@Configuration
@Order(1)
public class CustomerSecurityConfig {

    @Bean
    public UserDetailsService userDetailsService1(){
        return new CustomerDetailsServiceImpl();
    }

    @Bean
    public PasswordEncoder getPasswordEncoder1(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider1(){
        DaoAuthenticationProvider authenticationProvider=new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService1());
        authenticationProvider.setPasswordEncoder(getPasswordEncoder1());
        return authenticationProvider;
    }

//    @Bean
//    public AuthenticationManager authenticationManager1(AuthenticationConfiguration configuration) throws Exception{
//        return configuration.getAuthenticationManager();
//
//    }

    @Bean
    public SecurityFilterChain securityFilterChain1(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.authenticationProvider(authenticationProvider1());

        httpSecurity.authorizeRequests()
                .requestMatchers("/client/**").hasRole("HIGH")
                .and().formLogin().loginPage("/loginClient")
                .loginProcessingUrl("/client/login")
                .defaultSuccessUrl("/client/index")
                .permitAll();


        return httpSecurity.build();
    }

//    public static void main(String[] args) {
//        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//        System.out.println(passwordEncoder.encode("ghost123"));
//    }
}
