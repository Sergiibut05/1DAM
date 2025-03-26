import { TestBed } from '@angular/core/testing';
import { HttpClientModule } from '@angular/common/http';
import { InfoService } from './info.service';

describe('InfoServiceService', () => {
  let service: InfoService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientModule]
    });
    service = TestBed.inject(InfoService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
