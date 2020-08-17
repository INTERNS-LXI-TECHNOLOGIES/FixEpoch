import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ITimeSlot } from 'app/shared/model/time-slot.model';
import { TimeSlotService } from './time-slot.service';

@Component({
  templateUrl: './time-slot-delete-dialog.component.html',
})
export class TimeSlotDeleteDialogComponent {
  timeSlot?: ITimeSlot;

  constructor(protected timeSlotService: TimeSlotService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.timeSlotService.delete(id).subscribe(() => {
      this.eventManager.broadcast('timeSlotListModification');
      this.activeModal.close();
    });
  }
}
