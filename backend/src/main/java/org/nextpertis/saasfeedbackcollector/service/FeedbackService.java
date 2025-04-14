package org.nextpertis.saasfeedbackcollector.service;

import lombok.RequiredArgsConstructor;
import org.nextpertis.saasfeedbackcollector.model.Feedback;
import org.nextpertis.saasfeedbackcollector.repository.FeedbackRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class FeedbackService {
    private final FeedbackRepository feedbackRepository;

    public Feedback saveFeedback(Feedback feedback) {
        if (feedback.getTimestamp() == null) {
            feedback.setTimestamp(Instant.now());
        }

        return feedbackRepository.save(feedback);
    }
} 