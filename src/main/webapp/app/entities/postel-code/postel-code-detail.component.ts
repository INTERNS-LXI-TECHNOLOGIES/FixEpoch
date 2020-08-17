import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IPostelCode } from 'app/shared/model/postel-code.model';

@Component({
  selector: 'jhi-postel-code-detail',
  templateUrl: './postel-code-detail.component.html',
})
export class PostelCodeDetailComponent implements OnInit {
  postelCode: IPostelCode | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ postelCode }) => (this.postelCode = postelCode));
  }

  previousState(): void {
    window.history.back();
  }
}
