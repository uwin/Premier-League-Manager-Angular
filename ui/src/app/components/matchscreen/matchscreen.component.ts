import { Component, OnInit } from '@angular/core';

import {Match} from './Match'

import {AppService} from "../../app.service";

@Component({
  selector: 'app-matchscreen',
  templateUrl: './matchscreen.component.html',
  styleUrls: ['./matchscreen.component.css']
})
export class MatchscreenComponent implements OnInit {
  variable6: Match[];
  variable8: String;
  date: String;
  dateget: String;
  datain: Match;

  matchListitle: string[] = ['date', 'teamAName', 'teamAScore', 'teamBName','teamBScore'];
  private matchList: { date: any; teamAName: any; teamAScore: any; teamBName: any; teamBScore: any }[];


  constructor(private appService: AppService) { }

  ngOnInit() {
    this.getMatchPost();
  }

  public getMatchPost(): void {
    this.appService.getMatch().subscribe((data: any) => {
      this.matchList = data.response.map((match: any) => ({
        date: match.date,
        teamAName: match.teamAName,
        teamAScore: match.teamAScore,
        teamBName: match.teamBName,
        teamBScore: match.teamBScore,
      }))
    });
  }

  public getMatchDatePost(): void {
    this.appService.getMatchDate(this.dateget).subscribe((data: any) => {
      // this.variable6 = (data);
      this.matchList = data.response.map((match: any) => ({
        date: match.date,
        teamAName: match.teamAName,
        teamAScore: match.teamAScore,
        teamBName: match.teamBName,
        teamBScore: match.teamBScore,
      }))
    });
  }

  public generateMatchPost(): void {
    this.appService.generateMatch().subscribe((data: any) => {
      this.datain = (data);
      this.getMatchPost()
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
