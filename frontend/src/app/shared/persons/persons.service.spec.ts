import { TestBed } from '@angular/core/testing';
import { HttpClientModule } from '@angular/common/http';
import { Overlay } from '@angular/cdk/overlay';
import { DatePipe } from '@angular/common';

import { MatSnackBar } from '@angular/material/snack-bar';

import { PersonsService } from './persons.service';

describe('PersonsService', () => {
  let service: PersonsService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientModule],
      providers: [MatSnackBar, Overlay, DatePipe]
    });
    service = TestBed.inject(PersonsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
