package my.demo.bookmark.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		//HTTP Basic authentication
        .httpBasic()
        .and()
        .authorizeRequests()
        .antMatchers(HttpMethod.GET, "/bookmark/**").hasRole("USER")
        .antMatchers(HttpMethod.POST, "/bookmark").hasRole("ADMIN")
        .antMatchers(HttpMethod.PUT, "/bookmark/**").hasRole("ADMIN")
        .antMatchers(HttpMethod.PATCH, "/bookmark/**").hasRole("ADMIN")
        .antMatchers(HttpMethod.DELETE, "/bookmark/**").hasRole("ADMIN")
        .and()
        //.csrf().disable()
        .formLogin().disable();
	}

	@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.inMemoryAuthentication()
                .withUser("user").password("{noop}password").roles("USER")
                .and()
                .withUser("admin").password("{noop}password").roles("USER", "ADMIN");

    }
}