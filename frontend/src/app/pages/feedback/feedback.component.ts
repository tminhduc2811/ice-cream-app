import { FeedbackService } from './../../services/feedback.service';
import { Component, OnInit } from '@angular/core';
import { Feedback } from 'src/app/models/feedback.model';

@Component({
  selector: 'app-feedback',
  templateUrl: './feedback.component.html',
  styleUrls: ['./feedback.component.css']
})
export class FeedbackComponent implements OnInit {

  feedbacks: Feedback[] = [];
  feedbacksLoaded = false;

  constructor(private feedbackService: FeedbackService) { }

  ngOnInit(): void {
    this.feedbackService.getAll()
    .subscribe(feedbacks => {
      this.feedbacks = feedbacks;
      this.feedbacksLoaded = true;
    });
  }

}
