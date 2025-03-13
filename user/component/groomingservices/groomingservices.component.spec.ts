import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GroomingservicesComponent } from './groomingservices.component';

describe('GroomingservicesComponent', () => {
  let component: GroomingservicesComponent;
  let fixture: ComponentFixture<GroomingservicesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [GroomingservicesComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(GroomingservicesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
