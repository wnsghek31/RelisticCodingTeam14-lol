package org.cnu.realcoding.lolcrawler.config;

import com.mongodb.MongoClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
public class MongoConfig {
    @Bean
    public MongoTemplate createMongoTemplate() {
        //MongoDB 받아오는 Config 설정
        MongoClient mongoClient = new MongoClient("localhost", 27017);
        String databaseName = "LegueOfLegend";
        return new MongoTemplate(mongoClient, databaseName);
    }
}
