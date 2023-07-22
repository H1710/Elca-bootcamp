import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class GroupService {
  private baseURL = 'https://elca-bootcamp-production.up.railway.app/api/group';

  constructor(private httpClient: HttpClient) {}

  getGroupIdList(): Observable<number[]> {
    return this.httpClient.get<number[]>(`${this.baseURL}`);
  }
}
