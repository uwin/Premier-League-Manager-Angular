import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ClubscreenComponent } from './clubscreen.component';

describe('ClubscreenComponent', () => {
  let component: ClubscreenComponent;
  let fixture: ComponentFixture<ClubscreenComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ClubscreenComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ClubscreenComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
