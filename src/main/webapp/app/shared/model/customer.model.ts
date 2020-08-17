import { IFirm } from 'app/shared/model/firm.model';

export interface ICustomer {
  id?: number;
  firstName?: string;
  lastName?: string;
  customerImageContentType?: string;
  customerImage?: any;
  email?: string;
  phone?: string;
  addressId?: number;
  firms?: IFirm[];
}

export class Customer implements ICustomer {
  constructor(
    public id?: number,
    public firstName?: string,
    public lastName?: string,
    public customerImageContentType?: string,
    public customerImage?: any,
    public email?: string,
    public phone?: string,
    public addressId?: number,
    public firms?: IFirm[]
  ) {}
}
