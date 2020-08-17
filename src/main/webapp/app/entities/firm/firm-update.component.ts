import { Component, OnInit, ElementRef } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { JhiDataUtils, JhiFileLoadError, JhiEventManager, JhiEventWithContent } from 'ng-jhipster';

import { IFirm, Firm } from 'app/shared/model/firm.model';
import { FirmService } from './firm.service';
import { AlertError } from 'app/shared/alert/alert-error.model';
import { IAddress } from 'app/shared/model/address.model';
import { AddressService } from 'app/entities/address/address.service';
import { ITimeSlot } from 'app/shared/model/time-slot.model';
import { TimeSlotService } from 'app/entities/time-slot/time-slot.service';
import { ICategory } from 'app/shared/model/category.model';
import { CategoryService } from 'app/entities/category/category.service';
import { ICustomer } from 'app/shared/model/customer.model';
import { CustomerService } from 'app/entities/customer/customer.service';

type SelectableEntity = IAddress | ITimeSlot | ICategory | ICustomer;

@Component({
  selector: 'jhi-firm-update',
  templateUrl: './firm-update.component.html',
})
export class FirmUpdateComponent implements OnInit {
  isSaving = false;
  addresses: IAddress[] = [];
  timeslots: ITimeSlot[] = [];
  categories: ICategory[] = [];
  customers: ICustomer[] = [];

  editForm = this.fb.group({
    id: [],
    name: [null, [Validators.required, Validators.maxLength(30)]],
    description: [],
    image: [],
    imageContentType: [],
    addressId: [null, Validators.required],
    timeslots: [null, Validators.required],
    categoryId: [null, Validators.required],
    customerId: [null, Validators.required],
  });

  constructor(
    protected dataUtils: JhiDataUtils,
    protected eventManager: JhiEventManager,
    protected firmService: FirmService,
    protected addressService: AddressService,
    protected timeSlotService: TimeSlotService,
    protected categoryService: CategoryService,
    protected customerService: CustomerService,
    protected elementRef: ElementRef,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ firm }) => {
      this.updateForm(firm);

      this.addressService
        .query({ filter: 'firm-is-null' })
        .pipe(
          map((res: HttpResponse<IAddress[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: IAddress[]) => {
          if (!firm.addressId) {
            this.addresses = resBody;
          } else {
            this.addressService
              .find(firm.addressId)
              .pipe(
                map((subRes: HttpResponse<IAddress>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: IAddress[]) => (this.addresses = concatRes));
          }
        });

      this.timeSlotService.query().subscribe((res: HttpResponse<ITimeSlot[]>) => (this.timeslots = res.body || []));

      this.categoryService.query().subscribe((res: HttpResponse<ICategory[]>) => (this.categories = res.body || []));

      this.customerService.query().subscribe((res: HttpResponse<ICustomer[]>) => (this.customers = res.body || []));
    });
  }

  updateForm(firm: IFirm): void {
    this.editForm.patchValue({
      id: firm.id,
      name: firm.name,
      description: firm.description,
      image: firm.image,
      imageContentType: firm.imageContentType,
      addressId: firm.addressId,
      timeslots: firm.timeslots,
      categoryId: firm.categoryId,
      customerId: firm.customerId,
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
    const firm = this.createFromForm();
    if (firm.id !== undefined) {
      this.subscribeToSaveResponse(this.firmService.update(firm));
    } else {
      this.subscribeToSaveResponse(this.firmService.create(firm));
    }
  }

  private createFromForm(): IFirm {
    return {
      ...new Firm(),
      id: this.editForm.get(['id'])!.value,
      name: this.editForm.get(['name'])!.value,
      description: this.editForm.get(['description'])!.value,
      imageContentType: this.editForm.get(['imageContentType'])!.value,
      image: this.editForm.get(['image'])!.value,
      addressId: this.editForm.get(['addressId'])!.value,
      timeslots: this.editForm.get(['timeslots'])!.value,
      categoryId: this.editForm.get(['categoryId'])!.value,
      customerId: this.editForm.get(['customerId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IFirm>>): void {
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

  trackById(index: number, item: SelectableEntity): any {
    return item.id;
  }

  getSelected(selectedVals: ITimeSlot[], option: ITimeSlot): ITimeSlot {
    if (selectedVals) {
      for (let i = 0; i < selectedVals.length; i++) {
        if (option.id === selectedVals[i].id) {
          return selectedVals[i];
        }
      }
    }
    return option;
  }
}
