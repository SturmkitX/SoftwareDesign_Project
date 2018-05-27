package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .antMatchers("/register").permitAll()
                    .antMatchers("/adminpanel", "/mediaupload", "/mediauploadstatus").hasAuthority("ADMIN")
                        .anyRequest().authenticated()
                    .antMatchers("/testsocket/**", "/welcome", "/createroom").hasAnyAuthority("ADMIN", "USER")
                        .anyRequest().authenticated()
                .and()
                .formLogin()
                    .loginPage("/login")
                    .successForwardUrl("/welcome")
                    .permitAll()
                .and()
                .logout()
                    .permitAll()
        .and()
        .rememberMe()
        .and()
        .csrf().disable();
    }

    @Autowired
    private DataSource dataSource;

    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
    auth.jdbcAuthentication().dataSource(dataSource)
            .usersByUsernameQuery("select email AS username, password, enabled from user where email=?")
            .authoritiesByUsernameQuery("select email AS username, role from user where email=?")
            .passwordEncoder(new BCryptPasswordEncoder());
    }
}