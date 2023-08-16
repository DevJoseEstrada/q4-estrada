import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DetailsCustomerComponent } from './details-customer.component';

describe('DetailsCustomerComponent', () => {
  let component: DetailsCustomerComponent;
  let fixture: ComponentFixture<DetailsCustomerComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [DetailsCustomerComponent]
    });
    fixture = TestBed.createComponent(DetailsCustomerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
