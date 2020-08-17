import { Component, OnInit, ElementRef } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { JhiDataUtils, JhiFileLoadError, JhiEventManager, JhiEventWithContent } from 'ng-jhipster';

import { IProvidedService, ProvidedService } from 'app/shared/model/provided-service.model';
import { ProvidedServiceService } from './provided-service.service';
import { AlertError } from 'app/shared/alert/alert-error.model';
import { IFirm } from 'app/shared/model/firm.model';
import { FirmService } from 'app/entities/firm/firm.service';

@Component({
  selector: 'jhi-provided-service-update',
  templateUrl: './provided-service-update.component.html',
})
export class ProvidedServiceUpdateComponent implements OnInit {
  isSaving = false;
  firms: IFirm[] = [];

  editForm = this.fb.group({
    id: [],
    name: [null, [Validators.required, Validators.maxLength(50)]],
    serviceImage: [],
    serviceImageContentType: [],
    firmId: [null, Validators.required],
  });

  constructor(
    protected dataUtils: JhiDataUtils,
    protected eventManager: JhiEventManager,
    protected providedServiceService: ProvidedServiceService,
    protected firmService: FirmService,
    protected elementRef: ElementRef,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ providedService }) => {
      this.updateForm(providedService);

      this.firmService.query().subscribe((res: HttpResponse<IFirm[]>) => (this.firms = res.body || []));
    });
  }

  updateForm(providedService: IProvidedService): void {
    this.editForm.patchValue({
      id: providedService.id,
      name: providedService.name,
      serviceImage: providedService.serviceImage,
      serviceImageContentType: providedService.serviceImageContentType,
      firmId: providedService.firmId,
    });
  }

  byteSize(base64String: string): string {
    return this.dataUtils.byteSize(base64String);
  }

  openFile(contentType: string, base64String: string): void {
    this.dataUtils.openFile(contentType, base64String);
  }

  setFileData(event: Event, field: string, isImage: boolean): void {
    this.dataUtils.loadFileToForm(event, this.editForm, field, isImage).subscribe(null, (err: JhiFileLoadError) => {
      this.eventManager.broadcast(
        new JhiEventWithContent<AlertError>('fixEpochApp.error', { ...err, key: 'error.file.' + err.key })
      );
    });
  }

  clearInputImage(field: string, fieldContentType: string, idInput: string): void {
    this.editForm.patchValue({
      [field]: null,
      [fieldContentType]: null,
    });
    if (this.elementRef && idInput && this.elementRef.nativeElement.querySelector('#' + idInput)) {
      this.elementRef.nativeElement.querySelector('#' + idInput).value = null;
    }
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const providedService = this.createFromForm();
    if (providedService.id !== undefined) {
      this.subscribeToSaveResponse(this.providedServiceService.update(providedService));
    } else {
      this.subscribeToSaveResponse(this.providedServiceService.create(providedService));
    }
  }

  private createFromForm(): IProvidedService {
    return {
      ...new ProvidedService(),
      id: this.editForm.get(['id'])!.value,
      name: this.editForm.get(['name'])!.value,
      serviceImageContentType: this.editForm.get(['serviceImageContentType'])!.value,
      serviceImage: this.editForm.get(['serviceImage'])!.value,
      firmId: this.editForm.get(['firmId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IProvidedService>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }

  trackById(index: number, item: IFirm): any {
    return item.id;
  }
}
