import { Component, OnInit } from '@angular/core';
import { SetService, SetTennisDto} from '../service/set.service';
import { GameComponent } from '../game/game.component';

@Component({
  selector: 'app-set',
  templateUrl: './set.component.html',
  styleUrls: ['./set.component.less']
})
export class SetComponent implements  OnInit {

   currentSet: SetTennisDto ;

   constructor(private setService: SetService) { }

   createSetTennis(): void {
    this.setService.createSetTennis()
      .subscribe( response => { this.currentSet = response; } );
  }

  loadSetTennis(): void {
    this.setService.getSetTennis(this.currentSet.id)
      .subscribe( response => { this.currentSet = response; } );
  }

  ngOnInit() {
       this.createSetTennis();
  }

  onWin(win: boolean) {
        if(win) {
            this.loadSetTennis();
        }
  }
}
