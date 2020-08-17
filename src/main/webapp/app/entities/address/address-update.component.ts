import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IAddress, Address } from 'app/shared/model/address.model';
import { AddressService } from './address.service';
import { IPostelCode } from 'app/shared/model/postel-code.model';
import { PostelCodeService } from 'app/entities/postel-code/postel-code.service';
import { ICity } from 'app/shared/model/city.model';
import { CityService } from 'app/entities/city/city.service';
import { IState } from 'app/shared/model/state.model';
import { StateService } from 'app/entities/state/state.service';

type SelectableEntity = IPostelCode | ICity | IState;

@Component({
  selector: 'jhi-address-update',
  templateUrl: './address-update.component.html',
})
export class AddressUpdateComponent implements OnInit {
  isSaving = false;
  postelcodes: IPostelCode[] = [];
  cities: ICity[] = [];
  states: IState[] = [];

  editForm = this.fb.group({
    id: [],
    locationAddressLineOne: [null, [Validators.maxLength(90)]],
    locationAddressLineTwo: [null, [Validators.maxLength(90)]],
    landMark: [null, [Validators.maxLength(90)]],
    postalCodeId: [null, Validators.required],
    cityId: [null, Validators.required],
    stateId: [null, Validators.required],
  });

  constructor(
    protected addressService: AddressService,
    protected postelCodeService: PostelCodeService,
    protected cityService: CityService,
    protected stateService: StateService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ address }) => {
      this.updateForm(address);

      this.postelCodeService.query().subscribe((res: HttpResponse<IPostelCode[]>) => (this.postelcodes = res.body || []));

      this.cityService.query().subscribe((res: HttpResponse<ICity[]>) => (this.cities = res.body || []));

      this.stateService.query().subscribe((res: HttpResponse<IState[]>) => (this.states = res.body || []));
    });
  }

  updateForm(address: IAddress): void {
    this.editForm.patchValue({
      id: address.id,
      locationAddressLineOne: address.locationAddressLineOne,
      locationAddressLineTwo: address.locationAddressLineTwo,
      landMark: address.landMark,
      postalCodeId: address.postalCodeId,
      cityId: address.cityId,
      stateId: address.stateId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const address = this.createFromForm();
    if (address.id !== undefined) {
      this.subscribeToSaveResponse(this.addressService.update(address));
    } else {
      this.subscribeToSaveResponse(this.addressService.create(address));
    }
  }

  private createFromForm(): IAddress {
    return {
      ...new Address(),
      id: this.editForm.get(['id'])!.value,
      locationAddressLineOne: this.editForm.get(['locationAddressLineOne'])!.value,
      locationAddressLineTwo: this.editForm.get(['locationAddressLineTwo'])!.value,
      landMark: this.editForm.get(['landMark'])!.value,
      postalCodeId: this.editForm.get(['postalCodeId'])!.value,
      cityId: this.editForm.get(['cityId'])!.value,
      stateId: this.editForm.get(['stateId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IAddress>>): void {
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
}
