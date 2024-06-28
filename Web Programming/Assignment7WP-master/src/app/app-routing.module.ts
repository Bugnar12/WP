import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {DisplayFileComponent} from "./display-file/display-file.component";
import {AddFileComponent} from "./add-file/add-file.component";
import {DeleteFileComponent} from "./delete-file/delete-file.component";
import {UpdateFileComponent} from "./update-file/update-file.component";

const routes: Routes = [
  {path: '', component: DisplayFileComponent},
  {path: 'display-file', component: DisplayFileComponent},
  {path: 'add-file', component: AddFileComponent},
  {path: 'delete-file', component: DeleteFileComponent},
  {path: 'update-file', component: UpdateFileComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
