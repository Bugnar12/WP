import {Component, OnInit} from '@angular/core';
import {GenericService} from "../generic.service";
import {File} from "../File";
import {Router} from "@angular/router";

@Component({
  selector: 'app-display-file',
  templateUrl: './display-file.component.html',
  styleUrl: './display-file.component.css'
})
export class DisplayFileComponent implements OnInit{
  files : File[] = []
  genreFilter: string;
  constructor(private service : GenericService, private router: Router) {}

  ngOnInit() {
    console.log("called ng OnInit")
    this.getFiles();
  }

  getFiles() {
    this.service.fetchFiles().subscribe(files => this.files = files);
  }

  filterByGenre(){
    this.service.filterByGenre(this.genreFilter).subscribe(files => {
      this.files = files;
    })
  }

  navigateToAdd() {
    this.router.navigate(['add-file']).then(_ => {
      console.log('Navigation Successful!')
    });
  }

  navigateToDelete(fileId: number) {
    this.router.navigate(['delete-file'], {queryParams: {id: fileId}}).then(_ => {
      console.log('Navigation Successful!')
    });
  }

  navigateToUpdate(fileId: number)
  {
    this.router.navigate(['update-file'], {queryParams: {id: fileId}}).then(_ => {
      console.log('Navigation Successful!')
    });
  }

}
