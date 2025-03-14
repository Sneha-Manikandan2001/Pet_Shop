import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UPetfoodComponent } from './petfood.component';

describe('PetfoodComponent', () => {
  let component: UPetfoodComponent;
  let fixture: ComponentFixture<UPetfoodComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [UPetfoodComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(UPetfoodComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
