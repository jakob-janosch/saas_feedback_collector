package org.nextpertis.saasfeedbackcollector.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class FeedbackRequest {
    @NotBlank(message = "userId is required")
    private String userId;
    
    @NotBlank(message = "category is required")
    private String category;
    
    @NotBlank(message = "message is required")
    private String message;
}
