import { UserService } from './services/user.service';
import { FaqItemComponent } from './pages/faq/faq-item/faq-item.component';
import { FaqService } from './services/faq.service';
import { ApiService } from './services/api.service';
import { ListErrorsComponent } from './shared/list-errors.component';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './header/header.component';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import { RecipesComponent } from './pages/recipes/recipes.component';
import { HomeComponent } from './pages/home/home.component';
import { ErrorPageComponent } from './pages/error-page/error-page.component';
import { UsersComponent } from './pages/users/users.component';
import { CustomersComponent } from './pages/customers/customers.component';
import { FeedbackComponent } from './pages/feedback/feedback.component';
import { OrdersComponent } from './pages/orders/orders.component';
import { RecipeListComponent } from './pages/recipes/recipe-list/recipe-list.component';
import { RecipeDetailComponent } from './pages/recipes/recipe-detail/recipe-detail.component';
import { RecipeItemComponent } from './pages/recipes/recipe-list/recipe-item/recipe-item.component';
import { AuthComponent } from './auth/auth.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { FaqComponent } from './pages/faq/faq.component';
import { HttpClientModule } from '@angular/common/http';
import { UserItemComponent } from './pages/users/user-item/user-item.component';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    PageNotFoundComponent,
    RecipesComponent,
    HomeComponent,
    ErrorPageComponent,
    UsersComponent,
    CustomersComponent,
    FeedbackComponent,
    OrdersComponent,
    RecipeListComponent,
    RecipeDetailComponent,
    RecipeItemComponent,
    AuthComponent,
    ListErrorsComponent,
    FaqComponent,
    FaqItemComponent,
    UserItemComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule
  ],
  providers: [ApiService, FaqService, UserService],
  bootstrap: [AppComponent]
})
export class AppModule { }
