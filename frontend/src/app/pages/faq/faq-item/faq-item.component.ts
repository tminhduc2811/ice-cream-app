import { FAQ } from './../../../models/faq.model';
import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-faq-item',
  templateUrl: './faq-item.component.html',
  styleUrls: ['./faq-item.component.css']
})
export class FaqItemComponent implements OnInit {

  @Input() faqItem: FAQ;

  constructor() { }

  ngOnInit(): void {
  }

}
