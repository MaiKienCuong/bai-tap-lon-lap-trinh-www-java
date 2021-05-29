package quanaotreem.config;

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

import quanaotreem.jwt.AccessDeniedHandlerJwt;
import quanaotreem.jwt.AuthEntryPointJwt;
import quanaotreem.jwt.AuthTokenFilter;

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

	/**
	 * The user details service.
	 * <p>
	 * Doi tuong nay dung de lay thong tin nguoi dung dang nhap theo username
	 * </p>
	 */
	@Autowired
	private UserDetailsService userDetailsService;

	/**
	 * The unauthorized handler.
	 * <p>
	 * Doi tuong nay handle loi 401
	 * </p>
	 */
	@Autowired
	private AuthEntryPointJwt unauthorizedHandler;

	/**
	 * The access denied handle.
	 * <p>
	 * Doi tuong nay handle loi 403
	 * </p>
	 */
	@Autowired
	private AccessDeniedHandlerJwt accessDeniedHandle;

	/**
	 * Authentication jwt token filter.
	 *
	 * <p>
	 * Doi tuong filter, extend class OncePerRequestFilter, dung cho viec xac thuc
	 * jwt
	 * </p>
	 * 
	 * @return the auth token filter
	 */
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

	/**
	 * Authentication manager bean.
	 * <p>
	 * Doi tuong quan ly viec dang nhap, duoc su dung trong class AuthApi
	 * </p>
	 * 
	 * @return the authentication manager
	 * @throws Exception the exception
	 */
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
			.and().csrf().disable()
			.authorizeRequests()
			.antMatchers("/").permitAll()
			.antMatchers("/src/**").permitAll()
			.antMatchers("/api/**").permitAll()
			.anyRequest().authenticated()
			.and().exceptionHandling().accessDeniedHandler(accessDeniedHandle)
			.and().exceptionHandling().authenticationEntryPoint(unauthorizedHandler)
			.and().sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
	}

}
