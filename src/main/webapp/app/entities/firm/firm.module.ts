import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { FixEpochSharedModule } from 'app/shared/shared.module';
import { FirmComponent } from './firm.component';
import { FirmDetailComponent } from './firm-detail.component';
import { FirmUpdateComponent } from './firm-update.component';
import { FirmDeleteDialogComponent } from './firm-delete-dialog.component';
import { firmRoute } from './firm.route';

@NgModule({
  imports: [FixEpochSharedModule, RouterModule.forChild(firmRoute)],
  declarations: [FirmComponent, FirmDetailComponent, FirmUpdateComponent, FirmDeleteDialogComponent],
  entryComponents: [FirmDeleteDialogComponent],
})
export class FixEpochFirmModule {}
