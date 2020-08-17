import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IProvidedService } from 'app/shared/model/provided-service.model';

@Component({
  selector: 'jhi-provided-service-detail',
  templateUrl: './provided-service-detail.component.html',
})
export class ProvidedServiceDetailComponent implements OnInit {
  providedService: IProvidedService | null = null;

  constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ providedService }) => (this.providedService = providedService));
  }

  byteSize(base64String: string): string {
    return this.dataUtils.byteSize(base64String);
  }

  openFile(contentType = '', base64String: string): void {
    this.dataUtils.openFile(contentType, base64String);
  }

  previousState(): void {
    window.history.back();
  }
}
