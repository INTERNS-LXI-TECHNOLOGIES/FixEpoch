import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IProvidedService, ProvidedService } from 'app/shared/model/provided-service.model';
import { ProvidedServiceService } from './provided-service.service';
import { ProvidedServiceComponent } from './provided-service.component';
import { ProvidedServiceDetailComponent } from './provided-service-detail.component';
import { ProvidedServiceUpdateComponent } from './provided-service-update.component';

@Injectable({ providedIn: 'root' })
export class ProvidedServiceResolve implements Resolve<IProvidedService> {
  constructor(private service: ProvidedServiceService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IProvidedService> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((providedService: HttpResponse<ProvidedService>) => {
          if (providedService.body) {
            return of(providedService.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new ProvidedService());
  }
}

export const providedServiceRoute: Routes = [
  {
    path: '',
    component: ProvidedServiceComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'fixEpochApp.providedService.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: ProvidedServiceDetailComponent,
    resolve: {
      providedService: ProvidedServiceResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'fixEpochApp.providedService.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: ProvidedServiceUpdateComponent,
    resolve: {
      providedService: ProvidedServiceResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'fixEpochApp.providedService.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: ProvidedServiceUpdateComponent,
    resolve: {
      providedService: ProvidedServiceResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'fixEpochApp.providedService.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
