package org.nextpertis.saasfeedbackcollector.service;

import lombok.RequiredArgsConstructor;
import org.nextpertis.saasfeedbackcollector.dto.FeedbackRequest;
import org.nextpertis.saasfeedbackcollector.exception.ResourceNotFoundException;
import org.nextpertis.saasfeedbackcollector.model.Feedback;
import org.nextpertis.saasfeedbackcollector.repository.FeedbackRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FeedbackServiceImpl implements FeedbackService {
    private final FeedbackRepository feedbackRepository;

    @Override
    public Feedback createFeedback(FeedbackRequest feedbackRequest) {
        Feedback feedback = Feedback.builder()
                .id(UUID.randomUUID().toString())
                .userId(feedbackRequest.getUserId())
                .category(feedbackRequest.getCategory())
                .message(feedbackRequest.getMessage())
                .timestamp(Instant.now())
                .build();

        return feedbackRepository.save(feedback);
    }

    @Override
    public Feedback getFeedbackById(String id) {
        return feedbackRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Feedback not found with id: " + id));
    }

    @Override
    public List<Feedback> getFeedbacksByCategory(String category) {
        List<Feedback> feedbacks = feedbackRepository.findByCategory(category);
        if (feedbacks.isEmpty()) {
            throw new ResourceNotFoundException("No feedback found for category: " + category);
        }
        return feedbacks;
    }
} 