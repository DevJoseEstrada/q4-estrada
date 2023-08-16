import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ListProjectComponent } from './components/list-project/list-project.component';
import { DetailsProjectComponent } from './components/details-project/details-project.component';
import { UpdateProjectComponent } from './components/update-project/update-project.component';
import { ListCustomerComponent } from './components/list-customer/list-customer.component';
import { DetailsCustomerComponent } from './components/details-customer/details-customer.component';
import { UpdateCustomerComponent } from './components/update-customer/update-customer.component';
const routes: Routes = [
  { path: '', redirectTo: '/project', pathMatch: 'full' },
  { path: 'project', component: ListProjectComponent },
  { path: 'project/update/:id', component: UpdateProjectComponent },
  { path: 'project/details/:id', component: DetailsProjectComponent },
  { path: 'customer', component: ListCustomerComponent },
  { path: 'customer/update/:id', component: UpdateCustomerComponent },
  { path: 'customer/details/:id', component: DetailsCustomerComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
