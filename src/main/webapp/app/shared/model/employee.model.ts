export interface IEmployee {
  id?: number;
  firstName?: string;
  lastName?: string;
  employeImageContentType?: string;
  employeImage?: any;
  email?: string;
  phone?: string;
  registerdEmployeeId?: string;
  firmId?: number;
}

export class Employee implements IEmployee {
  constructor(
    public id?: number,
    public firstName?: string,
    public lastName?: string,
    public employeImageContentType?: string,
    public employeImage?: any,
    public email?: string,
    public phone?: string,
    public registerdEmployeeId?: string,
    public firmId?: number
  ) {}
}
