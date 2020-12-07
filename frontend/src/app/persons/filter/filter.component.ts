import { Component } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { DatePipe } from '@angular/common';

import { PersonsService } from '../../shared/persons/persons.service';

@Component({
  selector: 'app-filter',
  templateUrl: './filter.component.html',
  styleUrls: ['./filter.component.css'],
})
export class FilterComponent {
  genderList = [
    { code: 'MALE', label: 'Male' },
    { code: 'FEMALE', label: 'Female' },
  ];

  filter = new FormGroup({
    firstName: new FormControl(''),
    lastName: new FormControl(''),
    gender: new FormControl(''),
    fromDateOfBirth: new FormControl(''),
    toDateOfBirth: new FormControl(''),
  });

  constructor(private persons: PersonsService, private datepipe: DatePipe) {}

  onFind(): void {
    const { firstName, lastName, gender, fromDateOfBirth, toDateOfBirth } = this.filter.value;

    this.persons
      .fetchPersons({
        pageIndex: 0,
        firstName,
        lastName,
        gender,
        fromDateOfBirth: this.datepipe.transform(fromDateOfBirth, 'yyyy-MM-dd'),
        toDateOfBirth: this.datepipe.transform(toDateOfBirth, 'yyyy-MM-dd'),
      })
      .subscribe();
  }

  onReset(): void {
    this.filter.patchValue({
      firstName: '',
      lastName: '',
      gender: '',
      fromDateOfBirth: '',
      toDateOfBirth: '',
    });

    this.persons.fetchPersons().subscribe();
  }

  get submitAvailable(): boolean {
    const { firstName, lastName, gender, fromDateOfBirth, toDateOfBirth } = this.filter.value;

    return firstName?.length > 0 || lastName?.length > 0 || gender?.length > 0 || !!fromDateOfBirth || !!toDateOfBirth;
  }
}
