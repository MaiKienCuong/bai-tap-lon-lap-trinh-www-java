package quanaotreem.config;

import java.util.List;
import java.util.Properties;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.fasterxml.jackson.databind.ObjectMapper;

import quanaotreem.dto.mapper.DTOModelMapper;

/**
 * The Class SpringWebConfig.
 * 
 * <p>
 * Config Spring app <br>
 * EnableAspectJAutoProxy cho phep su dung cac class aspect o trong project<br>
 * EnableJpaRepositories khai bao package chua cac class repository, cho phep su
 * dung jparepository trong project
 * </p>
 */
@EnableWebMvc
@Configuration
@EnableAspectJAutoProxy
@EnableTransactionManagement
@ComponentScan({ "quanaotreem" })
@PropertySource("classpath:application.properties")
@EnableJpaRepositories("quanaotreem.repository")
public class SpringWebConfig implements WebMvcConfigurer {

	private static final String DATABASE_URL = "spring.datasource.url";
	private static final String DATABASE_DRIVER = "spring.datasource.driver-class-name";
	private static final String DATABASE_PASSWORD = "spring.datasource.password";
	private static final String DATABASE_USERNAME = "spring.datasource.username";

	private static final String HIBERNATE_DIALECT = "spring.jpa.database-platform";
	private static final String HIBERNATE_SHOW_SQL = "spring.jpa.show-sql";
	private static final String HIBERNATE_FORMAT_SQL = "spring.jpa.properties.hibernate.format_sql";
	private static final String HIBERNATE_HBM2DDL_AUTO = "spring.jpa.hibernate.ddl-auto";

	@Value("${package.toscan}")
	private String packageToScan;

	/**
	 * The evn.
	 * 
	 * <p>
	 * Bien environment dung de chua cac gia tri cua file properties trong
	 * PropertySource
	 * </p>
	 */
	@Autowired
	private Environment evn;

	@Autowired
	private ApplicationContext applicationContext;

	/**
	 * View resolver.
	 * 
	 * <p>
	 * Config viewResolver, khai bao duong dan chua cac view jsp
	 * </p>
	 *
	 * @return the internal resource view resolver
	 */
	@Bean
	public InternalResourceViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/");
		viewResolver.setSuffix(".jsp");

		return viewResolver;
	}

	/**
	 * Data source.
	 * 
	 * <p>
	 * Config datasource de ket noi den database
	 * </p>
	 *
	 * @return the data source
	 */
	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setUrl(evn.getRequiredProperty(DATABASE_URL));
		dataSource.setUsername(evn.getRequiredProperty(DATABASE_USERNAME));
		dataSource.setPassword(evn.getRequiredProperty(DATABASE_PASSWORD));
		dataSource.setDriverClassName(evn.getRequiredProperty(DATABASE_DRIVER));

		return dataSource;
	}

	/**
	 * Entity manager factory.
	 * 
	 * <p>
	 * Config entityManager Factory
	 * </p>
	 *
	 * @return the local container entity manager factory bean
	 */
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
		entityManagerFactoryBean.setDataSource(dataSource());
		entityManagerFactoryBean.setPackagesToScan(packageToScan);
		entityManagerFactoryBean.setJpaProperties(hibernateProperties());
		entityManagerFactoryBean.setPersistenceProviderClass(HibernatePersistenceProvider.class);

		return entityManagerFactoryBean;
	}

	/**
	 * Transaction manager.
	 * 
	 * <p>
	 * Config transaction manager
	 * </p>
	 *
	 * @return the jpa transaction manager
	 */
	@Bean
	public JpaTransactionManager transactionManager() {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());

		return transactionManager;
	}

	/**
	 * Model mapper.
	 * 
	 * <p>
	 * Config doi tuong modelMapper, dung de tu dong anh xa cac thuoc tinh trong cac
	 * class DTO sang cac class entity va nguoc lai, ma khong phai anh xa thu cong
	 * tung thuoc tinh
	 * </p>
	 *
	 * @return the model mapper
	 */
	@Bean
	public ModelMapper modelMapper() {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STANDARD);

		return modelMapper;
	}

	/**
	 * Object mapper.
	 * 
	 * <p>
	 * Khai bao doi tuong objectMapper dung de map cac doi tuong sang json va nguoc
	 * lai
	 * </p>
	 *
	 * @return the object mapper
	 */
	@Bean
	public ObjectMapper objectMapper() {
		return Jackson2ObjectMapperBuilder.json().applicationContext(applicationContext).build();
	}

	/**
	 * Adds the argument resolvers.
	 * 
	 * <p>
	 * Config doi tuong de handle cac argument trong cac phuong thuc, cac phuong
	 * thuc trong controller co tham so thi se duoc cac doi tuong nay xu ly.
	 * </p>
	 * <p>
	 * O day chi them doi tuong DTOMapper de no tu dong chuyen cac tham so DTO trong
	 * phuong thuc cua controller ma client gui len thanh cac entity, khi do
	 * controller chi can quan tam den viec xu ly ma khong can quan tam den viec
	 * chuyen tu DTO sang entity
	 * </p>
	 *
	 * @param argumentResolvers the argument resolvers
	 */
	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
		EntityManagerFactory em = entityManagerFactory().getObject();
		EntityManager entityManager = null;
		if (em != null)
			entityManager = em.createEntityManager();
		argumentResolvers.add(new DTOModelMapper(objectMapper(), entityManager, modelMapper()));
	}

	/**
	 * Add the resource handlers.
	 * 
	 * <p>
	 * khai bao cac duong dan cho cac tai nguyen tinh nhu html, css, image,...
	 * </p>
	 *
	 * @param registry the registry
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/src/**").addResourceLocations("/src/");
	}

	/**
	 * Hibernate properties.
	 * 
	 * <p>
	 * Config file properties cho hibernate
	 * </p>
	 *
	 * @return the properties
	 */
	private Properties hibernateProperties() {
		Properties properties = new Properties();
		properties.setProperty("hibernate.dialect", evn.getProperty(HIBERNATE_DIALECT));
		properties.setProperty("hibernate.show_sql", evn.getProperty(HIBERNATE_SHOW_SQL));
		properties.setProperty("hibernate.format_sql", evn.getProperty(HIBERNATE_FORMAT_SQL));
		properties.setProperty("hibernate.hbm2ddl.auto", evn.getProperty(HIBERNATE_HBM2DDL_AUTO));

		return properties;
	}

	@Bean
	public JavaMailSender getMailSender() {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

		mailSender.setHost(evn.getProperty("spring.mail.host"));
		mailSender.setUsername(evn.getProperty("spring.mail.username"));
		mailSender.setPassword(evn.getProperty("spring.mail.password"));
		mailSender.setPort(Integer.valueOf(evn.getProperty("spring.mail.port")));

		Properties javaMailProperties = new Properties();
		javaMailProperties.put("mail.smtp.starttls.enable",
				evn.getProperty("spring.mail.properties.mail.smtp.starttls.enable"));
		javaMailProperties.put("mail.smtp.auth", evn.getProperty("spring.mail.properties.mail.smtp.auth"));
		javaMailProperties.put("mail.transport.protocol",
				evn.getProperty("spring.mail.properties.mail.transport.protocol"));
		javaMailProperties.put("mail.debug", evn.getProperty("spring.mail.properties.mail.debug"));

		mailSender.setJavaMailProperties(javaMailProperties);
		return mailSender;
	}

}