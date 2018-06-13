package es.uca.inovels;

import java.security.Principal;
import java.util.HashSet;
import java.util.Set;

import org.mockito.internal.util.collections.Sets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration.WebMvcAutoConfigurationAdapter;
import org.springframework.boot.convert.ApplicationConversionService;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterRegistry;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import es.uca.inovels.model.Novel;
import es.uca.inovels.model.Scene;
import es.uca.inovels.model.User;
import es.uca.inovels.model.UserNovel;
import es.uca.inovels.services.NovelService;
import es.uca.inovels.services.SceneService;
import es.uca.inovels.services.UserNovelService;
import es.uca.inovels.services.UserService;

@SpringBootApplication
@RestController
public class InteractivenovelsApplication{
	
	private static final Logger log = LoggerFactory.getLogger(InteractivenovelsApplication.class);
	
	//Prevents issues with Cross Origin Requests
	@Bean
    public CorsFilter corsFilter() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        final CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("OPTIONS");
        config.addAllowedMethod("HEAD");
        config.addAllowedMethod("GET");
        config.addAllowedMethod("PUT");
        config.addAllowedMethod("POST");
        config.addAllowedMethod("DELETE");
        config.addAllowedMethod("PATCH");
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
	
	@Bean
	public CommandLineRunner loadData(UserService uService, NovelService nService,
			UserNovelService unService, SceneService sService) {
		return (args) -> {
			if (uService.findAll().size() == 0) {
				//We create a root user with password root
				User root = new User("root");
				root.setPassword("root");
				root.setRole("ROLE_ADMIN");
				uService.save(root);
				
				root = uService.loadUserByUsername("root");
				
				Novel test = new Novel(root, "Test Novel", "Description of test novel");
				
				Novel test2 = new Novel(root, "Test Novel 2", "Description of test novel 2");

				nService.save(test);
				nService.save(test2);
				
				test = nService.loadNovelByName("Test Novel");
								
				UserNovel progress = new UserNovel(root, test);
				UserNovel progress2 = new UserNovel(root, test2);

				
				unService.save(progress);
				unService.save(progress2);
				
				Scene scene = new Scene(test);
				sService.save(scene);
				Scene scene2 = new Scene(test);
				scene2.setText("Text of Scene 2");
				sService.save(scene2);
				
				test.addScene(scene);
				test.addScene(scene2);
				
				nService.save(test);
				
				
				/*uService.save(root);
				nService.save(test);
				
				System.out.println("Progress test");
				System.out.println("User: " + unService.loadUserNovelByUser(root).get(0).getPage());
				System.out.println("Novel: " + unService.loadUserNovelByNovel(test).get(0).getPage());
				System.out.println("Both: " + unService.findOne(root, test).get().getPage());
				/*
				progress.turnPage();
				
				unService.save(progress);
				uService.save(root);
				nService.save(test);
				
				System.out.println("Progress test");
				System.out.println("User: " + unService.loadUserNovelByUser(root).get(0).getPage());
				System.out.println("Novel: " + unService.loadUserNovelByNovel(test).get(0).getPage());
				System.out.println("Both: " + unService.findOne(root, test).get().getPage());
				
				progress.turnPage();
				
				unService.save(progress);
				
				System.out.println("Progress test");
				System.out.println("User: " + unService.loadUserNovelByUser(root).get(0).getPage());
				System.out.println("Novel: " + unService.loadUserNovelByNovel(test).get(0).getPage());
				System.out.println("Both: " + unService.findOne(root, test).get().getPage());*/	
			}
		};
	}
	
	//Shows ids of user and novel
	@Configuration
	public static class RepositoryConfig extends
	        RepositoryRestConfigurerAdapter {

	    @Override
	    public void configureRepositoryRestConfiguration(
	            RepositoryRestConfiguration config) {
	        config.exposeIdsFor(User.class, Novel.class);
	    }
	}
	
	public static void main(String[] args) {
		SpringApplication.run(InteractivenovelsApplication.class, args);
	}
	
	
	
}
