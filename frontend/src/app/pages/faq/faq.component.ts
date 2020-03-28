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
  isLoaded = false;
  isLoading = false;

  constructor(private faqService: FaqService) {
  }

  ngOnInit(): void {
    this.isLoading = true;
    this.faqService.getAll().subscribe(
      faqs => {
        this.faqs = faqs;
        this.isLoaded = true;
        this.isLoading = false;
      }
    );
  }

}
