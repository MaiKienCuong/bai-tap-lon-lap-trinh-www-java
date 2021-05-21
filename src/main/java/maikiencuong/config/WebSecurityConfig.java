package maikiencuong.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import maikiencuong.jwt.AccessDeniedHandlerJwt;
import maikiencuong.jwt.AuthEntryPointJwt;
import maikiencuong.jwt.AuthTokenFilter;

/**
 * The Class WebSecurityConfig.
 * 
 * <p>
 * Config security cho ung dung
 * </p>
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, jsr250Enabled = true, prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private AuthEntryPointJwt unauthorizedHandler;

	@Autowired
	private AccessDeniedHandlerJwt accessDeniedHandle;

	@Bean
	public AuthTokenFilter authenticationJwtTokenFilter() {
		return new AuthTokenFilter();
	}

	/**
	 * Password encoder.
	 * 
	 * <p>
	 * Doi tuong dung de bam mat khau plain text sang ma Bcrypt
	 * </p>
	 *
	 * @return the password encoder
	 */
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	/**
	 * Configure.
	 * 
	 * <p>
	 * Them doi tuong userDetailsService da implement interface UserDetailService
	 * vao AuthenticationManagerBuilder
	 * </p>
	 *
	 * @param authenticationManagerBuilder the authentication manager builder
	 * @throws Exception the exception
	 */
	@Override
	public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
		authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.cors()
		.and().csrf().disable().authorizeRequests()
		.antMatchers("/").permitAll()
		.antMatchers("/src/**").permitAll()
		.antMatchers("/api/**").permitAll()
		.anyRequest().authenticated()
		.and().exceptionHandling().accessDeniedHandler(accessDeniedHandle)
		.and().exceptionHandling().authenticationEntryPoint(unauthorizedHandler)
		.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
	}

}
