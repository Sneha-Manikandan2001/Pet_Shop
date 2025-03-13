import { TestBed } from '@angular/core/testing';

import { VaccinationService } from './vaccinations.service';

describe('VaccinationsService', () => {
  let service: VaccinationService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(VaccinationService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
