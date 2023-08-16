import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import {HttpClientModule} from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ListCustomerComponent } from './components/list-customer/list-customer.component';
import { AddCustomerComponent } from './components/add-customer/add-customer.component';
import { DetailsCustomerComponent } from './components/details-customer/details-customer.component';
import { DetailsProjectComponent } from './components/details-project/details-project.component';
import { AddProjectComponent } from './components/add-project/add-project.component';
import { ListProjectComponent } from './components/list-project/list-project.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { UpdateProjectComponent } from './components/update-project/update-project.component';
import { UpdateCustomerComponent } from './components/update-customer/update-customer.component'
;

@NgModule({
  declarations: [
    AppComponent,
    ListCustomerComponent,
    AddCustomerComponent,
    DetailsCustomerComponent,
    DetailsProjectComponent,
    AddProjectComponent,
    ListProjectComponent,
    UpdateProjectComponent,
    UpdateCustomerComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
