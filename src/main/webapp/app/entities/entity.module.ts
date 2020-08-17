import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'customer',
        loadChildren: () => import('./customer/customer.module').then(m => m.FixEpochCustomerModule),
      },
      {
        path: 'employee',
        loadChildren: () => import('./employee/employee.module').then(m => m.FixEpochEmployeeModule),
      },
      {
        path: 'category',
        loadChildren: () => import('./category/category.module').then(m => m.FixEpochCategoryModule),
      },
      {
        path: 'firm',
        loadChildren: () => import('./firm/firm.module').then(m => m.FixEpochFirmModule),
      },
      {
        path: 'provided-service',
        loadChildren: () => import('./provided-service/provided-service.module').then(m => m.FixEpochProvidedServiceModule),
      },
      {
        path: 'time-slot',
        loadChildren: () => import('./time-slot/time-slot.module').then(m => m.FixEpochTimeSlotModule),
      },
      {
        path: 'appointment',
        loadChildren: () => import('./appointment/appointment.module').then(m => m.FixEpochAppointmentModule),
      },
      {
        path: 'user-extra',
        loadChildren: () => import('./user-extra/user-extra.module').then(m => m.FixEpochUserExtraModule),
      },
      {
        path: 'address',
        loadChildren: () => import('./address/address.module').then(m => m.FixEpochAddressModule),
      },
      {
        path: 'postel-code',
        loadChildren: () => import('./postel-code/postel-code.module').then(m => m.FixEpochPostelCodeModule),
      },
      {
        path: 'city',
        loadChildren: () => import('./city/city.module').then(m => m.FixEpochCityModule),
      },
      {
        path: 'state',
        loadChildren: () => import('./state/state.module').then(m => m.FixEpochStateModule),
      },
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class FixEpochEntityModule {}
