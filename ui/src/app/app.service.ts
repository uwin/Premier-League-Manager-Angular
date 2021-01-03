import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { map } from 'rxjs/operators';
import { Observable } from 'rxjs/index';

import {ScreenComponent} from './components/screen/screen.component'
/**
 * Class representing application service.
 *
 * @class AppService.
 */
@Injectable()
export class AppService {
  private serviceUrl =          '/api/summary';
  private dataPostTestUrl =     '/api/postTest';

  private getClubDataUrl =      '/clubData';
  private clubDataGoalUrl =  '/clubDataSortGoal ';
  private clubDataWinsUrl =  '/clubDataSortWins ';
  private clubDataResetUrl = '/clubDataSortReset ';

  private getMatchDataUrl =    '/matchData ';
  private getMatchDateUrl =    '/matchData/: ';
  private generateMatchUrl =    '/generateMatch ';

  constructor(private http: HttpClient) {
  }

  /**
   * Makes a http get request to retrieve the welcome message from the backend service.
   */
  public getWelcomeMessage() {
    return this.http.get(this.serviceUrl).pipe(
      map(response => response)
    );
  }

  /**
   * Makes a http post request to send some data to backend & get response.
   */
  public sendData(): Observable<any> {
    return this.http.post(this.dataPostTestUrl, {});
  }

  public getClub(){
    return this.http.get(this.getClubDataUrl);
  }
  public clubDataGoal(){
    return this.http.get(this.clubDataGoalUrl);
  }
  public clubDataWins(){
    return this.http.get(this.clubDataWinsUrl);
  }
  public clubDataReset(){
    return this.http.get(this.clubDataResetUrl);
  }

  public getMatch(){
    return this.http.get(this.getMatchDataUrl);
  }
  private screenComponent: ScreenComponent
  public getMatchDate(){
    return this.http.get(this.getMatchDateUrl+this.screenComponent.getdatep());
  }
  public generateMatch(){
    return this.http.get(this.generateMatchUrl);
  }






}
