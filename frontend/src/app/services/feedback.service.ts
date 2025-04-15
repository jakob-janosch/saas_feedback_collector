import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import { Feedback, FeedbackRequest } from '../models/feedback.interface';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root',
})
export class FeedbackService {
  private apiUrl = `${environment.apiUrl}/feedback`;

  constructor(private http: HttpClient) {}

  submitFeedback(feedback: FeedbackRequest): Observable<Feedback> {
    return this.http.post<Feedback>(this.apiUrl, feedback);
  }

  getFeedbacksByCategory(category: string): Observable<Feedback[]> {
    return this.http.get<Feedback[]>(`${this.apiUrl}?category=${category}`);
  }

  getFeedbackById(id: string): Observable<Feedback> {
    return this.http.get<Feedback>(`${this.apiUrl}/${id}`);
  }
}
