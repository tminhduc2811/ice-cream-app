import { AuthService } from './../../services/auth.service';
import { debounceTime } from 'rxjs/operators';
import { User } from './../../models/user.model';
import { RecipeEditModalComponent } from './../../modals/recipe-edit-modal/recipe-edit-modal.component';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Recipe } from './../../models/recipe.model';
import { IceCreamService } from './../../services/ice-cream.service';
import { RecipeService } from './../../services/recipe.service';
import { Component, OnInit } from '@angular/core';
import { IceCream } from 'src/app/models/ice-cream.model';
import { Subject } from 'rxjs';

@Component({
  selector: 'app-recipes',
  templateUrl: './recipes.component.html',
  styleUrls: ['./recipes.component.css']
})
export class RecipesComponent implements OnInit {

  eventsSubject: Subject<void> = new Subject<void>();
  selectedRecipe: Recipe;
  iceCreams: IceCream[] = [];
  isLoaded = false;
  selectedId: number;
  isUser = false;
  // Alert messages
  successMessage = '';
  success = new Subject<string>();
  warningMessage = '';
  warning = new Subject<string>();

  constructor(private recipeService: RecipeService,
              private iceCreamService: IceCreamService,
              private modalService: NgbModal,
              private auth: AuthService) {

    this.iceCreamService.getAll()
      .subscribe(rs => {
        this.iceCreams = rs;
        this.isLoaded = true;
      });
  }

  ngOnInit(): void {
    this.isUser = this.auth.isUser();
    this.setAlert();
  }
  iceCreamSelected(index) {
    if (index < this.iceCreams.length) {
      this.recipeService.typeSelected.emit(this.iceCreams[index].id);
    } else {
      this.recipeService.typeSelected.emit(0);
    }
  }
  setAlert() {

    // Set timeout for alert
    this.success.subscribe(message => this.successMessage = message);
    this.success.pipe(debounceTime(3000)).subscribe(() => this.successMessage = '');
    this.warning.subscribe(message => this.warningMessage = message);
    this.warning.pipe(debounceTime(4000)).subscribe(() => this.warningMessage = '');
  }

  newRecipe() {
    const recipe: Recipe = {} as Recipe;
    const icecream: IceCream = {} as IceCream;
    const user: User = {} as User;
    recipe.user = user;
    recipe.icecream = icecream;

    const modalRef = this.modalService.open(RecipeEditModalComponent, { size: 'lg' });
    modalRef.componentInstance.type = 1;
    modalRef.componentInstance.recipe = recipe;
    modalRef.componentInstance.icecreams = this.iceCreams;
    modalRef.result.then(rs => {
      if (rs.status) {
        this.eventsSubject.next();
        this.success.next('Recipe created successfully!');
      }
    }).catch(rs => { });
  }
  statusUpdated(rs) {
    this.success.next('Recipe updated successfully!');
  }
}
