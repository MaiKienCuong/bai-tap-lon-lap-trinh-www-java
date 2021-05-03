package maikiencuong.config;

import java.util.List;
import java.util.Properties;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import com.fasterxml.jackson.databind.ObjectMapper;

import maikiencuong.dto.mapper.DTOModelMapper;

@EnableWebMvc
@Configuration
@EnableAspectJAutoProxy
@EnableTransactionManagement
@ComponentScan({ "maikiencuong" })
@PropertySource("classpath:application.properties")
@EnableJpaRepositories("maikiencuong.repository")
public class SpringWebConfig implements WebMvcConfigurer {

	private static final String PROP_DATABASE_URL = "db.url";
	private static final String PROP_DATABASE_DRIVER = "db.driver";
	private static final String PROP_DATABASE_PASSWORD = "db.password";
	private static final String PROP_DATABASE_USERNAME = "db.username";
	private static final String PROP_HIBERNATE_DIALECT = "hibernate.dialect";
	private static final String PROP_HIBERNATE_SHOW_SQL = "hibernate.show_sql";
	private static final String PROP_HIBERNATE_FORMAT_SQL = "hibernate.format_sql";
	private static final String PROP_HIBERNATE_HBM2DDL_AUTO = "hibernate.hbm2ddl.auto";
	private static final String PROP_ENTITYMANAGER_PACKAGES_TO_SCAN = "db.entitymanager.packages.to.scan";

	@Autowired
	private Environment evn;

	@Autowired
	private ApplicationContext applicationContext;

	@Bean
	public InternalResourceViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/WEB-INF/");
		viewResolver.setSuffix(".jsp");

		return viewResolver;
	}

	/* config jpa */

	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setUrl(evn.getRequiredProperty(PROP_DATABASE_URL));
		dataSource.setUsername(evn.getRequiredProperty(PROP_DATABASE_USERNAME));
		dataSource.setPassword(evn.getRequiredProperty(PROP_DATABASE_PASSWORD));
		dataSource.setDriverClassName(evn.getRequiredProperty(PROP_DATABASE_DRIVER));

		return dataSource;
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
		entityManagerFactoryBean.setDataSource(dataSource());
		entityManagerFactoryBean.setPersistenceProviderClass(HibernatePersistenceProvider.class);
		entityManagerFactoryBean.setPackagesToScan(evn.getRequiredProperty(PROP_ENTITYMANAGER_PACKAGES_TO_SCAN));
		entityManagerFactoryBean.setJpaProperties(hibernateProperties());

		return entityManagerFactoryBean;
	}

	@Bean
	public JpaTransactionManager transactionManager() {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());

		return transactionManager;
	}

	@Bean
	public ModelMapper modelMapper() {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STANDARD);

		return modelMapper;
	}

	@Bean
	public ObjectMapper objectMapper() {
		return Jackson2ObjectMapperBuilder.json().applicationContext(applicationContext).build();
	}

	@Bean
	public CommonsMultipartResolver multipartResolver() {
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
		multipartResolver.setDefaultEncoding("UTF-8");
		multipartResolver.setMaxUploadSize(10000000);
		return multipartResolver;
	}

	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
		EntityManagerFactory em = entityManagerFactory().getObject();
		EntityManager entityManager = null;
		if (em != null)
			entityManager = em.createEntityManager();
		argumentResolvers.add(new DTOModelMapper(objectMapper(), entityManager, modelMapper()));
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/src/**").addResourceLocations("/src/");
	}

	private Properties hibernateProperties() {
		Properties properties = new Properties();
		properties.setProperty(PROP_HIBERNATE_DIALECT, evn.getProperty(PROP_HIBERNATE_DIALECT));
		properties.setProperty(PROP_HIBERNATE_FORMAT_SQL, evn.getProperty(PROP_HIBERNATE_FORMAT_SQL));
		properties.setProperty(PROP_HIBERNATE_SHOW_SQL, evn.getProperty(PROP_HIBERNATE_SHOW_SQL));
		properties.setProperty(PROP_HIBERNATE_HBM2DDL_AUTO, evn.getProperty(PROP_HIBERNATE_HBM2DDL_AUTO));

		return properties;
	}

}