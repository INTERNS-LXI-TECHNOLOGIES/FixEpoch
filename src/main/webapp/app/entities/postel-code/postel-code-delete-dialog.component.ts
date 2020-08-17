import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IPostelCode } from 'app/shared/model/postel-code.model';
import { PostelCodeService } from './postel-code.service';

@Component({
  templateUrl: './postel-code-delete-dialog.component.html',
})
export class PostelCodeDeleteDialogComponent {
  postelCode?: IPostelCode;

  constructor(
    protected postelCodeService: PostelCodeService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.postelCodeService.delete(id).subscribe(() => {
      this.eventManager.broadcast('postelCodeListModification');
      this.activeModal.close();
    });
  }
}
