package org.nextpertis.saasfeedbackcollector.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.nextpertis.saasfeedbackcollector.dto.FeedbackRequest;
import org.nextpertis.saasfeedbackcollector.model.Feedback;
import org.nextpertis.saasfeedbackcollector.service.FeedbackService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/feedback")
@RequiredArgsConstructor
public class FeedbackController {
    private final FeedbackService feedbackService;

    @PostMapping
    public ResponseEntity<Feedback> createFeedback(@Valid @RequestBody FeedbackRequest feedbackRequest) {
        Feedback feedback = feedbackService.createFeedback(feedbackRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(feedback);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Feedback> getFeedbackById(@PathVariable String id) {
        Feedback feedback = feedbackService.getFeedbackById(id);
        return ResponseEntity.ok(feedback);
    }

    @GetMapping
    public ResponseEntity<List<Feedback>> getFeedbacksByCategory(@RequestParam(required = false) String category) {
        List<Feedback> feedbacks = feedbackService.getFeedbacksByCategory(category);
        return ResponseEntity.ok(feedbacks);
    }
} 