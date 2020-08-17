export interface ICity {
  id?: number;
  district?: string;
}

export class City implements ICity {
  constructor(public id?: number, public district?: string) {}
}
