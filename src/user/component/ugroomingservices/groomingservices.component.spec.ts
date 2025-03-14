import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UGroomingServicesComponent } from './groomingservices.component';

describe('GroomingservicesComponent', () => {
  let component: UGroomingServicesComponent;
  let fixture: ComponentFixture<UGroomingServicesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [UGroomingServicesComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(UGroomingServicesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
