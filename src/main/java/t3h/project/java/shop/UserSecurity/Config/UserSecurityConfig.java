package t3h.project.java.shop.UserSecurity.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import t3h.project.java.shop.CustomerSecurity.Service.CustomerDetailsServiceImpl;
import t3h.project.java.shop.UserSecurity.Service.UserDetailsServiceImpl;
import t3h.project.java.shop.Utils.DefaultString;

@Configuration
@EnableWebSecurity
public class UserSecurityConfig {

    @Bean
    public UserDetailsService userDetailsService(){
        return new UserDetailsServiceImpl();
    }

    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }


    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider=new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(getPasswordEncoder());
        return authenticationProvider;
    }

    @Bean
    public UserDetailsService customerDetailsService(){
        return new CustomerDetailsServiceImpl();
    }

    @Bean
    public PasswordEncoder getPasswordEncoder1(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider1(){
        DaoAuthenticationProvider authenticationProvider=new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(customerDetailsService());
        authenticationProvider.setPasswordEncoder(getPasswordEncoder1());
        return authenticationProvider;
    }

    //    @Bean
    //    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception{
    //        return configuration.getAuthenticationManager();
    //
    //    }

    @Bean
    @Order(1)
    public SecurityFilterChain userSecurityFilterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.authenticationProvider(authenticationProvider());
        httpSecurity.securityMatcher(AntPathRequestMatcher.antMatcher("/admin/**"));
        httpSecurity.authorizeHttpRequests(autoConfig -> {
                    autoConfig.requestMatchers(HttpMethod.GET,"/admin/index").authenticated();
                    autoConfig.anyRequest().authenticated();
                })
                .formLogin(login -> {
                    login.loginPage("/admin/login");
                    login.defaultSuccessUrl("/admin/index");
                    login.failureUrl("/login-error");
                    login.permitAll();
                })
                .logout(logout -> {
                    logout.logoutRequestMatcher(new AntPathRequestMatcher("/admin/logout"));
                    logout.logoutSuccessUrl("/admin/login");
                    logout.deleteCookies("JSESSIONID");
                    logout.invalidateHttpSession(true);
                });

        return httpSecurity.build();
    }

    @Bean
    @Order(2)
    public SecurityFilterChain customerSecurityFilterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.authenticationProvider(authenticationProvider1());
        httpSecurity.authorizeHttpRequests(autoConfig -> {
            autoConfig.requestMatchers("/*","/ALLCSS/**").permitAll();
        });

        httpSecurity.securityMatcher(AntPathRequestMatcher.antMatcher("/client/**"));
        httpSecurity.authorizeHttpRequests(autoConfig -> {
                    autoConfig.requestMatchers(HttpMethod.GET,"/client/index").authenticated();
                    autoConfig.anyRequest().authenticated();
                })
                .formLogin(login -> {
                    login.loginPage("/client/login");
                    login.defaultSuccessUrl("/client/index");
                    login.failureUrl("/login-error");
                    login.permitAll();
                })
                .logout(logout -> {
                    logout.logoutRequestMatcher(new AntPathRequestMatcher("/client/logout"));
                    logout.logoutSuccessUrl("/client/login");
                    logout.deleteCookies("JSESSIONID");
                    logout.invalidateHttpSession(true);
                });


        return httpSecurity.build();
    }




//    public static void main(String[] args) {
//        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//        System.out.println(passwordEncoder.encode("ghost123"));
//    }

}
