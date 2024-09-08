package com.example.multiple_db.product.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = "com.example.multiple_db.product.repository",
        transactionManagerRef = "productTransactionManager",
        entityManagerFactoryRef = "productEntityManagerFactory"
)
public class ProductConfig {

    @Primary
    @Bean(name="productDatasource")
    @ConfigurationProperties(prefix = "spring.datasource.productdb")
    public DataSource productDatasource() {
        return DataSourceBuilder.create().build();
    }

    @Primary
    @Bean(name="productEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean productEntityManagerFactory(@Qualifier("productDatasource") DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource);
        em.setPackagesToScan("com.example.multiple_db.product.entity");
        em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());

        // Set custom JPA properties
        Map<String, Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto", "update");
        em.setJpaPropertyMap(properties);

        return em;
    }

    @Primary
    @Bean(name="productTransactionManager")
    public PlatformTransactionManager productTransactionManager(@Qualifier("productEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}
