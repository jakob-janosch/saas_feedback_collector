package org.nextpertis.saasfeedbackcollector.service;

import org.nextpertis.saasfeedbackcollector.dto.FeedbackRequest;
import org.nextpertis.saasfeedbackcollector.model.Feedback;

import java.util.List;

public interface FeedbackService {
    Feedback createFeedback(FeedbackRequest feedbackRequest);

    Feedback getFeedbackById(String id);

    List<Feedback> getFeedbacksByCategory(String category);
} 