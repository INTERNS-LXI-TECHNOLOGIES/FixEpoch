export interface IPostelCode {
  id?: number;
  postelCode?: string;
}

export class PostelCode implements IPostelCode {
  constructor(public id?: number, public postelCode?: string) {}
}
