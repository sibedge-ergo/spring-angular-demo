import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { catchError, map } from 'rxjs/operators';
import { Observable, of } from 'rxjs';
import { MatSnackBar } from '@angular/material/snack-bar';

import { Person, PersonsRequestParams, PersonsResponse } from './persons';

@Injectable({
  providedIn: 'root',
})
export class PersonsService {
  private personsUri = '/api/persons';

  private filter = {};

  loading = false;
  persons: Person[] = [];
  totalCount = 0;
  pageSize = 10;
  currentPageIndex = 0;

  constructor(private http: HttpClient, private snackbar: MatSnackBar) {}

  private getRequestParams(): string {
    const search = new URLSearchParams();

    Object.keys(this.filter).forEach((key: string) => {
      if (this.filter[key]) {
        search.append(key, this.filter[key]);
      }
    });

    return search.toString();
  }

  fetchPersons(params?: Partial<PersonsRequestParams>): Observable<PersonsResponse> {
    this.loading = true;

    let url = this.personsUri;
    const { pageIndex, ...rest } = params ?? {};

    if (params) {
      this.filter = {
        ...this.filter,
        ...rest,
        page: pageIndex
      };
    } else {
      this.filter = {
        page: pageIndex
      };
    }

    url += `?${this.getRequestParams()}&size=${this.pageSize}`;

    return this.http.get<PersonsResponse>(url).pipe(

      catchError(({ error }) => {
        this.snackbar.open(error.errors.join('. '), 'Close', { panelClass: 'snackbar-container' });

        this.loading = false;

        return of(error);
      }),
      map((response: PersonsResponse) => {
        const { total, content } = response;

        this.persons = content;
        this.totalCount = total;
        this.currentPageIndex = pageIndex;

        this.loading = false;

        return response;
      })
    );
  }
}
