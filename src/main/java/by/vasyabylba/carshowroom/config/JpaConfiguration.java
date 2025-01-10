package by.vasyabylba.carshowroom.config;

import liquibase.integration.spring.SpringLiquibase;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Map;
import java.util.Properties;

@Configuration
@PropertySource(value = "classpath:application.yaml", factory = ApplicationProperties.class)
@ComponentScan(basePackages = "by.vasyabylba.carshowroom")
@EnableJpaRepositories("by.vasyabylba.carshowroom.repository")
@EnableTransactionManagement
public class JpaConfiguration {

    @Value("${datasource.driver-class-name}")
    private String datasourceDriverClassName;

    @Value("${datasource.username}")
    private String datasourceUsername;

    @Value("${datasource.password}")
    private String datasourcePassword;

    @Value("${datasource.url}")
    private String datasourceUrl;

    @Value("${jpa.show-sql}")
    private String jpaShowSql;

    @Value("${jpa.hibernate.ddl-auto}")
    private String hibernateDDLAuto;

    @Value("${jpa.properties.hibernate.format_sql}")
    private String formatSql;

    @Value("${liquibase.change-log}")
    private String liquibaseChangeLog;

    @Bean
    public SpringLiquibase liquibase() {
        SpringLiquibase springLiquibase = new SpringLiquibase();

        springLiquibase.setDataSource(dataSource());
        springLiquibase.setChangeLog(liquibaseChangeLog);

        return springLiquibase;
    }

    @Bean
    public JpaTransactionManager transactionManager(LocalContainerEntityManagerFactoryBean entityManagerFactoryBean) {
        JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
        jpaTransactionManager.setEntityManagerFactory(entityManagerFactoryBean.getObject());

        return jpaTransactionManager;
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setDriverClassName(datasourceDriverClassName);
        dataSource.setUsername(datasourceUsername);
        dataSource.setPassword(datasourcePassword);
        dataSource.setUrl(datasourceUrl);

        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();

        entityManagerFactory.setPackagesToScan("by.vasyabylba");

        Properties properties = new Properties();
        properties.putAll(Map.of(
                "hibernate.show-sql", jpaShowSql,
                "hibernate.format-sql", formatSql,
                "hibernate.ddl-auto", hibernateDDLAuto
        ));
        entityManagerFactory.setJpaProperties(properties);

        entityManagerFactory.setDataSource(dataSource);
        entityManagerFactory.setPersistenceProviderClass(HibernatePersistenceProvider.class);
        entityManagerFactory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());

        return entityManagerFactory;
    }

}
