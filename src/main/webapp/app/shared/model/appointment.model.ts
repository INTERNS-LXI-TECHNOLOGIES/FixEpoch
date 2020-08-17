import { Moment } from 'moment';
import { AppointmentVerifiedStatus } from 'app/shared/model/enumerations/appointment-verified-status.model';

export interface IAppointment {
  id?: number;
  date?: Moment;
  appointmentStatus?: AppointmentVerifiedStatus;
  timeSlotId?: number;
  employeeId?: number;
  providedServiceId?: number;
  firmId?: number;
  userExtraId?: number;
}

export class Appointment implements IAppointment {
  constructor(
    public id?: number,
    public date?: Moment,
    public appointmentStatus?: AppointmentVerifiedStatus,
    public timeSlotId?: number,
    public employeeId?: number,
    public providedServiceId?: number,
    public firmId?: number,
    public userExtraId?: number
  ) {}
}
