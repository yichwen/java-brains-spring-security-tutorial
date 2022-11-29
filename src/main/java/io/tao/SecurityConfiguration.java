package io.tao;

//import org.h2.server.web.WebServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

//        In-Memory Authentication
//        auth.inMemoryAuthentication()
//                .withUser("blah")
//                .password("blah")
//                .roles("USER")
//                .and()
//                .withUser("foo")
//                .password("foo")
//                .roles("ADMIN");

//        JDBC-Authentication with default schema and user setup
//        auth.jdbcAuthentication()
//                .dataSource(dataSource)
//                .withDefaultSchema()
//                .withUser(User.withUsername("user").password("pass").roles("USER"))
//                .withUser(User.withUsername("admin").password("pass").roles("ADMIN"));

//        JDBC-Authentication with real-world scenario
//        auth.jdbcAuthentication().dataSource(dataSource);

        auth.userDetailsService(userDetailsService);
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()  // open up the method chaining
                // most restricted will configure first
                .antMatchers("/admin").hasRole("ADMIN")
                .antMatchers("/user").hasAnyRole("USER", "ADMIN")
                .antMatchers("/", "/h2/**").permitAll()
                .and().formLogin();

        // set for h2 console
//        http.csrf().disable();
//        http.headers().frameOptions().disable();
    }

    // register for h2 console
//    @Bean
//    ServletRegistrationBean h2servletRegistration(){
//        ServletRegistrationBean registrationBean = new ServletRegistrationBean(new WebServlet());
//        registrationBean.addUrlMappings("/h2/*");
//        return registrationBean;
//    }

}
