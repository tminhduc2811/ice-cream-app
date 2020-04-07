import { Order } from './../../models/order.model';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-order-modal',
  templateUrl: './order-modal.component.html',
  styleUrls: ['./order-modal.component.css']
})
export class OrderModalComponent implements OnInit {

  @Input() order: Order;
  total = 0;

  constructor(public modal: NgbActiveModal) { }

  ngOnInit(): void {
    console.log(this.order);
    for (const od of this.order.orderDetails) {
      this.total += od.total;
    }
  }

  confirm(form) {
    this.modal.close(this.order);
  }
}
