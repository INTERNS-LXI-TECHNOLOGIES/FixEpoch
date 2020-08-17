import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IFirm } from 'app/shared/model/firm.model';
import { FirmService } from './firm.service';

@Component({
  templateUrl: './firm-delete-dialog.component.html',
})
export class FirmDeleteDialogComponent {
  firm?: IFirm;

  constructor(protected firmService: FirmService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.firmService.delete(id).subscribe(() => {
      this.eventManager.broadcast('firmListModification');
      this.activeModal.close();
    });
  }
}
