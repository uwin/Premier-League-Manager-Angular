import { Component, OnInit } from '@angular/core';

import {AppService} from "../../app.service";
import {Subscription} from "rxjs";



@Component({
  selector: 'app-clubscreen',
  templateUrl: './clubscreen.component.html',
  styleUrls: ['./clubscreen.component.css']
})
export class ClubscreenComponent implements OnInit {
  clubListitle: string[] = ['name', 'location', 'pointCount', 'goalsScored','wins','defeat','matchCount'];
  private clubList: { Points: any; Draw: any; Loss: any; Goals: any; Wins: any; Name: any; location: any }[];

  clickEventsubscription:Subscription;







  constructor(private appService: AppService) {
    this.clickEventsubscription=
      this.appService.getClickEvent().subscribe(()=>{
      this.getClubPost();
    })
  }
  count:number=0;
  ngOnInit() {
    this.getClubPost();
  }

  public getClubPost(): void {
    this.appService.getClub().subscribe((data: any) => {
      this.clubList = data.response.map((club: any) => ({
        name: club.name,
        location: club.location,
        pointCount: club.pointCount,
        goalsScored: club.goalsScored,
        wins: club.wins,
        defeat: club.defeat,
        matchCount: club.matchCount,
      }))
    });
  }
  public clubDataGoalPost(): void {
    this.appService.clubDataGoal().subscribe((data: any) => {
      this.clubList = data.response.map((club: any) => ({
        name: club.name,
        location: club.location,
        pointCount: club.pointCount,
        goalsScored: club.goalsScored,
        wins: club.wins,
        defeat: club.defeat,
        matchCount: club.matchCount,
      }))
    });
  }
  public clubDataWinsPost(): void {
    this.appService.clubDataWins().subscribe((data: any) => {
      this.clubList = data.response.map((club: any) => ({
        name: club.name,
        location: club.location,
        pointCount: club.pointCount,
        goalsScored: club.goalsScored,
        wins: club.wins,
        defeat: club.defeat,
        matchCount: club.matchCount,
      }))
    });
  }
  public clubDataResetPost(): void {
    this.appService.clubDataReset().subscribe((data: any) => {
      this.clubList = data.response.map((club: any) => ({
        name: club.name,
        location: club.location,
        pointCount: club.pointCount,
        goalsScored: club.goalsScored,
        wins: club.wins,
        defeat: club.defeat,
        matchCount: club.matchCount,
      }))
    });
  }
}
