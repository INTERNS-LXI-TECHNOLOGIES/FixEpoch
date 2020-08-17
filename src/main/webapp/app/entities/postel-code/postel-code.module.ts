import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { FixEpochSharedModule } from 'app/shared/shared.module';
import { PostelCodeComponent } from './postel-code.component';
import { PostelCodeDetailComponent } from './postel-code-detail.component';
import { PostelCodeUpdateComponent } from './postel-code-update.component';
import { PostelCodeDeleteDialogComponent } from './postel-code-delete-dialog.component';
import { postelCodeRoute } from './postel-code.route';

@NgModule({
  imports: [FixEpochSharedModule, RouterModule.forChild(postelCodeRoute)],
  declarations: [PostelCodeComponent, PostelCodeDetailComponent, PostelCodeUpdateComponent, PostelCodeDeleteDialogComponent],
  entryComponents: [PostelCodeDeleteDialogComponent],
})
export class FixEpochPostelCodeModule {}
