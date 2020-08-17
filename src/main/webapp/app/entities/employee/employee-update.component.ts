import { Component, OnInit, ElementRef } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { JhiDataUtils, JhiFileLoadError, JhiEventManager, JhiEventWithContent } from 'ng-jhipster';

import { IEmployee, Employee } from 'app/shared/model/employee.model';
import { EmployeeService } from './employee.service';
import { AlertError } from 'app/shared/alert/alert-error.model';
import { IFirm } from 'app/shared/model/firm.model';
import { FirmService } from 'app/entities/firm/firm.service';

@Component({
  selector: 'jhi-employee-update',
  templateUrl: './employee-update.component.html',
})
export class EmployeeUpdateComponent implements OnInit {
  isSaving = false;
  firms: IFirm[] = [];

  editForm = this.fb.group({
    id: [],
    firstName: [null, [Validators.required, Validators.maxLength(20)]],
    lastName: [null, [Validators.required, Validators.maxLength(20)]],
    employeImage: [],
    employeImageContentType: [],
    email: [null, [Validators.required, Validators.pattern('^([a-zA-Z0-9._%-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6})*$')]],
    phone: [null, [Validators.required, Validators.maxLength(11), Validators.pattern('^\\d+$')]],
    registerdEmployeeId: [null, [Validators.maxLength(5)]],
    firmId: [null, Validators.required],
  });

  constructor(
    protected dataUtils: JhiDataUtils,
    protected eventManager: JhiEventManager,
    protected employeeService: EmployeeService,
    protected firmService: FirmService,
    protected elementRef: ElementRef,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ employee }) => {
      this.updateForm(employee);

      this.firmService.query().subscribe((res: HttpResponse<IFirm[]>) => (this.firms = res.body || []));
    });
  }

  updateForm(employee: IEmployee): void {
    this.editForm.patchValue({
      id: employee.id,
      firstName: employee.firstName,
      lastName: employee.lastName,
      employeImage: employee.employeImage,
      employeImageContentType: employee.employeImageContentType,
      email: employee.email,
      phone: employee.phone,
      registerdEmployeeId: employee.registerdEmployeeId,
      firmId: employee.firmId,
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
    const employee = this.createFromForm();
    if (employee.id !== undefined) {
      this.subscribeToSaveResponse(this.employeeService.update(employee));
    } else {
      this.subscribeToSaveResponse(this.employeeService.create(employee));
    }
  }

  private createFromForm(): IEmployee {
    return {
      ...new Employee(),
      id: this.editForm.get(['id'])!.value,
      firstName: this.editForm.get(['firstName'])!.value,
      lastName: this.editForm.get(['lastName'])!.value,
      employeImageContentType: this.editForm.get(['employeImageContentType'])!.value,
      employeImage: this.editForm.get(['employeImage'])!.value,
      email: this.editForm.get(['email'])!.value,
      phone: this.editForm.get(['phone'])!.value,
      registerdEmployeeId: this.editForm.get(['registerdEmployeeId'])!.value,
      firmId: this.editForm.get(['firmId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IEmployee>>): void {
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
