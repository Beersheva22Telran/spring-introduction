package telran.spring.security;

import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

public class SecurityConfiguration {
 @Bean
 @Order(Ordered.HIGHEST_PRECEDENCE)
	SecurityFilterChain configure(HttpSecurity httpSecurity) throws Exception {
	 return null;
 }
}
