import { Component} from '@angular/core';

import { AppService } from './../../app.service';

// export interface PeriodicElement {
//   name: string;
//   position: number;
//   weight: number;
//   symbol: string;
// }

@Component({
  selector: 'app-screen',
  templateUrl: './screen.component.html',
  styleUrls: ['./screen.component.css']
})
export class ScreenComponent {
  // variable1: String;
  // variable2: String;
  // variable3: String;
  // variable4: String;
  // variable5: String;
  // variable6: String;
  // variable7: String;
  // date: String;
  // variable8: String;
  //
  // clubListitle: string[] = ['name', 'location', 'pointCount', 'goalsScored','wins','defeat','matchCount'];
  // private clubList: { Points: any; Draw: any; Loss: any; Goals: any; Wins: any; Name: any; Location: any }[];
  // // private matchList: { Points: any; Draw: any; Loss: any; Goals: any; Wins: any; Name: any; Location: any }[];
  // matchListitle: string[] = ['date', 'teamAName', 'teamAScore', 'teamBName','teamBScore'];
  // private matchList: { date: any; teamAName: any; teamAScore: any; teamBName: any; teamBScore: any }[];

  constructor(private appService: AppService) {
  }
  // with .responce u get just the responce, without it u get with status
  // so we can take action if it's false
  // public getClubPost(): void {
  //   this.appService.getClub().subscribe((data: any) => {
  //     this.clubList = data.response.map((club: any) => ({
  //       name: club.name,
  //       Location: club.location,
  //       pointCount: club.pointCount,
  //       goalsScored: club.goalsScored,
  //       wins: club.wins,
  //       defeat: club.defeat,
  //       matchCount: club.matchCount,
  //     }))
  //   });
  // }
  // public clubDataGoalPost(): void {
  //   this.appService.clubDataGoal().subscribe((data: any) => {
  //     this.clubList = data.response.map((club: any) => ({
  //       name: club.name,
  //       Location: club.location,
  //       pointCount: club.pointCount,
  //       goalsScored: club.goalsScored,
  //       wins: club.wins,
  //       defeat: club.defeat,
  //       matchCount: club.matchCount,
  //     }))
  //   });
  // }
  // public clubDataWinsPost(): void {
  //   this.appService.clubDataWins().subscribe((data: any) => {
  //     this.clubList = data.response.map((club: any) => ({
  //       name: club.name,
  //       Location: club.location,
  //       pointCount: club.pointCount,
  //       goalsScored: club.goalsScored,
  //       wins: club.wins,
  //       defeat: club.defeat,
  //       matchCount: club.matchCount,
  //     }))
  //   });
  // }
  // public clubDataResetPost(): void {
  //   this.appService.clubDataReset().subscribe((data: any) => {
  //     this.clubList = data.response.map((club: any) => ({
  //       name: club.name,
  //       Location: club.location,
  //       pointCount: club.pointCount,
  //       goalsScored: club.goalsScored,
  //       wins: club.wins,
  //       defeat: club.defeat,
  //       matchCount: club.matchCount,
  //     }))
  //   });
  // }
  // public getMatchPost(): void {
  //   this.appService.getMatch().subscribe((data: any) => {
  //     this.matchList = data.response.map((match: any) => ({
  //       date: match.date,
  //       teamAName: match.teamAName,
  //       teamAScore: match.teamAScore,
  //       teamBName: match.teamBName,
  //       teamBScore: match.teamBScore,
  //     }))
  //   });
  // }
  // public getMatchDatePost(): void {
  //   this.appService.getMatchDate().subscribe((data: any) => {
  //     this.variable6 = JSON.stringify(data);
  //   });
  // }
  // public generateMatchPost(): void {
  //   this.appService.generateMatch().subscribe((data: any) => {
  //     this.matchList = data.response.map((match: any) => ({
  //       date: match.date,
  //       teamAName: match.teamAName,
  //       teamAScore: match.teamAScore,
  //       teamBName: match.teamBName,
  //       teamBScore: match.teamBScore,
  //     }))
  //   });
  // }
  // public getdatep(){
  //   return this.date;
  // }
  // public (): void {
  //   this.appService.getClub().subscribe((data: any) => {
  //     this.variable8 = JSON.stringify(data.response);
  //   });
  // }
}
