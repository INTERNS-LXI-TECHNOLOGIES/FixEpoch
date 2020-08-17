import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { FixEpochTestModule } from '../../../test.module';
import { FirmUpdateComponent } from 'app/entities/firm/firm-update.component';
import { FirmService } from 'app/entities/firm/firm.service';
import { Firm } from 'app/shared/model/firm.model';

describe('Component Tests', () => {
  describe('Firm Management Update Component', () => {
    let comp: FirmUpdateComponent;
    let fixture: ComponentFixture<FirmUpdateComponent>;
    let service: FirmService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [FixEpochTestModule],
        declarations: [FirmUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(FirmUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(FirmUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(FirmService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Firm(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new Firm();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
