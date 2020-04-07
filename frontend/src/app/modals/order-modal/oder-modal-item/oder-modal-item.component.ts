import { Detail } from './../../../models/order.model';
import { Recipe } from './../../../models/recipe.model';
import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-oder-modal-item',
  templateUrl: './oder-modal-item.component.html',
  styleUrls: ['./oder-modal-item.component.css']
})
export class OderModalItemComponent implements OnInit {

  @Input() orderDetail: Detail;
  constructor() { }

  ngOnInit(): void {
  }

}
