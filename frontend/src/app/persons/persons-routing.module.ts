import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { PersonsComponent } from './persons.component';

const adminRoutes: Routes = [
  {
    path: '',
    component: PersonsComponent,
  },
];

@NgModule({
  imports: [RouterModule.forChild(adminRoutes)],
  exports: [RouterModule],
})
export class PersonsRoutingModule {}
