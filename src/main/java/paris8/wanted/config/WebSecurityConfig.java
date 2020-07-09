package paris8.wanted.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.filter.OAuth2ClientAuthenticationProcessingFilter;
import org.springframework.security.oauth2.client.filter.OAuth2ClientContextFilter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;

import java.time.LocalDateTime;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import javax.servlet.Filter;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.boot.autoconfigure.security.oauth2.resource.PrincipalExtractor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;

import paris8.wanted.domain.WantedUser;
import paris8.wanted.repo.WantedUserRepo;
import paris8.wanted.domain.UserRole;

@Configuration
@EnableWebSecurity
@EnableOAuth2Sso
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.antMatcher("/**")
	        .authorizeRequests()
	        .antMatchers("/", "/login**", "/js/**", "/error**", "/images/**").permitAll()
	        .anyRequest().authenticated()
	        .and().logout().logoutSuccessUrl("/").permitAll()
	        .and()
          .csrf().disable();
	}
	
	@Bean
    public PrincipalExtractor principalExtractor(WantedUserRepo userDetailsRepo) {
		
        return map -> {
            String id = (String) map.get("sub");
            //soit l'utilisateur se trouve dans la base de données, soit il est créé et ajouté dans la base de donné au moment d'authentification
            WantedUser user = userDetailsRepo.findById(id).orElseGet(() -> {
            	WantedUser newUser = new  WantedUser();
                newUser.setId(id);
                newUser.setUsername((String) map.get("name"));
                newUser.setEmail((String) map.get("email"));
                newUser.setGender((String) map.get("gender"));
                
                return newUser;
            });
            return userDetailsRepo.save(user);
        };
        
    }
	
}

