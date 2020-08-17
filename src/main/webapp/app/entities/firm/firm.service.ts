import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IFirm } from 'app/shared/model/firm.model';

type EntityResponseType = HttpResponse<IFirm>;
type EntityArrayResponseType = HttpResponse<IFirm[]>;

@Injectable({ providedIn: 'root' })
export class FirmService {
  public resourceUrl = SERVER_API_URL + 'api/firms';

  constructor(protected http: HttpClient) {}

  create(firm: IFirm): Observable<EntityResponseType> {
    return this.http.post<IFirm>(this.resourceUrl, firm, { observe: 'response' });
  }

  update(firm: IFirm): Observable<EntityResponseType> {
    return this.http.put<IFirm>(this.resourceUrl, firm, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IFirm>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IFirm[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
