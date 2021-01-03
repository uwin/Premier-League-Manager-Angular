import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { map } from 'rxjs/operators';
import { Observable,Subject } from 'rxjs/index';


/**
 * Class representing application service.
 *
 * @class AppService.
 */
@Injectable({
  providedIn: "root"
})
export class AppService {
  private serviceUrl =          '/api/summary';
  private dataPostTestUrl =     '/api/postTest';

  private getClubDataUrl =      '/clubData';
  private clubDataGoalUrl =  '/clubDataSortGoal ';
  private clubDataWinsUrl =  '/clubDataSortWins ';
  private clubDataResetUrl = '/clubDataSortReset ';

  private getMatchDataUrl =    '/matchData ';

  private getMatchDateUrl =    '/matchDate';
  private generateMatchUrl =    '/generateMatch ';

  private subject = new Subject<any>()

  sendClickEvent() {
    this.subject.next();
  }
  getClickEvent(): Observable<any>{
    return this.subject.asObservable();
  }

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

  public getMatchDate(date: String){
    console.log(date)
    return this.http.post(this.getMatchDateUrl,{date});
  }
  public generateMatch(){
    return this.http.get(this.generateMatchUrl);
  }






}
