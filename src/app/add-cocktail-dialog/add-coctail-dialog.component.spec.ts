import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddCoctailDialogComponent } from './add-coctail-dialog.component';

describe('AddCoctailDialogComponent', () => {
  let component: AddCoctailDialogComponent;
  let fixture: ComponentFixture<AddCoctailDialogComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AddCoctailDialogComponent]
    });
    fixture = TestBed.createComponent(AddCoctailDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
