import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PetFoodComponent } from './petfood.component';

describe('PetfoodComponent', () => {
  let component: PetFoodComponent;
  let fixture: ComponentFixture<PetFoodComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PetFoodComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PetFoodComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
