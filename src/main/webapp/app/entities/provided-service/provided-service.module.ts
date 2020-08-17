import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { FixEpochSharedModule } from 'app/shared/shared.module';
import { ProvidedServiceComponent } from './provided-service.component';
import { ProvidedServiceDetailComponent } from './provided-service-detail.component';
import { ProvidedServiceUpdateComponent } from './provided-service-update.component';
import { ProvidedServiceDeleteDialogComponent } from './provided-service-delete-dialog.component';
import { providedServiceRoute } from './provided-service.route';

@NgModule({
  imports: [FixEpochSharedModule, RouterModule.forChild(providedServiceRoute)],
  declarations: [
    ProvidedServiceComponent,
    ProvidedServiceDetailComponent,
    ProvidedServiceUpdateComponent,
    ProvidedServiceDeleteDialogComponent,
  ],
  entryComponents: [ProvidedServiceDeleteDialogComponent],
})
export class FixEpochProvidedServiceModule {}
