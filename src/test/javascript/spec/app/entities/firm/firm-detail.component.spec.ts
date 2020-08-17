import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';
import { JhiDataUtils } from 'ng-jhipster';

import { FixEpochTestModule } from '../../../test.module';
import { FirmDetailComponent } from 'app/entities/firm/firm-detail.component';
import { Firm } from 'app/shared/model/firm.model';

describe('Component Tests', () => {
  describe('Firm Management Detail Component', () => {
    let comp: FirmDetailComponent;
    let fixture: ComponentFixture<FirmDetailComponent>;
    let dataUtils: JhiDataUtils;
    const route = ({ data: of({ firm: new Firm(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [FixEpochTestModule],
        declarations: [FirmDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(FirmDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(FirmDetailComponent);
      comp = fixture.componentInstance;
      dataUtils = fixture.debugElement.injector.get(JhiDataUtils);
    });

    describe('OnInit', () => {
      it('Should load firm on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.firm).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });

    describe('byteSize', () => {
      it('Should call byteSize from JhiDataUtils', () => {
        // GIVEN
        spyOn(dataUtils, 'byteSize');
        const fakeBase64 = 'fake base64';

        // WHEN
        comp.byteSize(fakeBase64);

        // THEN
        expect(dataUtils.byteSize).toBeCalledWith(fakeBase64);
      });
    });

    describe('openFile', () => {
      it('Should call openFile from JhiDataUtils', () => {
        // GIVEN
        spyOn(dataUtils, 'openFile');
        const fakeContentType = 'fake content type';
        const fakeBase64 = 'fake base64';

        // WHEN
        comp.openFile(fakeContentType, fakeBase64);

        // THEN
        expect(dataUtils.openFile).toBeCalledWith(fakeContentType, fakeBase64);
      });
    });
  });
});
