export interface IState {
  id?: number;
  state?: string;
}

export class State implements IState {
  constructor(public id?: number, public state?: string) {}
}
