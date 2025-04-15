import { Component, Inject } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { ThemePalette } from '@angular/material/core';

import { FeedbackService } from '../../services/feedback.service';

@Component({
  selector: 'app-feedback-form',
  templateUrl: './feedback-form.component.html',
  styleUrls: ['./feedback-form.component.scss'],
})
export class FeedbackFormComponent {
  feedbackForm: FormGroup;
  isSubmitting = false;
  submitError: string = '';
  color: ThemePalette = 'accent';

  constructor(
    private fb: FormBuilder,
    private dialogRef: MatDialogRef<FeedbackFormComponent>,
    private feedbackService: FeedbackService,
    @Inject(MAT_DIALOG_DATA) public data: { categories: any[] },
  ) {
    this.feedbackForm = this.fb.group({
      userId: ['', [Validators.required, Validators.minLength(3)]],
      category: ['', Validators.required],
      message: ['', [Validators.required, Validators.minLength(10)]],
    });
  }

  onSubmit(): void {
    if (this.feedbackForm.valid) {
      this.isSubmitting = true;
      this.submitError = '';

      this.feedbackService.submitFeedback(this.feedbackForm.value).subscribe({
        next: () => {
          this.dialogRef.close(true);
        },
        error: error => {
          this.isSubmitting = false;
          this.submitError = 'Failed to submit feedback. Please try again.';
          console.error('Error submitting feedback:', error);
        },
      });
    }
  }

  onCancel(): void {
    this.dialogRef.close();
  }

  getErrorMessage(controlName: string): string {
    const control = this.feedbackForm.get(controlName);
    if (control?.errors) {
      if (control.errors['required']) {
        return `${controlName.charAt(0).toUpperCase() + controlName.slice(1)} is required`;
      }
      if (control.errors['minlength']) {
        return `${controlName.charAt(0).toUpperCase() + controlName.slice(1)} must be at least ${control.errors['minlength'].requiredLength} characters`;
      }
    }
    return '';
  }
}
