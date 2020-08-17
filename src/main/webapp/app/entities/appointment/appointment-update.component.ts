import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IAppointment, Appointment } from 'app/shared/model/appointment.model';
import { AppointmentService } from './appointment.service';
import { ITimeSlot } from 'app/shared/model/time-slot.model';
import { TimeSlotService } from 'app/entities/time-slot/time-slot.service';
import { IEmployee } from 'app/shared/model/employee.model';
import { EmployeeService } from 'app/entities/employee/employee.service';
import { IProvidedService } from 'app/shared/model/provided-service.model';
import { ProvidedServiceService } from 'app/entities/provided-service/provided-service.service';
import { IFirm } from 'app/shared/model/firm.model';
import { FirmService } from 'app/entities/firm/firm.service';
import { IUserExtra } from 'app/shared/model/user-extra.model';
import { UserExtraService } from 'app/entities/user-extra/user-extra.service';

type SelectableEntity = ITimeSlot | IEmployee | IProvidedService | IFirm | IUserExtra;

@Component({
  selector: 'jhi-appointment-update',
  templateUrl: './appointment-update.component.html',
})
export class AppointmentUpdateComponent implements OnInit {
  isSaving = false;
  timeslots: ITimeSlot[] = [];
  employees: IEmployee[] = [];
  providedservices: IProvidedService[] = [];
  firms: IFirm[] = [];
  userextras: IUserExtra[] = [];
  dateDp: any;

  editForm = this.fb.group({
    id: [],
    date: [null, [Validators.required]],
    appointmentStatus: [null, [Validators.required]],
    timeSlotId: [null, Validators.required],
    employeeId: [null, Validators.required],
    providedServiceId: [null, Validators.required],
    firmId: [null, Validators.required],
    userExtraId: [null, Validators.required],
  });

  constructor(
    protected appointmentService: AppointmentService,
    protected timeSlotService: TimeSlotService,
    protected employeeService: EmployeeService,
    protected providedServiceService: ProvidedServiceService,
    protected firmService: FirmService,
    protected userExtraService: UserExtraService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ appointment }) => {
      this.updateForm(appointment);

      this.timeSlotService.query().subscribe((res: HttpResponse<ITimeSlot[]>) => (this.timeslots = res.body || []));

      this.employeeService.query().subscribe((res: HttpResponse<IEmployee[]>) => (this.employees = res.body || []));

      this.providedServiceService.query().subscribe((res: HttpResponse<IProvidedService[]>) => (this.providedservices = res.body || []));

      this.firmService.query().subscribe((res: HttpResponse<IFirm[]>) => (this.firms = res.body || []));

      this.userExtraService.query().subscribe((res: HttpResponse<IUserExtra[]>) => (this.userextras = res.body || []));
    });
  }

  updateForm(appointment: IAppointment): void {
    this.editForm.patchValue({
      id: appointment.id,
      date: appointment.date,
      appointmentStatus: appointment.appointmentStatus,
      timeSlotId: appointment.timeSlotId,
      employeeId: appointment.employeeId,
      providedServiceId: appointment.providedServiceId,
      firmId: appointment.firmId,
      userExtraId: appointment.userExtraId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const appointment = this.createFromForm();
    if (appointment.id !== undefined) {
      this.subscribeToSaveResponse(this.appointmentService.update(appointment));
    } else {
      this.subscribeToSaveResponse(this.appointmentService.create(appointment));
    }
  }

  private createFromForm(): IAppointment {
    return {
      ...new Appointment(),
      id: this.editForm.get(['id'])!.value,
      date: this.editForm.get(['date'])!.value,
      appointmentStatus: this.editForm.get(['appointmentStatus'])!.value,
      timeSlotId: this.editForm.get(['timeSlotId'])!.value,
      employeeId: this.editForm.get(['employeeId'])!.value,
      providedServiceId: this.editForm.get(['providedServiceId'])!.value,
      firmId: this.editForm.get(['firmId'])!.value,
      userExtraId: this.editForm.get(['userExtraId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IAppointment>>): void {
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
