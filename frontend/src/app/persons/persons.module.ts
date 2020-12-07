import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { DatePipe } from '@angular/common';

import { MatCardModule } from '@angular/material/card';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';
import { MatButtonModule } from '@angular/material/button';
import { MatTableModule } from '@angular/material/table';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { MatPaginatorModule } from '@angular/material/paginator';

import { PersonsComponent } from './persons.component';
import { PersonsRoutingModule } from './persons-routing.module';
import { FilterComponent } from './filter/filter.component';
import { ReactiveFormsModule } from '@angular/forms';
import { PersonsListComponent } from './persons-list/persons-list.component';

@NgModule({
  imports: [
    CommonModule,
    PersonsRoutingModule,
    ReactiveFormsModule,

    MatCardModule,
    MatInputModule,
    MatSelectModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatButtonModule,
    MatProgressSpinnerModule,
    MatTableModule,
    MatPaginatorModule
  ],
  declarations: [PersonsComponent, FilterComponent, PersonsListComponent],
  providers: [MatDatepickerModule, DatePipe]
})
export class PersonsModule {}
