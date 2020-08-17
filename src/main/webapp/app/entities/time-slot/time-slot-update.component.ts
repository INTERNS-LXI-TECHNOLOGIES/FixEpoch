import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { ITimeSlot, TimeSlot } from 'app/shared/model/time-slot.model';
import { TimeSlotService } from './time-slot.service';

@Component({
  selector: 'jhi-time-slot-update',
  templateUrl: './time-slot-update.component.html',
})
export class TimeSlotUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    startTime: [],
    endTime: [],
    timeSlotLabel: [],
  });

  constructor(protected timeSlotService: TimeSlotService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ timeSlot }) => {
      this.updateForm(timeSlot);
    });
  }

  updateForm(timeSlot: ITimeSlot): void {
    this.editForm.patchValue({
      id: timeSlot.id,
      startTime: timeSlot.startTime,
      endTime: timeSlot.endTime,
      timeSlotLabel: timeSlot.timeSlotLabel,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const timeSlot = this.createFromForm();
    if (timeSlot.id !== undefined) {
      this.subscribeToSaveResponse(this.timeSlotService.update(timeSlot));
    } else {
      this.subscribeToSaveResponse(this.timeSlotService.create(timeSlot));
    }
  }

  private createFromForm(): ITimeSlot {
    return {
      ...new TimeSlot(),
      id: this.editForm.get(['id'])!.value,
      startTime: this.editForm.get(['startTime'])!.value,
      endTime: this.editForm.get(['endTime'])!.value,
      timeSlotLabel: this.editForm.get(['timeSlotLabel'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITimeSlot>>): void {
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
}
