import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UVaccinationsComponent } from './vaccinations.component';

describe('VaccinationsComponent', () => {
  let component: UVaccinationsComponent;
  let fixture: ComponentFixture<UVaccinationsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [UVaccinationsComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(UVaccinationsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
