import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CoctailPageComponent } from './coctail-page.component';

describe('CoctailPageComponent', () => {
  let component: CoctailPageComponent;
  let fixture: ComponentFixture<CoctailPageComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [CoctailPageComponent]
    });
    fixture = TestBed.createComponent(CoctailPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
