import { AuthService } from './../../services/auth.service';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Component, OnInit, Input } from '@angular/core';
import { Recipe } from 'src/app/models/recipe.model';

@Component({
  selector: 'app-recipe-edit-modal',
  templateUrl: './recipe-edit-modal.component.html',
  styleUrls: ['./recipe-edit-modal.component.css']
})
export class RecipeEditModalComponent implements OnInit {

  @Input() recipe: Recipe;
  constructor(public modal: NgbActiveModal, private auth: AuthService) { }

  ngOnInit(): void {
  }
  confirm() {

  }
  uploadImage(event) {

  }
}
