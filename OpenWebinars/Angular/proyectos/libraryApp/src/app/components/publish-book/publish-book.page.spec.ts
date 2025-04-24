import { ComponentFixture, TestBed } from '@angular/core/testing';
import { PublishBookPage } from './publish-book.page';

describe('PublishBookPage', () => {
  let component: PublishBookPage;
  let fixture: ComponentFixture<PublishBookPage>;

  beforeEach(() => {
    fixture = TestBed.createComponent(PublishBookPage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
