import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GroomingServicesComponent } from './groomingservices.component';

describe('GroomingservicesComponent', () => {
  let component: GroomingServicesComponent;
  let fixture: ComponentFixture<GroomingServicesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [GroomingServicesComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(GroomingServicesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
