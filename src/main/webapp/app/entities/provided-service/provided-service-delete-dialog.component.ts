import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IProvidedService } from 'app/shared/model/provided-service.model';
import { ProvidedServiceService } from './provided-service.service';

@Component({
  templateUrl: './provided-service-delete-dialog.component.html',
})
export class ProvidedServiceDeleteDialogComponent {
  providedService?: IProvidedService;

  constructor(
    protected providedServiceService: ProvidedServiceService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.providedServiceService.delete(id).subscribe(() => {
      this.eventManager.broadcast('providedServiceListModification');
      this.activeModal.close();
    });
  }
}
