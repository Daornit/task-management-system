package stud.num.edu.mn.taskmanagementsystem;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import stud.num.edu.mn.taskmanagementsystem.config.FileStorageProperties;
import stud.num.edu.mn.taskmanagementsystem.security.TokenAuthenticationFilter;

import java.util.Properties;

@SpringBootApplication
@EnableConfigurationProperties({
		FileStorageProperties.class
})
public class Application {

	@Value("${isTokenRequiredContext}")
	boolean isTokenRequiredContext;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@EnableWebSecurity
	@Configuration
	class WebSecurityConfig extends WebSecurityConfigurerAdapter {

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			if(isTokenRequiredContext){
				http.csrf().disable()
						.addFilterAfter(new TokenAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
						.authorizeRequests()
						.antMatchers("/api/public/**").permitAll()
						.antMatchers("/token/login").permitAll()
						.anyRequest().authenticated();
			} else {
				http.csrf().disable()
						.addFilterAfter(new TokenAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
						.authorizeRequests()
						.anyRequest().permitAll();
			}
		}
	}
}
