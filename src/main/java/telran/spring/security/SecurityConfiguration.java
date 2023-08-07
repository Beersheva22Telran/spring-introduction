package telran.spring.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
@Bean
SecurityFilterChain configure(HttpSecurity httpSec) throws Exception {
	
	return httpSec.csrf(custom -> custom.disable())
	.cors(custom->custom.disable()).authorizeHttpRequests(custom -> 
			custom.anyRequest().permitAll()).build();
}
}
