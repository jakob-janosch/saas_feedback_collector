package org.nextpertis.saasfeedbackcollector.service;

import lombok.RequiredArgsConstructor;
import org.nextpertis.saasfeedbackcollector.dto.FeedbackRequest;
import org.nextpertis.saasfeedbackcollector.exception.ResourceNotFoundException;
import org.nextpertis.saasfeedbackcollector.exception.ValidationException;
import org.nextpertis.saasfeedbackcollector.model.Feedback;
import org.nextpertis.saasfeedbackcollector.repository.FeedbackRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FeedbackServiceImpl implements FeedbackService {
    private final FeedbackRepository feedbackRepository;

    @Override
    public Feedback createFeedback(FeedbackRequest feedbackRequest) {
        validateFeedbackRequest(feedbackRequest);

        try {
            Feedback feedback = Feedback.builder()
                    .id(UUID.randomUUID().toString())
                    .userId(feedbackRequest.getUserId())
                    .category(feedbackRequest.getCategory())
                    .message(feedbackRequest.getMessage())
                    .timestamp(Instant.now())
                    .build();

            return feedbackRepository.save(feedback);
        } catch (Exception e) {
            throw new RuntimeException("Failed to save feedback: " + e.getMessage());
        }
    }

    private void validateFeedbackRequest(FeedbackRequest request) {
        if (!StringUtils.hasText(request.getUserId())) {
            throw new ValidationException("User ID is required");
        }
        if (!StringUtils.hasText(request.getCategory())) {
            throw new ValidationException("Category is required");
        }
        if (!StringUtils.hasText(request.getMessage())) {
            throw new ValidationException("Message is required");
        }
        if (request.getMessage().length() < 10) {
            throw new ValidationException("Message must be at least 10 characters long");
        }
    }

    @Override
    public Feedback getFeedbackById(String id) {
        if (!StringUtils.hasText(id)) {
            throw new ValidationException("Feedback ID is required");
        }
        try {
            return feedbackRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Feedback not found with id: " + id));
        } catch (Exception e) {
            if (e instanceof ResourceNotFoundException) {
                throw e;
            }
            throw new RuntimeException("Failed to retrieve feedback: " + e.getMessage());
        }
    }

    @Override
    public List<Feedback> getFeedbacksByCategory(String category) {
        try {
            if (!StringUtils.hasText(category) || "all".equalsIgnoreCase(category)) {
                return feedbackRepository.findAll();
            }
            return feedbackRepository.findByCategory(category);
        } catch (Exception e) {
            throw new RuntimeException("Failed to retrieve feedbacks: " + e.getMessage());
        }
    }
} 