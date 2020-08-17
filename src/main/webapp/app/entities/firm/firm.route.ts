import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IFirm, Firm } from 'app/shared/model/firm.model';
import { FirmService } from './firm.service';
import { FirmComponent } from './firm.component';
import { FirmDetailComponent } from './firm-detail.component';
import { FirmUpdateComponent } from './firm-update.component';

@Injectable({ providedIn: 'root' })
export class FirmResolve implements Resolve<IFirm> {
  constructor(private service: FirmService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IFirm> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((firm: HttpResponse<Firm>) => {
          if (firm.body) {
            return of(firm.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Firm());
  }
}

export const firmRoute: Routes = [
  {
    path: '',
    component: FirmComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'fixEpochApp.firm.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: FirmDetailComponent,
    resolve: {
      firm: FirmResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'fixEpochApp.firm.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: FirmUpdateComponent,
    resolve: {
      firm: FirmResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'fixEpochApp.firm.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: FirmUpdateComponent,
    resolve: {
      firm: FirmResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'fixEpochApp.firm.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
