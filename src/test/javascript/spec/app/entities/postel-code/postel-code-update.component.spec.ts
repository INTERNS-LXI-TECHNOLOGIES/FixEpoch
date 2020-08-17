import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { FixEpochTestModule } from '../../../test.module';
import { PostelCodeUpdateComponent } from 'app/entities/postel-code/postel-code-update.component';
import { PostelCodeService } from 'app/entities/postel-code/postel-code.service';
import { PostelCode } from 'app/shared/model/postel-code.model';

describe('Component Tests', () => {
  describe('PostelCode Management Update Component', () => {
    let comp: PostelCodeUpdateComponent;
    let fixture: ComponentFixture<PostelCodeUpdateComponent>;
    let service: PostelCodeService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [FixEpochTestModule],
        declarations: [PostelCodeUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(PostelCodeUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(PostelCodeUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(PostelCodeService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new PostelCode(123);
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
        const entity = new PostelCode();
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
