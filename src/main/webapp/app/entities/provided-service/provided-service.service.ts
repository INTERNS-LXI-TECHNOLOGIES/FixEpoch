import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IProvidedService } from 'app/shared/model/provided-service.model';

type EntityResponseType = HttpResponse<IProvidedService>;
type EntityArrayResponseType = HttpResponse<IProvidedService[]>;

@Injectable({ providedIn: 'root' })
export class ProvidedServiceService {
  public resourceUrl = SERVER_API_URL + 'api/provided-services';

  constructor(protected http: HttpClient) {}

  create(providedService: IProvidedService): Observable<EntityResponseType> {
    return this.http.post<IProvidedService>(this.resourceUrl, providedService, { observe: 'response' });
  }

  update(providedService: IProvidedService): Observable<EntityResponseType> {
    return this.http.put<IProvidedService>(this.resourceUrl, providedService, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IProvidedService>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IProvidedService[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
