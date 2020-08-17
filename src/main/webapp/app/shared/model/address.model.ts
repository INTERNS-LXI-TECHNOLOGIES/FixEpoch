export interface IAddress {
  id?: number;
  locationAddressLineOne?: string;
  locationAddressLineTwo?: string;
  landMark?: string;
  postalCodeId?: number;
  cityId?: number;
  stateId?: number;
  userExtraId?: number;
}

export class Address implements IAddress {
  constructor(
    public id?: number,
    public locationAddressLineOne?: string,
    public locationAddressLineTwo?: string,
    public landMark?: string,
    public postalCodeId?: number,
    public cityId?: number,
    public stateId?: number,
    public userExtraId?: number
  ) {}
}
