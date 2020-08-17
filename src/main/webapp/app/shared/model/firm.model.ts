import { IProvidedService } from 'app/shared/model/provided-service.model';
import { IAppointment } from 'app/shared/model/appointment.model';
import { IEmployee } from 'app/shared/model/employee.model';
import { ITimeSlot } from 'app/shared/model/time-slot.model';

export interface IFirm {
  id?: number;
  name?: string;
  description?: string;
  imageContentType?: string;
  image?: any;
  addressId?: number;
  providedServices?: IProvidedService[];
  appointments?: IAppointment[];
  employees?: IEmployee[];
  timeslots?: ITimeSlot[];
  categoryId?: number;
  customerId?: number;
}

export class Firm implements IFirm {
  constructor(
    public id?: number,
    public name?: string,
    public description?: string,
    public imageContentType?: string,
    public image?: any,
    public addressId?: number,
    public providedServices?: IProvidedService[],
    public appointments?: IAppointment[],
    public employees?: IEmployee[],
    public timeslots?: ITimeSlot[],
    public categoryId?: number,
    public customerId?: number
  ) {}
}
