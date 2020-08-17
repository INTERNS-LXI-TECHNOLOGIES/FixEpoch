import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ITimeSlot, TimeSlot } from 'app/shared/model/time-slot.model';
import { TimeSlotService } from './time-slot.service';
import { TimeSlotComponent } from './time-slot.component';
import { TimeSlotDetailComponent } from './time-slot-detail.component';
import { TimeSlotUpdateComponent } from './time-slot-update.component';

@Injectable({ providedIn: 'root' })
export class TimeSlotResolve implements Resolve<ITimeSlot> {
  constructor(private service: TimeSlotService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ITimeSlot> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((timeSlot: HttpResponse<TimeSlot>) => {
          if (timeSlot.body) {
            return of(timeSlot.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new TimeSlot());
  }
}

export const timeSlotRoute: Routes = [
  {
    path: '',
    component: TimeSlotComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'fixEpochApp.timeSlot.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: TimeSlotDetailComponent,
    resolve: {
      timeSlot: TimeSlotResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'fixEpochApp.timeSlot.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: TimeSlotUpdateComponent,
    resolve: {
      timeSlot: TimeSlotResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'fixEpochApp.timeSlot.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: TimeSlotUpdateComponent,
    resolve: {
      timeSlot: TimeSlotResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'fixEpochApp.timeSlot.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
