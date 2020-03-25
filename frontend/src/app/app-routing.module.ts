import { FaqComponent } from './pages/faq/faq.component';
import { AuthComponent } from './auth/auth.component';
import { OrdersComponent } from './pages/orders/orders.component';
import { FeedbackComponent } from './pages/feedback/feedback.component';
import { CustomersComponent } from './pages/customers/customers.component';
import { UsersComponent } from './pages/users/users.component';
import { HomeComponent } from './pages/home/home.component';
import { ErrorPageComponent } from './pages/error-page/error-page.component';
import { RecipesComponent } from './pages/recipes/recipes.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';


const routes: Routes = [
  { path: '', component: HomeComponent },
  {
    path: 'recipes', component: RecipesComponent, children: [
      { path: ':id/:name', component: RecipesComponent }
    ]
  },
  { path: 'users', component: UsersComponent },
  { path: 'faq', component: FaqComponent },
  { path: 'login', component: AuthComponent },
  { path: 'register', component: AuthComponent },
  { path: 'customers', component: CustomersComponent },
  { path: 'feedback', component: FeedbackComponent },
  { path: 'orders', component: OrdersComponent },
  { path: 'not-found', component: ErrorPageComponent, data: { message: 'Page not found!' } },
  { path: '**', redirectTo: '/not-found' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
