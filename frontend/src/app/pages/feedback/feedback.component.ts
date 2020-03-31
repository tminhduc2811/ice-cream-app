import { PageService } from './../../services/page.service';
import { Page } from './../../models/page.model';
import { FeedbackService } from './../../services/feedback.service';
import { Component, OnInit } from '@angular/core';
import { Feedback } from 'src/app/models/feedback.model';
import { FeedbackView } from 'src/app/auth/views/feedback.view.model';

@Component({
  selector: 'app-feedback',
  templateUrl: './feedback.component.html',
  styleUrls: ['./feedback.component.css']
})
export class FeedbackComponent implements OnInit {

  feedbacks: Feedback[] = [];
  isLoaded = false;
  isLoading = false;
  result: FeedbackView;
  page: Page;
  size = 5;

  constructor(private feedbackService: FeedbackService, private pageService: PageService) { }

  ngOnInit(): void {
    this.isLoading = true;
    this.feedbackService.getAll({ page: 0, size: this.size })
    .subscribe(rs => {
      this.result = rs;
      this.feedbacks = this.result.content;
      this.setPage(1);
      this.isLoaded = true;
      this.isLoading = false;
    });
  }

  setPage(currentPage: number) {
    if (currentPage < 0 || currentPage >= this.result.totalPages + 1) {
      return;
    }
    this.page = this.pageService.getPage(this.result.totalPages, currentPage);
    this.isLoading = true;
    // get new recipes
    this.feedbackService.getAll({ page: currentPage - 1, size: this.size })
      .subscribe(rs => {
        this.result = rs;
        this.feedbacks = this.result.content;
        this.isLoading = false;
      }, err => {
        this.isLoading = false;
      });

  }
}
