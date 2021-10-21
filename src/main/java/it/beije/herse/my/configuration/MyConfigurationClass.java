package it.beije.herse.my.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import it.beije.herse.JpaEntityManager;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(value = {"it.beije.herse.my.repository"})
public class MyConfigurationClass {


    @Primary
    @Bean(name="transactionManager")
    public PlatformTransactionManager dbTransactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(JpaEntityManager.getInstance());
        return transactionManager;
    }
}
