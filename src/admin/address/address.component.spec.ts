import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddressesComponent } from './address.component';

describe('AddressComponent', () => {
  let component: AddressesComponent;
  let fixture: ComponentFixture<AddressesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AddressesComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AddressesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
