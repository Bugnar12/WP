import {Component, OnInit} from '@angular/core';
import {GenericService} from "../generic.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-add-file',
  templateUrl: './add-file.component.html',
  styleUrl: './add-file.component.css'
})
export class AddFileComponent implements OnInit {
  protected title: string;
  protected format_type: string;
  protected genre: string;
  protected path: string;
  constructor(private service : GenericService, private router: Router){}
  ngOnInit(){}

  addFile(title: string, format_type: string, genre: string, path: string) {
    if (!title || !format_type || !genre || !path) {
      alert('All fields are required');
      return;
    }

    this.service.addFile(title, format_type, genre, path).subscribe(() => {
      this.router.navigate(['display-file']).then(_ => {
        console.log('Navigation Successful!')
      })
    })
  }

  goBack() {
      this.router.navigate(['display-file']).then(_ => {
        console.log('Navigation Successful!')
    })
  }
}
