package com.valantic;

import jakarta.persistence.EntityManagerFactory;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "mockServerEntityManagerFactory",
        transactionManagerRef = "mockServerTransactionManager",
        basePackages = {"com.valantic.sti.model"}
)
@EnableJpaAuditing
@ConditionalOnProperty(name = "mockserver.enabled", matchIfMissing = true)
public class PersistenceConfig {
    @Bean(name = "mockServerProperties")
    @ConfigurationProperties("spring.datasource.mockserver")
    public DataSourceProperties dataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean(name = "mockServerDatasource")
    // @ConfigurationProperties(prefix = "spring.datasource.mockserver")
    public DataSource datasource(final @NotNull @Qualifier("mockServerProperties") DataSourceProperties properties) {
        return properties.initializeDataSourceBuilder().build();
    }

    @Bean(name = "mockServerEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(
            final @NotNull EntityManagerFactoryBuilder builder, @Qualifier("mockServerDatasource") final DataSource dataSource) {
        return builder.dataSource(dataSource)
                .packages("com.valantic.sti.model")
                .persistenceUnit("mockServer")
                .build();
    }

    @Bean(name = "mockServerTransactionManager")
    @ConfigurationProperties("spring.jpa")
    public PlatformTransactionManager transactionManager(
            @Qualifier("mockServerEntityManagerFactory") final EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}
