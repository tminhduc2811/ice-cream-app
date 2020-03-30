import { IceCreamService } from './services/ice-cream.service';
import { CustomerGuard } from './services/auth-customer.service';

import { LoadingSpinnerComponent } from './shared/loading-spinner/loading.component';
import { JwtInterceptor } from './auth/Jwt.Interceptor';
import { AdminGuard } from './services/auth-admin.service';
import { AuthService } from './services/auth.service';
import { OrderService } from 'src/app/services/order.service';
import { FeedbackService } from './services/feedback.service';
import { RecipeListComponent } from './pages/recipes/recipe-list/recipe-list.component';
import { RecipeService } from './services/recipe.service';
import { CustomerService } from './services/customer.service';
import { UserService } from './services/user.service';
import { FaqItemComponent } from './pages/faq/faq-item/faq-item.component';
import { FaqService } from './services/faq.service';
import { ApiService } from './services/api.service';
import { ListErrorsComponent } from './shared/list-errors.component';
import { BrowserModule } from '@angular/platform-browser';

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
import { RecipeDetailComponent } from './pages/recipes/recipe-detail/recipe-detail.component';
import { RecipeItemComponent } from './pages/recipes/recipe-list/recipe-item/recipe-item.component';
import { AuthComponent } from './auth/auth.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { FaqComponent } from './pages/faq/faq.component';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { ProfileComponent } from './pages/profile/profile.component';
import { UserProfileComponent } from './pages/profile/user-profile/user-profile.component';
import { AngularFireModule } from '@angular/fire';
import { AngularFireStorage } from '@angular/fire/storage';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import { NgModule } from '@angular/core';
import { CustomerProfileComponent } from './pages/profile/customer-profile/customer-profile.component';

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
    ProfileComponent,
    UserProfileComponent,
    LoadingSpinnerComponent,
    CustomerProfileComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    AngularFireModule.initializeApp({
      apiKey: 'AIzaSyAd8rrLHWQDDq9Tgo9bmyoiKjC0b-ybwyA',
      authDomain: 'ice-cream-web.firebaseapp.com',
      databaseURL: 'https://ice-cream-web.firebaseio.com',
      projectId: 'ice-cream-web',
      storageBucket: 'ice-cream-web.appspot.com',
      messagingSenderId: '363876808617',
      appId: '1:363876808617:web:89dc1c9f8ee47b4f2036b8',
      measurementId: 'G-JS6HP4V3VH'
    },
      AngularFireStorage,
    ),
    NgbModule

  ],
  providers: [ApiService, FaqService, UserService, IceCreamService,
    CustomerService, RecipeService, FeedbackService,
    OrderService, AuthService, AdminGuard, CustomerGuard,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: JwtInterceptor,
      multi: true
    }],
  bootstrap: [AppComponent]
})
export class AppModule { }
