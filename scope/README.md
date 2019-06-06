
## Web Scope
```java
@Configuration
public class MyConfiguration {
	@Bean
	@RequestScope
	public Foo fooBean() {
		return new Foo();
	}

	@Bean
	@SessionScope
	public Bar barBean() {
		return new Bar();
	}

	@Bean
	@ApplicationScope
	public Baz bazBean() {
		return new Baz();
	}
}

### Maven
```xml
<dependency>
	<groupId>org.springframework</groupId>
	<artifactId>spring-web</artifactId>
	<version>5.0.2.RELEASE</version>
</dependency>
```		