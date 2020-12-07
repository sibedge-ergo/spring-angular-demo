import { Component, OnDestroy, OnInit } from '@angular/core';
import { PersonsService } from '../../shared/persons/persons.service';
import { MatTableDataSource } from '@angular/material/table';
import { Person } from '../../shared/persons/persons';

@Component({
  selector: 'app-persons-list',
  templateUrl: './persons-list.component.html',
  styleUrls: ['./persons-list.component.css'],
})
export class PersonsListComponent implements OnInit {
  columns: string[] = ['personalId', 'firstName', 'lastName', 'gender', 'dateOfBirth'];

  constructor(private persons: PersonsService) {}

  ngOnInit(): void {
    this.persons.fetchPersons({ page: 0 }).subscribe();
  }

  paginate({ pageIndex }): void {
    this.persons.fetchPersons({ page: pageIndex }).subscribe();
  }

  get personsList(): MatTableDataSource<Person> {
    return new MatTableDataSource<Person>(this.persons.persons);
  }

  get personsLoading(): boolean {
    return this.persons.loading;
  }

  get personsTotal(): number {
    return this.persons.totalCount;
  }

  get pageSize(): number {
    return this.persons.pageSize;
  }
}
