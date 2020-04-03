import { debounceTime } from 'rxjs/operators';
import { RecipeModalComponent } from './../../../modals/recipe-modal/recipe-modal.component';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { AuthService } from './../../../services/auth.service';
import { PageService } from './../../../services/page.service';
import { RecipeView } from './../../../auth/views/recipes.view.model';
import { Page } from './../../../models/page.model';
import { Pageable } from './../../../models/view.model';
import { Recipe } from './../../../models/recipe.model';
import { RecipeService } from './../../../services/recipe.service';
import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { Subject } from 'rxjs';

@Component({
  selector: 'app-recipe-list',
  templateUrl: './recipe-list.component.html',
  styleUrls: ['./recipe-list.component.css'],
  // tslint:disable-next-line: no-host-metadata-property
  host: {
    '(window:resize)': 'onResize($event)'
  }
})
export class RecipeListComponent implements OnInit {

  @Output() statusUpdated = new EventEmitter<any>();
  recipes: Recipe[] = [];
  recipesLoaded = false;
  result: RecipeView;
  page: Page;
  isLoading = false;
  size = 10;

  constructor(private recipeService: RecipeService,
              private pageService: PageService,
              private auth: AuthService) {
  }
  ngOnInit(): void {
    this.isLoading = true;
    this.recipeService.getAll({ page: 0, size: this.size })
      .subscribe(rs => {
        this.result = rs;
        this.recipes = this.result.content;
        this.setPage(1);
        this.recipesLoaded = true;
        this.isLoading = false;
      });
    // this.isLoading = true;
    // this.recipeService.typeSelected
    //   .subscribe(idType => {
    //     console.log('Type id:', idType);
    //     if (idType === 0) {
    //       this.recipeService.getAll()
    //         .subscribe(rs => {
    //           this.recipes = rs.content;
    //           this.isLoading = false;
    //         }, err => {
    //           // TODO: Handle error later
    //           this.isLoading = false;
    //         });
    //     } else {
    //       this.recipeService.getAllByType(idType)
    //         .subscribe(recipes => {
    //           this.recipes = recipes;
    //           this.isLoading = false;
    //         }, err => {
    //           // TODO: Handle error later
    //           this.isLoading = false;
    //         });
    //     }
    //   });
  }

  setPage(currentPage: number) {
    if (currentPage < 0 || currentPage >= this.result.totalPages + 1) {
      return;
    }
    this.page = this.pageService.getPage(this.result.totalPages, currentPage);
    this.isLoading = true;
    // get new recipes
    this.recipeService.getAll({ page: currentPage - 1, size: this.size })
      .subscribe(rs => {
        this.result = rs;
        this.recipes = rs.content;
        this.isLoading = false;
      }, err => {
        this.isLoading = false;
      });
  }
  statusUpdate(status) {
    this.statusUpdated.emit(status);
  }
  onResize(event) {
    const width = event.target.innerWidth; // window width
    // TODO: Handle page size when changes happend
  }
}
