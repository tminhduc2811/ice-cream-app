import { AuthService } from './../../services/auth.service';
import { Component, OnInit, Input } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Recipe } from 'src/app/models/recipe.model';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-recipe-modal',
  templateUrl: './recipe-modal.component.html',
  styleUrls: ['./recipe-modal.component.css']
})
export class RecipeModalComponent implements OnInit {

  @Input() recipe: Recipe;
  imgLoading = false;
  isCustomer = false;
  f: NgForm;
  constructor(public modal: NgbActiveModal, private auth: AuthService) { }

  ngOnInit(): void {
    console.log(this.recipe);
    this.isCustomer = this.auth.isCustomer();
  }

  confirm() {

  }
  onSubmit(f) {
    // console.log(this.f.value);
    console.log(f.value);
  }
}
