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
  imgUrl = '';
  imgLoading = false;
  isUser = false;
  isCustomer = false;
  f: NgForm;
  temp = 'https://firebasestorage.googleapis.com/v0/b/ice-cream-web.appspot.com/o/images%2Fic1.jpg?alt=media&token=882db35d-43c6-4dfe-a70e-10cfae2869b3';
  constructor(public modal: NgbActiveModal, private auth: AuthService) { }

  ngOnInit(): void {
    console.log(this.recipe);
    this.isCustomer = this.auth.isCustomer();
    this.isUser = this.auth.isUser();
  }

  confirm() {

  }
  onSubmit(f) {
    // console.log(this.f.value);
    console.log(f.value);
  }
}
