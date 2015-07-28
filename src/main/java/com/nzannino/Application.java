package com.nzannino;

import com.mangofactory.swagger.configuration.SpringSwaggerConfig;
import com.mangofactory.swagger.models.dto.ApiInfo;
import com.mangofactory.swagger.plugin.EnableSwagger;
import com.mangofactory.swagger.plugin.SwaggerSpringMvcPlugin;
import com.nzannino.web.data.ExceptionTranslator;
import com.nzannino.web.data.SpringTransactionProvider;
import org.jooq.*;
import org.jooq.impl.DataSourceConnectionProvider;
import org.jooq.impl.DefaultConfiguration;
import org.jooq.impl.DefaultDSLContext;
import org.jooq.impl.DefaultExecuteListenerProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy;

import javax.sql.DataSource;


/**
 * User: zannino
 * Date: 20/03/2015
 * Time: 10:31
 */
@SpringBootApplication
@EnableSwagger
public class Application {

    private SpringSwaggerConfig springSwaggerConfig;

    @Autowired
    public void setSpringSwaggerConfig(SpringSwaggerConfig springSwaggerConfig) {
        this.springSwaggerConfig = springSwaggerConfig;
    }

    @Bean
    public SwaggerSpringMvcPlugin customImplementation() {
        return new SwaggerSpringMvcPlugin(this.springSwaggerConfig).apiInfo(
                apiInfo());
    }

    private ApiInfo apiInfo() {
        ApiInfo apiInfo = new ApiInfo(
                "Spring Boot Example API",
                "",
                "",
                "",
                "",
                "");
        return apiInfo;
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public DataSourceTransactionManager transactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean
    public DSLContext dsl(org.jooq.Configuration config) {
        return new DefaultDSLContext(config);
    }

    @Bean
    public ConnectionProvider connectionProvider(DataSource dataSource) {
        return new DataSourceConnectionProvider(new TransactionAwareDataSourceProxy(dataSource));
    }

    @Bean
    public TransactionProvider transactionProvider() {
        return new SpringTransactionProvider();
    }

    @Bean
    public ExceptionTranslator exceptionTranslator() {
        return new ExceptionTranslator();
    }

    @Bean
    public ExecuteListenerProvider executeListenerProvider(ExceptionTranslator exceptionTranslator) {
        return new DefaultExecuteListenerProvider(exceptionTranslator);
    }

    @Bean
    public org.jooq.Configuration jooqConfig(ConnectionProvider connectionProvider,
                                             TransactionProvider transactionProvider,
                                             ExecuteListenerProvider executeListenerProvider) {

        return new DefaultConfiguration() //
                .derive(connectionProvider) //
                .derive(transactionProvider) //
                .derive(executeListenerProvider) //
                .derive(SQLDialect.H2);
    }
}
