package io.github.hizhangbo.config;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.internal.MongoClientImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.data.mongodb.config.MongoConfigurationSupport;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDbFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * @author Bob
 * @date 2020-03-03 18:19
 */
@Configuration
@PropertySource("classpath:/application.properties")
@EnableMongoRepositories(basePackages = "io.github.hizhangbo.repository")
public class MongoConfig extends MongoConfigurationSupport {
    @Value("${mongodb.url}")
    private String url;

    @Value("${mongodb.port}")
    private int port;

    @Value("${mongodb.database}")
    private String database;

    @Value("${mongodb.user}")
    private String user;

    @Value("${mongodb.password}")
    private String password;

    @Bean
    public MongoClient mongoClient() {
        return new MongoClientImpl(
                MongoClientSettings.builder()
                        .applyConnectionString(new ConnectionString(String.format("mongodb://%s:%d", url, port)))
                        .build(), null
        );
    }

    @Bean
    public MongoDbFactory mongoDbFactory(MongoClient mongoClient) {
        return new SimpleMongoClientDbFactory(mongoClient, database);
    }

    @Bean
    MongoTransactionManager transactionManager(MongoDbFactory mongoDbFactory) {
        return new MongoTransactionManager(mongoDbFactory);
    }

    @Bean
    public MongoTemplate mongoTemplate(MongoClient mongoClient) {
        return new MongoTemplate(mongoClient, database);
    }

    @Override
    protected String getDatabaseName() {
        return database;
    }
}
