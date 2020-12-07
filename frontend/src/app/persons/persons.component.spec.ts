import { TestBed } from '@angular/core/testing';
import { PersonsComponent } from './persons.component';

describe('PersonsComponent', () => {
  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [PersonsComponent]
    }).compileComponents();
  });

  it('should create the app', () => {
    const fixture = TestBed.createComponent(PersonsComponent);
    const app = fixture.componentInstance;
    expect(app).toBeTruthy();
  });
});
