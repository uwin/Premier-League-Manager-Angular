import { Component} from '@angular/core';

import { AppService } from './../../app.service';

@Component({
  selector: 'app-screen',
  templateUrl: './screen.component.html',
  styleUrls: ['./screen.component.css']
})
export class ScreenComponent {
  variable: String;

  constructor(private appService: AppService) {
  }
  // with .responce u get just the responce, without it u get with status
  // so we can take action if it's false
  public getClubPost(): void {
    this.appService.getclub().subscribe((data: any) => {
      this.variable = JSON.stringify(data.response);
    });
  }

  public (): void {
    this.appService.getclub().subscribe((data: any) => {
      this.variable = JSON.stringify(data.response);
    });
  }
}
