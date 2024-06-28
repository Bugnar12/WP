import {Component, OnInit} from '@angular/core';
import {GenericService} from "../generic.service";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-delete-file',
  templateUrl: './delete-file.component.html',
  styleUrl: './delete-file.component.css'
})
export class DeleteFileComponent implements OnInit{
  constructor(private service: GenericService, private router: Router, private route: ActivatedRoute) {}

  ngOnInit() {
  }

  onYes() {
    this.route.queryParams.subscribe(params => {
      console.log(params); // Add this line
      this.service.deleteFile(params['id']).subscribe(() => {
        this.router.navigate(['display-file']).then(_ => {
          console.log('Navigation Successful!')
        })
      })
    })
  }

  onNo() {
    this.router.navigate(['display-file']).then(_ => {
      console.log('Navigation Successful!')
    })
  }

}
