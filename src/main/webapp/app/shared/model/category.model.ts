import { IFirm } from 'app/shared/model/firm.model';

export interface ICategory {
  id?: number;
  name?: string;
  description?: string;
  categoryImageContentType?: string;
  categoryImage?: any;
  firms?: IFirm[];
}

export class Category implements ICategory {
  constructor(
    public id?: number,
    public name?: string,
    public description?: string,
    public categoryImageContentType?: string,
    public categoryImage?: any,
    public firms?: IFirm[]
  ) {}
}
