export interface IProvidedService {
  id?: number;
  name?: string;
  serviceImageContentType?: string;
  serviceImage?: any;
  firmId?: number;
}

export class ProvidedService implements IProvidedService {
  constructor(
    public id?: number,
    public name?: string,
    public serviceImageContentType?: string,
    public serviceImage?: any,
    public firmId?: number
  ) {}
}
