import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DetailsProjectComponent } from './details-project.component';

describe('DetailsProjectComponent', () => {
  let component: DetailsProjectComponent;
  let fixture: ComponentFixture<DetailsProjectComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [DetailsProjectComponent]
    });
    fixture = TestBed.createComponent(DetailsProjectComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
