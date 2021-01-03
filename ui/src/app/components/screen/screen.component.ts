import { Component} from '@angular/core';

import { AppService } from './../../app.service';

export interface PeriodicElement {
  name: string;
  position: number;
  weight: number;
  symbol: string;
}

@Component({
  selector: 'app-screen',
  templateUrl: './screen.component.html',
  styleUrls: ['./screen.component.css']
})
export class ScreenComponent {
  variable1: String;
  variable2: String;
  variable3: String;
  variable4: String;
  variable5: String;
  variable6: String;
  variable7: String;
  date: String;
  variable8: String;

  clubListitle: string[] = ['name', 'location', 'pointCount', 'goalsScored','wins','defeat','matchCount'];
  private clubList: { Points: any; Draw: any; Loss: any; Goals: any; Wins: any; Name: any; Location: any }[];

  constructor(private appService: AppService) {
  }
  // with .responce u get just the responce, without it u get with status
  // so we can take action if it's false
  public getClubPost(): void {
    this.appService.getClub().subscribe((data: any) => {
      this.clubList = data.response.map((club: any) => ({
        Name: club.name,
        Location: club.location,
        Points: club.pointCount,
        Goals: club.goalsScored,
        Wins: club.wins,
        Loss: club.defeat,
        Draw: club.matchCount,
      }))
    });
  }
  public clubDataGoalPost(): void {
    this.appService.clubDataGoal().subscribe((data: any) => {
      this.variable2 = JSON.stringify(data);
    });
  }
  public clubDataWinsPost(): void {
    this.appService.clubDataWins().subscribe((data: any) => {
      this.variable3 = JSON.stringify(data);
    });
  }
  public clubDataResetPost(): void {
    this.appService.clubDataReset().subscribe((data: any) => {
      this.variable4 = JSON.stringify(data);
    });
  }
  public getMatchPost(): void {
    this.appService.getMatch().subscribe((data: any) => {
      this.variable5 = JSON.stringify(data);
    });
  }
  public getMatchDatePost(): void {
    this.appService.getMatchDate().subscribe((data: any) => {
      this.variable6 = JSON.stringify(data);
    });
  }
  public generateMatchPost(): void {
    this.appService.generateMatch().subscribe((data: any) => {
      this.variable7 = JSON.stringify(data);
    });
  }
  public getdatep(){
    return this.date;
  }
  public (): void {
    this.appService.getClub().subscribe((data: any) => {
      this.variable8 = JSON.stringify(data.response);
    });
  }
}
