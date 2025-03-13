import { TestBed } from '@angular/core/testing';

import { PetFoodService } from './petfood.service';

describe('PetfoodService', () => {
  let service: PetFoodService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PetFoodService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
