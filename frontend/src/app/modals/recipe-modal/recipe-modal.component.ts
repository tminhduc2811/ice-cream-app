import { Router } from '@angular/router';
import { AuthService } from './../../services/auth.service';
import { Component, OnInit, Input } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Recipe } from 'src/app/models/recipe.model';
import { NgForm } from '@angular/forms';
import { CartService } from 'src/app/services/cart.service';

@Component({
  selector: 'app-recipe-modal',
  templateUrl: './recipe-modal.component.html',
  styleUrls: ['./recipe-modal.component.css']
})
export class RecipeModalComponent implements OnInit {

  @Input() recipe: Recipe;
  imgLoading = false;
  isCustomer = false;
  quantity = 0;
  f: NgForm;
  constructor(public modal: NgbActiveModal, private auth: AuthService, private cartService: CartService, private router: Router) { }

  ngOnInit(): void {
    this.isCustomer = this.auth.isCustomer();
  }

  confirm() {

  }
  onSubmit(f) {
  }
  addToCart() {
    if (!this.isCustomer) {
      this.router.navigate(['/login']);
    }
    this.cartService.saveItem(this.recipe, this.quantity);
    this.quantity = 0;
  }

}
