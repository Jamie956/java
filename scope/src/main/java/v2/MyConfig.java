package v2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class MyConfig {
	@Bean
	@Scope("prototype")
	public User userBean() {
		return new User();
	}
}
