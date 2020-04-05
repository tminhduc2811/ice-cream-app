import { ConfirmModalComponent } from './../../../../modals/confirm-modal/confirm-modal.component';
import { Router } from '@angular/router';
import { debounceTime } from 'rxjs/operators';
import { IceCreamService } from './../../../../services/ice-cream.service';
import { IceCream } from './../../../../models/ice-cream.model';
import { RecipeEditModalComponent } from './../../../../modals/recipe-edit-modal/recipe-edit-modal.component';
import { AngularFireStorage } from '@angular/fire/storage';
import { RecipeModalComponent } from './../../../../modals/recipe-modal/recipe-modal.component';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { AuthService } from './../../../../services/auth.service';
import { RecipeService } from './../../../../services/recipe.service';
import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { Recipe } from 'src/app/models/recipe.model';
import { Subject } from 'rxjs';
import { CartService } from 'src/app/services/cart.service';

@Component({
  selector: 'app-recipe-item',
  templateUrl: './recipe-item.component.html',
  styleUrls: ['./recipe-item.component.css']
})
export class RecipeItemComponent implements OnInit {

  @Output() statusUpdate = new EventEmitter<any>();
  @Input() recipe: Recipe;
  roles: string[] = [];
  icecreams: IceCream[] = [];
  imageUrl = '';
  imgLoading = false;
  quantity = 0;
  isCustomer = false;

  constructor(private iceCreamService: IceCreamService,
              private auth: AuthService,
              private modalService: NgbModal,
              private cartService: CartService,
              private router: Router,
              private recipeService: RecipeService) {
  }

  ngOnInit(): void {
    this.auth.authInfo.subscribe(data => {
      this.roles = data.roles;
      this.isCustomer = this.auth.isCustomer();
    });
    this.iceCreamService.getAll().subscribe(rs => this.icecreams = rs);
  }

  showDetail() {
    console.log(this.recipe);
    const modalRef = this.modalService.open(RecipeModalComponent, { centered: true, size: 'm' });
    modalRef.componentInstance.recipe = this.recipe;
  }

  edit() {
    const modalRef = this.modalService.open(RecipeEditModalComponent, { centered: true, size: 'lg' });
    modalRef.componentInstance.recipe = this.recipe;
    modalRef.componentInstance.type = 0;
    modalRef.componentInstance.icecreams = this.icecreams;
    modalRef.result.then(rs => {
      if (rs.status) {
        this.statusUpdate.emit(rs.status);
      }
    }).catch(rs => {});
  }

  addToCart() {
    if (!this.isCustomer) {
      this.router.navigate(['/login']);
    }
    this.cartService.saveItem(this.recipe, this.quantity);
    const cart = this.cartService.getCartFromLocalStorage();
    this.quantity = 0;
  }

  remove() {
    const modalRef = this.modalService.open(ConfirmModalComponent);
    modalRef.componentInstance.data = {
      header: 'Delete recipe',
      message: 'Do you want to delete this recipe?',
      subMessage: 'All orders of this recipe will also be removed',
      danger: true
    };
    modalRef.result.then(() => {
      this.recipeService.deleteRecipe(this.recipe.id)
      .subscribe(rs => {
        this.statusUpdate.emit(true);
      });
    }).catch(rs => {});
  }
}
