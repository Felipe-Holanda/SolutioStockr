import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SmartHeaderComponent } from './smart-header.component';

describe('SmartHeaderComponent', () => {
  let component: SmartHeaderComponent;
  let fixture: ComponentFixture<SmartHeaderComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [SmartHeaderComponent]
    });
    fixture = TestBed.createComponent(SmartHeaderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
