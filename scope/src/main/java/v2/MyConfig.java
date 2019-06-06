package v2;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;

@Configuration
@ComponentScan("v2")
public class MyConfig {
	@Bean
	@Lazy
	@Scope(scopeName = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	public Window windowBean() {
		return new Window();
	}

}