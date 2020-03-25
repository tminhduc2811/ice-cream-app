import { FaqService } from './../../services/faq.service';
import { FAQ } from './../../models/faq.model';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-faq',
  templateUrl: './faq.component.html',
  styleUrls: ['./faq.component.css']
})
export class FaqComponent implements OnInit {

  faqs: FAQ[] = [];
  faqsLoaded = false;

  constructor(private faqService: FaqService) {

  }

  ngOnInit(): void {
    this.faqService.getAll().subscribe(
      faqs => {
        this.faqs = faqs;
        this.faqsLoaded = true;
      }
    );
  }

}
