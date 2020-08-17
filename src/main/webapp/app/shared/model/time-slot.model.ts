import { IFirm } from 'app/shared/model/firm.model';
import { TimeSlotLabel } from 'app/shared/model/enumerations/time-slot-label.model';

export interface ITimeSlot {
  id?: number;
  startTime?: string;
  endTime?: string;
  timeSlotLabel?: TimeSlotLabel;
  firms?: IFirm[];
}

export class TimeSlot implements ITimeSlot {
  constructor(
    public id?: number,
    public startTime?: string,
    public endTime?: string,
    public timeSlotLabel?: TimeSlotLabel,
    public firms?: IFirm[]
  ) {}
}
