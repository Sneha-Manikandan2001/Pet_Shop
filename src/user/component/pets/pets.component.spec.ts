import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UPetsComponent } from './pets.component';

describe('PetsComponent', () => {
  let component: UPetsComponent;
  let fixture: ComponentFixture<UPetsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [UPetsComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(UPetsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
