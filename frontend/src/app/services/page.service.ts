import { Page } from './../models/page.model';
import { range } from 'rxjs';
export class PageService {
  getPage(totalPages: number, currentPage: number): Page {
    let startPage: number;
    let endPage: number;

    if (totalPages <= 5) {
      startPage = 1;
      endPage = totalPages;
    } else {
      if (currentPage <= 3) {
        startPage = 1;
        endPage = 5;
      } else if (currentPage + 1 >= totalPages) {
        startPage = totalPages - 4;
        endPage = totalPages;
      } else {
        startPage = currentPage - 2;
        endPage = currentPage + 2;
      }
    }
    const pages: number[] = [...Array(1 + endPage - startPage).keys()].map(v => startPage + v);

    const page: Page = {
      currentPage,
      startPage,
      endPage,
      pages
    };
    return page;

  }
}
