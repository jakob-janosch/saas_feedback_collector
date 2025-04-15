package org.nextpertis.saasfeedbackcollector.repository;

import org.nextpertis.saasfeedbackcollector.model.Feedback;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

@EnableScan
public interface FeedbackRepository extends CrudRepository<Feedback, String> {
    List<Feedback> findByCategory(String category);
}