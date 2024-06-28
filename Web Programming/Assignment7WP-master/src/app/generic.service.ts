import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders, HttpRequest, HttpResponse} from "@angular/common/http";
import {catchError, filter, map, Observable, of} from "rxjs";
import {File} from "./File";

@Injectable({
  providedIn: 'root'
})
export class GenericService {
  httpOptions = {
    headers : new HttpHeaders({
      'Content-Type': 'application/json',
    })
  };

  private url = 'http://localhost/Assignment6_WP/php/crud/';
  private filterUrl = 'http://localhost/Assignment6_WP/php/filter/'
  constructor(private http : HttpClient) { };

  fetchFiles() : Observable<File[]>{
    return this.http.get<File[]>(this.url + 'read.php')
      .pipe(catchError(this.handleError<File[]>('fetchFiles', [])))
  }

  addFile(title: string, format_type: string, genre: string, path: string) : Observable<File> {
    return this.http.post<File>(this.url + 'create.php', {title, format_type, genre, path}, this.httpOptions)
      .pipe(catchError(this.handleError<File>('addFile')))
  }

  deleteFile(fileId: number): Observable<any> {
    return this.http.post(this.url + `delete.php`, {id: fileId}, this.httpOptions);
  }

  updateFile(idChanged: number, titleChanged: string, format_typeChanged: string, genreChanged: string, pathChanged: string): Observable<File> {
    return this.http.put<File>(this.url + 'update.php', {id: idChanged, title: titleChanged, format_type: format_typeChanged, genre: genreChanged, path: pathChanged}, this.httpOptions)
      .pipe(catchError(this.handleError<File>('updateFile')))
  }

  fetchFile(fileId: number): Observable<File> {
    return this.http.get<File>(`${this.url}read_one.php?id=${fileId}`, this.httpOptions)
      .pipe(catchError(this.handleError<File>('fetchFile')));
  }

  filterByGenre(genre: string): Observable<File[]> {
    return this.http.post<File[]>(this.filterUrl + 'getAllFilesByGenre.php', {genre}, this.httpOptions)
      .pipe(catchError(this.handleError<File[]>('filterByGenre', [])))
  }

  private handleError<T>(operation = 'operation', result?: T): (error: any) => Observable<T> {
    return (error: any): Observable<T> => {
      console.error(error);
      return of(result as T);
    };
  }


}
