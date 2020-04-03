import { ConfirmModalComponent } from './../../modals/confirm-modal/confirm-modal.component';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { debounceTime } from 'rxjs/operators';
import { AuthService } from './../../services/auth.service';
import { PageService } from './../../services/page.service';
import { Page } from './../../models/page.model';
import { FeedbackService } from './../../services/feedback.service';
import { Component, OnInit } from '@angular/core';
import { Feedback } from 'src/app/models/feedback.model';
import { FeedbackView } from 'src/app/auth/views/feedback.view.model';
import { Subject } from 'rxjs';

@Component({
  selector: 'app-feedback',
  templateUrl: './feedback.component.html',
  styleUrls: ['./feedback.component.css']
})
export class FeedbackComponent implements OnInit {

  feedbacks: Feedback[] = [];
  isLoaded = false;
  isLoading = false;
  isUser = false;
  result: FeedbackView;
  page: Page;
  size = 5;
  // Alert messages
  successMessage = '';
  success = new Subject<string>();
  warningMessage = '';
  warning = new Subject<string>();

  constructor(private feedbackService: FeedbackService,
              private pageService: PageService,
              private auth: AuthService,
              private modalService: NgbModal) { }

  ngOnInit(): void {
    this.setAlert();
    this.isLoading = true;
    this.isUser = this.auth.isUser();
    this.feedbackService.getAll({ page: 0, size: this.size })
      .subscribe(rs => {
        this.result = rs;
        this.feedbacks = this.result.content;
        this.setPage(1);
        this.isLoaded = true;
        this.isLoading = false;
      });
  }

  setAlert() {

    // Set timeout for alert
    this.success.subscribe(message => this.successMessage = message);
    this.success.pipe(debounceTime(3000)).subscribe(() => this.successMessage = '');
    this.warning.subscribe(message => this.warningMessage = message);
    this.warning.pipe(debounceTime(4000)).subscribe(() => this.warningMessage = '');
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

  deleteOnClick(index) {
    const modalRef = this.modalService.open(ConfirmModalComponent);
    modalRef.componentInstance.data = {
      header: 'Are you sure to delete this feedback',
      message: '',
      subMessage: 'This operation will not be undoned',
      danger: true
    };
    modalRef.result.then(() => {
      this.feedbackService.delete(this.feedbacks[index].id)
        .subscribe(() => {
          this.feedbacks.splice(index, 1);
        }, err => {
          this.warning.next(err.message);
        });
    }).catch(rs => { });
  }
}
