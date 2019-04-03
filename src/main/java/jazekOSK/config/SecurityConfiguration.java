package jazekOSK.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.filter.CharacterEncodingFilter;
import jazekOSK.handler.SuccessLoginHandler;

/**
 * @author trutyna
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final SuccessLoginHandler successLoginHandler;

    private final UserDetailsService userDetailsService;

    @Autowired
    public SecurityConfiguration(SuccessLoginHandler successLoginHandler, @Qualifier("customUserDetailsService") UserDetailsService userDetailsService) {
        this.successLoginHandler = successLoginHandler;
        this.userDetailsService = userDetailsService;
    }


    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
        //auth.inMemoryAuthentication().withUser("admin").password("admin").roles("ADMIN");
        //auth.inMemoryAuthentication().withUser("kurs").password("kurs").roles("STUDENT");
        //auth.inMemoryAuthentication().withUser("inst").password("inst").roles("INSTRUCTOR");
        auth.userDetailsService(userDetailsService);
        auth.authenticationProvider(authenticationProvider());
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding("UTF-8");
        filter.setForceEncoding(true);
        http.addFilterBefore(filter,CsrfFilter.class);

        http.authorizeRequests()
                .antMatchers("/", "/main").permitAll()
                .antMatchers("/panelAdmin/**").access("hasRole('ROLE_ADMIN')")
                .antMatchers("/panelInstructor/**").access("hasRole('ROLE_INSTRUCTOR')")
                .antMatchers("/panelStudent/**").access("hasRole('ROLE_STUDENT')")
                .and()
                .formLogin()
                .loginPage("/main")
                .loginProcessingUrl("/signin")
                .usernameParameter("login")
                .passwordParameter("password")
                .failureUrl("/main")
                .successForwardUrl("/panelAdmin")
                .permitAll()
                .successHandler(successLoginHandler)
                .and()
                .exceptionHandling()
                .accessDeniedPage("/error")
                .and()
                .csrf().disable();
    }
}
