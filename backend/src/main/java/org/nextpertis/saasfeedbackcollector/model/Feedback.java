package org.nextpertis.saasfeedbackcollector.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverted;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.nextpertis.saasfeedbackcollector.config.InstantConverter;

import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamoDBTable(tableName = "Feedback")
public class Feedback {
    @NotBlank(message = "id is required")
    @DynamoDBHashKey
    private String id;

    @NotBlank(message = "userId is required")
    @DynamoDBAttribute
    private String userId;

    @NotBlank(message = "category is required")
    @DynamoDBAttribute
    private String category;

    @NotBlank(message = "message is required")
    @DynamoDBAttribute
    private String message;

    @NotNull(message = "timestamp is required")
    @DynamoDBAttribute
    @DynamoDBTypeConverted(converter = InstantConverter.class)
    private Instant timestamp;
} 