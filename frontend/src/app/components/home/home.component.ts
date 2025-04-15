import { Component, OnInit, OnDestroy } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Subscription } from 'rxjs';

import { Feedback } from '../../models/feedback.interface';
import { FeedbackService } from '../../services/feedback.service';
import { FeedbackFormComponent } from '../feedback-form/feedback-form.component';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss'],
})
export class HomeComponent implements OnInit, OnDestroy {
  feedbacks: Feedback[] = [];
  isLoading = false;
  error: string | null = null;
  selectedCategory = 'all';
  private subscription: Subscription | null = null;

  categories = [
    { value: 'all', label: 'All Categories' },
    { value: 'bug', label: 'Bug' },
    { value: 'feature', label: 'Feature' },
    { value: 'improvement', label: 'Improvement' },
    { value: 'other', label: 'Other' },
  ];

  constructor(
    private feedbackService: FeedbackService,
    private dialog: MatDialog,
    private snackBar: MatSnackBar,
  ) {}

  ngOnInit(): void {
    this.loadFeedbacks();
  }

  ngOnDestroy(): void {
    if (this.subscription) {
      this.subscription.unsubscribe();
    }
  }

  openSubmitDialog(): void {
    const dialogRef = this.dialog.open(FeedbackFormComponent, {
      width: '500px',
      maxWidth: '95vw',
      maxHeight: '90vh',
      panelClass: 'feedback-dialog',
      disableClose: true,
      autoFocus: true,
      data: {
        categories: this.categories.filter(cat => cat.value !== 'all'),
      },
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.loadFeedbacks();
      }
    });
  }

  loadFeedbacks(): void {
    this.isLoading = true;
    this.error = null;

    if (this.subscription) {
      this.subscription.unsubscribe();
    }

    this.subscription = this.feedbackService
      .getFeedbacksByCategory(this.selectedCategory)
      .subscribe({
        next: feedbacks => {
          this.feedbacks = feedbacks;
          this.isLoading = false;
        },
        error: error => {
          this.error =
            error.message || 'An error occurred while loading feedbacks';
          this.isLoading = false;
        },
      });
  }

  formatDate(timestamp: string): string {
    return new Date(timestamp).toLocaleString();
  }

  getCategoryColor(category: string): string {
    switch (category.toLowerCase()) {
      case 'bug':
        return 'warn';
      case 'feature':
        return 'primary';
      case 'improvement':
        return 'accent';
      default:
        return 'default';
    }
  }
}
