import { IAppointment } from 'app/shared/model/appointment.model';

export interface IUserExtra {
  id?: number;
  phone?: string;
  addressId?: number;
  appointments?: IAppointment[];
}

export class UserExtra implements IUserExtra {
  constructor(public id?: number, public phone?: string, public addressId?: number, public appointments?: IAppointment[]) {}
}
