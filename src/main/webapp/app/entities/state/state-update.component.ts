import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IState, State } from 'app/shared/model/state.model';
import { StateService } from './state.service';

@Component({
  selector: 'jhi-state-update',
  templateUrl: './state-update.component.html',
})
export class StateUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    state: [null, [Validators.maxLength(30)]],
  });

  constructor(protected stateService: StateService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ state }) => {
      this.updateForm(state);
    });
  }

  updateForm(state: IState): void {
    this.editForm.patchValue({
      id: state.id,
      state: state.state,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const state = this.createFromForm();
    if (state.id !== undefined) {
      this.subscribeToSaveResponse(this.stateService.update(state));
    } else {
      this.subscribeToSaveResponse(this.stateService.create(state));
    }
  }

  private createFromForm(): IState {
    return {
      ...new State(),
      id: this.editForm.get(['id'])!.value,
      state: this.editForm.get(['state'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IState>>): void {
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
