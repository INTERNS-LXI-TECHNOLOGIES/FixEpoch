import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IPostelCode, PostelCode } from 'app/shared/model/postel-code.model';
import { PostelCodeService } from './postel-code.service';

@Component({
  selector: 'jhi-postel-code-update',
  templateUrl: './postel-code-update.component.html',
})
export class PostelCodeUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    postelCode: [],
  });

  constructor(protected postelCodeService: PostelCodeService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ postelCode }) => {
      this.updateForm(postelCode);
    });
  }

  updateForm(postelCode: IPostelCode): void {
    this.editForm.patchValue({
      id: postelCode.id,
      postelCode: postelCode.postelCode,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const postelCode = this.createFromForm();
    if (postelCode.id !== undefined) {
      this.subscribeToSaveResponse(this.postelCodeService.update(postelCode));
    } else {
      this.subscribeToSaveResponse(this.postelCodeService.create(postelCode));
    }
  }

  private createFromForm(): IPostelCode {
    return {
      ...new PostelCode(),
      id: this.editForm.get(['id'])!.value,
      postelCode: this.editForm.get(['postelCode'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IPostelCode>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }
}
