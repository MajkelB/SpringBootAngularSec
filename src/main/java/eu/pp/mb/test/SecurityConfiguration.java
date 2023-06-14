package eu.pp.mb.test;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter  {


        @Override
        protected void configure(HttpSecurity http) throws Exception {
            // @formatter:off
            http
                    .csrf()
                    .disable()
                    .authorizeRequests()
//                    .antMatchers(HttpMethod.OPTIONS, "app/**")
                    .antMatchers("/app/**.js", "/app/**.css", "/app/**.ico" )
                    .permitAll()
//                    .antMatchers( "/app/favicon.ico", "/app/**.js" ).permitAll()
//;
                    .antMatchers( "/app/index.html", "/app/test", "/app/login", "app/logout", "/test" ).permitAll()
                    .antMatchers( HttpMethod.POST, "/login", "/logout" ).permitAll()
                    .anyRequest().authenticated()
                    .and().formLogin().loginPage("/app/login").permitAll()
                    .and().logout().logoutSuccessUrl("/app/login");






//                    .and().httpBasic();
//					.and()
//					.csrf()
//					.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
            // @formatter:on




//            http
//                    .formLogin().loginPage("/app/login").successForwardUrl("/user").and()
//                    .logout().and()
//                    .authorizeRequests()
//                    .antMatchers("/app/index.html", "/app", "/app/home").permitAll()
//                    .anyRequest().authenticated()
//                    .and()
//                    .csrf()
//                    .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
        }


//    @Bean
//    public InMemoryUserDetailsManager userDetailsService() {
//        UserDetails user1 = User.withUsername("user1")
//                .password(passwordEncoder().encode("user1Pass"))
//                .roles("USER")
//                .build();
//        UserDetails user2 = User.withUsername("user2")
//                .password(passwordEncoder().encode("user2Pass"))
//                .roles("USER")
//                .build();
//        UserDetails user = User.withUsername("user")
//                .password(passwordEncoder().encode("password"))
//                .roles("ADMIN")
//                .build();
//        UserDetails admin = User.withUsername("admin")
//                .password(passwordEncoder().encode("adminPass"))
//                .roles("ADMIN")
//                .build();
//        return new InMemoryUserDetailsManager(user1, user2, user, admin);
//    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("user").password("{noop}password").roles("USER");
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
}
