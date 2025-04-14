package org.nextpertis.saasfeedbackcollector.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.time.Instant;

@Data
public class FeedbackRequest {
    @NotBlank(message = "userId is required")
    private String userId;
    
    @NotBlank(message = "category is required")
    private String category;
    
    @NotBlank(message = "message is required")
    private String message;
    
    @NotNull(message = "timestamp is required")
    private Instant timestamp;
}
