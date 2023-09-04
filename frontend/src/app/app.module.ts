import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { SmartHeaderComponent } from './components/smart-header/smart-header.component';
import { ProductsComponent } from './components/products/products.component';
import { LoginComponent } from './pages/login/login.component';
import { ProductModalComponent } from './components/product-modal/product-modal.component';
import { ProductListerComponent } from './components/product-lister/product-lister.component';
import { RegisterPageComponent } from './pages/register-page/register-page.component';
import { FormsModule } from '@angular/forms';

@NgModule({
  declarations: [
    AppComponent,
    SmartHeaderComponent,
    ProductsComponent,
    LoginComponent,
    ProductModalComponent,
    ProductListerComponent,
    RegisterPageComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
