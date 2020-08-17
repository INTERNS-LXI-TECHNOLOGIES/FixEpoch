import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { FixEpochTestModule } from '../../../test.module';
import { PostelCodeDetailComponent } from 'app/entities/postel-code/postel-code-detail.component';
import { PostelCode } from 'app/shared/model/postel-code.model';

describe('Component Tests', () => {
  describe('PostelCode Management Detail Component', () => {
    let comp: PostelCodeDetailComponent;
    let fixture: ComponentFixture<PostelCodeDetailComponent>;
    const route = ({ data: of({ postelCode: new PostelCode(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [FixEpochTestModule],
        declarations: [PostelCodeDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(PostelCodeDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(PostelCodeDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load postelCode on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.postelCode).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
