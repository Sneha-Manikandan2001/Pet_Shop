import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PetcategoriesComponent } from './petcategories.component';

describe('PetcategoriesComponent', () => {
  let component: PetcategoriesComponent;
  let fixture: ComponentFixture<PetcategoriesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PetcategoriesComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PetcategoriesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
