import { NgModule } from '@angular/core';
import { BrowserModule, provideClientHydration } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { DisplayFileComponent } from './display-file/display-file.component';
import {HttpClientModule, provideHttpClient, withFetch} from "@angular/common/http";
import { AddFileComponent } from './add-file/add-file.component';
import { DeleteFileComponent } from './delete-file/delete-file.component';
import { UpdateFileComponent } from './update-file/update-file.component';
import {FormsModule} from "@angular/forms";

@NgModule({
  declarations: [
    AppComponent,
      DisplayFileComponent,
      AddFileComponent,
      DeleteFileComponent,
      UpdateFileComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [provideHttpClient(withFetch())],
  bootstrap: [AppComponent]
})
export class AppModule { }
