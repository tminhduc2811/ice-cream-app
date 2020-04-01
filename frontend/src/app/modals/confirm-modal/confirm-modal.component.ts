import { ConfirmModalModel } from './confirm-modal.model';
import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-confirm-modal',
  templateUrl: './confirm-modal.component.html',
  styleUrls: ['./confirm-modal.component.css']
})
export class ConfirmModalComponent {

  @Input() data: ConfirmModalModel;

  constructor(public modal: NgbActiveModal) { }

  confirm() {
    this.modal.close(true);
  }
}
