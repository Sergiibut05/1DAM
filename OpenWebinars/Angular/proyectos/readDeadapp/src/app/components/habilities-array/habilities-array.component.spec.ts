import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HabilitiesArrayComponent } from './habilities-array.component';

describe('HabilitiesArrayComponent', () => {
  let component: HabilitiesArrayComponent;
  let fixture: ComponentFixture<HabilitiesArrayComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [HabilitiesArrayComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(HabilitiesArrayComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
