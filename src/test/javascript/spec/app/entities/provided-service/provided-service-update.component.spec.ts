import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { FixEpochTestModule } from '../../../test.module';
import { ProvidedServiceUpdateComponent } from 'app/entities/provided-service/provided-service-update.component';
import { ProvidedServiceService } from 'app/entities/provided-service/provided-service.service';
import { ProvidedService } from 'app/shared/model/provided-service.model';

describe('Component Tests', () => {
  describe('ProvidedService Management Update Component', () => {
    let comp: ProvidedServiceUpdateComponent;
    let fixture: ComponentFixture<ProvidedServiceUpdateComponent>;
    let service: ProvidedServiceService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [FixEpochTestModule],
        declarations: [ProvidedServiceUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(ProvidedServiceUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ProvidedServiceUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ProvidedServiceService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new ProvidedService(123);
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
        const entity = new ProvidedService();
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
