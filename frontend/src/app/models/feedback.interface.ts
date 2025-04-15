export interface FeedbackRequest {
  userId: string;
  category: string;
  message: string;
}

export interface Feedback extends FeedbackRequest {
  id: string;
  timestamp: string;
}
