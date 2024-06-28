import { Component, OnInit } from '@angular/core';
import {GenericService} from "../generic.service";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-update-file',
  templateUrl: './update-file.component.html',
  styleUrls: ['./update-file.component.css']
})
export class UpdateFileComponent implements OnInit {
  protected title: string;
  protected format_type: string;
  protected genre: string;
  protected path: string;
  protected dataLoaded = false;

  constructor(private service : GenericService, private router: Router, private route: ActivatedRoute){}

  ngOnInit() {
    this.route.queryParams.subscribe(params => {
      const fileId = params['id'];
      console.log("File ID: " + fileId)
      this.service.fetchFile(fileId).subscribe(file => {
        this.title = file.title;
        this.format_type = file.format_type;
        this.genre = file.genre;
        this.path = file.path;
        this.dataLoaded = true; // Set the flag to true here
      });
    });
  }

  updateFile(title: string, format_type: string, genre: string, path: string) {
    if (!title || !format_type || !genre || !path) {
      alert('All fields are required');
      return;
    }
    this.route.queryParams.subscribe(params => {
      this.service.updateFile(params['id'], title, format_type, genre, path).subscribe(() => {
        this.router.navigate(['display-file']).then(_ => {
          console.log('Navigation Successful!')
        })
      })
    })
  }

  onCancel() {
    this.router.navigate(['display-file']).then(_ => {
      console.log('Navigation Successful!')
    })
  }
}
