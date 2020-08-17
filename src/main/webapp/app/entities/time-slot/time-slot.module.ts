import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { FixEpochSharedModule } from 'app/shared/shared.module';
import { TimeSlotComponent } from './time-slot.component';
import { TimeSlotDetailComponent } from './time-slot-detail.component';
import { TimeSlotUpdateComponent } from './time-slot-update.component';
import { TimeSlotDeleteDialogComponent } from './time-slot-delete-dialog.component';
import { timeSlotRoute } from './time-slot.route';

@NgModule({
  imports: [FixEpochSharedModule, RouterModule.forChild(timeSlotRoute)],
  declarations: [TimeSlotComponent, TimeSlotDetailComponent, TimeSlotUpdateComponent, TimeSlotDeleteDialogComponent],
  entryComponents: [TimeSlotDeleteDialogComponent],
})
export class FixEpochTimeSlotModule {}
