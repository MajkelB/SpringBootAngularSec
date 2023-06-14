package eu.pp.mb.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@SpringBootApplication
@RestController
//@EnableAutoConfiguration
//@Controller
public class SpringBootSecurityApplication {

	Logger logger = LoggerFactory.getLogger(MvcConfiguration.class);

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	private HttpServletRequest httpServletRequest;

	@Autowired
	private HttpServletResponse httpServletResponse;

	public static void main(String[] args) {
		SpringApplication.run(SpringBootSecurityApplication.class, args);
	}


//	@GetMapping(value = "/{path:[^\\.]*}")
//	public String redirect() {
//		return "forward:/";
//	}

	@GetMapping("/resource")
	@ResponseBody
	public Map<String, Object> home() {
		logger.info("------------- /resource" );
		Map<String, Object> model = new HashMap<>();
		model.put("id", UUID.randomUUID().toString());
		model.put("content", "Hello World");
		logger.info( "******************************************************8 works ok");
		return model;
	}

	@GetMapping("/test")
	@ResponseBody
	public Map<String, Object> test() {
		logger.info("------------- /test" );
		Map<String, Object> model = new HashMap<>();
		model.put("test", "OK");
		return model;
	}

	@RequestMapping("/user")
//	@ResponseBody
	public Principal user(Principal user) {
		logger.info("------------- /user" );
		logger.info( "****************************************************" + user );
		return user;
	}

	@PostMapping("/login")
	@ResponseBody
	public User login( @RequestBody User userDetails) {
		logger.info("------------- /login" );
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			logger.info("****************************************************" + objectMapper.writeValueAsString(userDetails));
			UsernamePasswordAuthenticationToken authenticationTokenRequest = new
					UsernamePasswordAuthenticationToken(userDetails.getUsername(), userDetails.getPassword());
			Authentication authentication = this.authenticationManager.authenticate(authenticationTokenRequest);
			SecurityContext securityContext = SecurityContextHolder.getContext();
			securityContext.setAuthentication(authentication);

            /*HttpSession session = httpServletRequest.getSession(true);
            session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, securityContext);*/

//			User user = (User) authentication.getPrincipal();
			User user = new User();
			if (authentication.isAuthenticated()) {
//				user = (User) authentication.getPrincipal();
				user = new User( (org.springframework.security.core.userdetails.User ) authentication.getPrincipal() );
			}
			else
				user = new User();
			logger.info("Logged in user: {}", user);
			return user;
		} catch (BadCredentialsException ex) {
			logger.error( "Login BadCredentialsException: " , ex );
			return null;
		} catch (Exception e ) {
			logger.error( "Login Unhandled exception: " , e );
			return null;
		}

	}

	@PostMapping("/logout")
	@ResponseBody
	public String logout() {
		logger.info("------------- /logout" );
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null) {
			new SecurityContextLogoutHandler().logout(
					httpServletRequest,
					httpServletResponse,
					authentication);
		}
		return "{ result: \"OK\" }";
	}

//	@GetMapping("/userInfo")
//	@ResponseBody
//	public Principal userInfo() {
//		return user;
//	}

//	@Configuration
//	protected static class SecurityConfiguration extends WebSecurityConfigurerAdapter {
//		@Override
//		protected void configure(HttpSecurity http) throws Exception {
//			// @formatter:off
//			http
//					.csrf()
//					.disable()
//
//					.authorizeRequests()
//					.antMatchers(HttpMethod.OPTIONS, "app/**")
//					.permitAll()
//					.antMatchers( "app/index.html", "/app/test", "app/home", "app/login", "/test", "/resource", "/user" ).permitAll()
//					.anyRequest().authenticated().and()
//					.formLogin().loginPage("app/login").and()
//					.httpBasic();
////					.and()
////					.csrf()
////					.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
//			// @formatter:on
//		}
//	}

}
