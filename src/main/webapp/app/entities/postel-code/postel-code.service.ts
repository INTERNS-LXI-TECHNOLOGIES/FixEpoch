import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IPostelCode } from 'app/shared/model/postel-code.model';

type EntityResponseType = HttpResponse<IPostelCode>;
type EntityArrayResponseType = HttpResponse<IPostelCode[]>;

@Injectable({ providedIn: 'root' })
export class PostelCodeService {
  public resourceUrl = SERVER_API_URL + 'api/postel-codes';

  constructor(protected http: HttpClient) {}

  create(postelCode: IPostelCode): Observable<EntityResponseType> {
    return this.http.post<IPostelCode>(this.resourceUrl, postelCode, { observe: 'response' });
  }

  update(postelCode: IPostelCode): Observable<EntityResponseType> {
    return this.http.put<IPostelCode>(this.resourceUrl, postelCode, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IPostelCode>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IPostelCode[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
