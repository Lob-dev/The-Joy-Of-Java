package com.shard.demo.global.configuration;

import com.shard.demo.global.configuration.property.DataSourceProperty;
import com.shard.demo.global.configuration.property.ShardPolicyProperty;
import com.shard.demo.global.persistence.route.DataSourceRouter;
import com.shard.demo.global.persistence.shard.DataSourceLocator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.LazyConnectionDataSourceProxy;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Slf4j
@Configuration
@EnableJpaRepositories(
        basePackages = "com.shard.demo.post.domain",
        entityManagerFactoryRef = "entityManagerFactory",
        transactionManagerRef = "transactionManager"
)
@EnableTransactionManagement
@RequiredArgsConstructor
public class PersistenceConfiguration {

    protected final Environment env;

    @Bean
    protected DataSourceLocator dataSourceLocator(
            DataSourceProperty dataSourceProperty,
            ShardPolicyProperty shardPolicyProperty
    ) {
        return new ConsistenceHashDataSourceLocator(dataSourceProperty.configs(), shardPolicyProperty.configs());
    }

    @Bean
    protected DataSource dataSourceRouter(
            DataSourceProperty dataSourceProperty,
            ShardPolicyProperty shardPolicyProperty
    ) {
        final Map<Object, Object> dataSources = new LinkedHashMap<>();
        dataSourceProperty.configs().forEach(config -> {
            DriverManagerDataSource dataSource = new DriverManagerDataSource();
            dataSource.setUrl(config.url());
            dataSource.setUsername(config.username());
            dataSource.setPassword(config.password());
            dataSource.setDriverClassName(config.driverClassName());
            dataSources.put(config.name(), dataSource);
        });

        log.info("dataSources = {}", dataSources);
        return new DataSourceRouter(dataSources, dataSourceLocator(dataSourceProperty, shardPolicyProperty));
    }

    @Bean
    protected DataSource dataSource(DataSource dataSourceRouter) {
        return new LazyConnectionDataSourceProxy(dataSourceRouter);
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean entityManager = new LocalContainerEntityManagerFactoryBean();
        entityManager.setDataSource(dataSource);
        entityManager.setJpaVendorAdapter(new HibernateJpaVendorAdapter());

        Map<String, Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto", "update");
        properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        entityManager.setJpaPropertyMap(properties);

        entityManager.setPackagesToScan("com.shard.demo.post");
        entityManager.setPersistenceUnitName("post");

        return entityManager;
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        return transactionManager;
    }
}
