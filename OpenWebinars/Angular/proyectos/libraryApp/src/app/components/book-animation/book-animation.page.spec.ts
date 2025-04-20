import { ComponentFixture, TestBed } from '@angular/core/testing';
import { BookAnimationPage } from './book-animation.page';

describe('BookAnimationPage', () => {
  let component: BookAnimationPage;
  let fixture: ComponentFixture<BookAnimationPage>;

  beforeEach(() => {
    fixture = TestBed.createComponent(BookAnimationPage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
