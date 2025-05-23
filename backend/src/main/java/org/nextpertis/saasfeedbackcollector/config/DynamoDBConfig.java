package org.nextpertis.saasfeedbackcollector.config;

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@EnableDynamoDBRepositories(basePackages = "org.nextpertis.saasfeedbackcollector.repository")
public class DynamoDBConfig {

    @Value("${amazon.dynamodb.region}")
    private String amazonDynamoDBRegion;

    @Value("${amazon.dynamodb.table-name}")
    private String tableName;

    @Bean
    @Primary
    public AmazonDynamoDB amazonDynamoDB() {
        return AmazonDynamoDBClientBuilder.standard()
                .withCredentials(DefaultAWSCredentialsProviderChain.getInstance())
                .withRegion(amazonDynamoDBRegion)
                .build();
    }

    @Bean
    @Primary
    public DynamoDBMapperConfig dynamoDBMapperConfig() {
        return DynamoDBMapperConfig.builder()
                .withTableNameOverride(DynamoDBMapperConfig.TableNameOverride.withTableNameReplacement(tableName))
                .withConsistentReads(DynamoDBMapperConfig.ConsistentReads.CONSISTENT)
                .withSaveBehavior(DynamoDBMapperConfig.SaveBehavior.UPDATE)
                .build();
    }

    @Bean
    @Primary
    public DynamoDBMapper dynamoDBMapper(AmazonDynamoDB amazonDynamoDB, DynamoDBMapperConfig config) {
        return new DynamoDBMapper(amazonDynamoDB, config);
    }
} 