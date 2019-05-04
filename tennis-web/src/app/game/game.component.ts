import { Component, OnInit, Input } from '@angular/core';
import { GameService, GameDto, ScoreGameDto } from '../service/game.service';

@Component({
  selector: 'app-game',
  templateUrl: './game.component.html',
  styleUrls: ['./game.component.less']
})
export class GameComponent implements OnInit {

  private _setTennisId: string;

  currentGame: GameDto;

  constructor(private gameService: GameService) { }

  ngOnInit() {
        this.getCurrentGame(this.setTennisId);
  }

  getCurrentGame(id: string): void {
    this.gameService.getCurrentGame(id)
      .subscribe( response => { this.currentGame = response; } );
  }

  addPoint(playerId: string): void {
    this.gameService.addPoint(this.currentGame.id, playerId)
      .subscribe( response => { this.currentGame = response; } );
  }

  get setTennisId(): string {
      return this._setTennisId;
  }

  @Input()
  set setTennisId(setTennisId: string) {
        this._setTennisId = setTennisId;
        this.getCurrentGame(this.setTennisId);
  }

}
