import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IPostelCode, PostelCode } from 'app/shared/model/postel-code.model';
import { PostelCodeService } from './postel-code.service';
import { PostelCodeComponent } from './postel-code.component';
import { PostelCodeDetailComponent } from './postel-code-detail.component';
import { PostelCodeUpdateComponent } from './postel-code-update.component';

@Injectable({ providedIn: 'root' })
export class PostelCodeResolve implements Resolve<IPostelCode> {
  constructor(private service: PostelCodeService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IPostelCode> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((postelCode: HttpResponse<PostelCode>) => {
          if (postelCode.body) {
            return of(postelCode.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new PostelCode());
  }
}

export const postelCodeRoute: Routes = [
  {
    path: '',
    component: PostelCodeComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'fixEpochApp.postelCode.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: PostelCodeDetailComponent,
    resolve: {
      postelCode: PostelCodeResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'fixEpochApp.postelCode.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: PostelCodeUpdateComponent,
    resolve: {
      postelCode: PostelCodeResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'fixEpochApp.postelCode.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: PostelCodeUpdateComponent,
    resolve: {
      postelCode: PostelCodeResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'fixEpochApp.postelCode.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
