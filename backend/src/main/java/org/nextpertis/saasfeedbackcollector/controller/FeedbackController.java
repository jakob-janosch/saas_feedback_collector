package org.nextpertis.saasfeedbackcollector.controller;

import lombok.RequiredArgsConstructor;
import org.nextpertis.saasfeedbackcollector.dto.FeedbackRequest;
import org.nextpertis.saasfeedbackcollector.model.Feedback;
import org.nextpertis.saasfeedbackcollector.service.FeedbackService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/feedback")
@RequiredArgsConstructor
public class FeedbackController {
    private final FeedbackService feedbackService;

    @PostMapping(
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Feedback> createFeedback(@Valid @RequestBody FeedbackRequest request) {
        String generatedId = UUID.randomUUID().toString();
        
        Feedback feedback = Feedback.builder()
            .id(generatedId)
            .userId(request.getUserId())
            .category(request.getCategory())
            .message(request.getMessage())
            .timestamp(request.getTimestamp())
            .build();
            
        Feedback savedFeedback = feedbackService.saveFeedback(feedback);
        return ResponseEntity.ok(savedFeedback);
    }
} 