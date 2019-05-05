import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { GameService, GameDto, ScoreGameDto } from '../service/game.service';

@Component({
  selector: 'app-game',
  templateUrl: './game.component.html',
  styleUrls: ['./game.component.less']
})
export class GameComponent implements OnInit {

  private _setTennisId: string;

  private _setWon: boolean;

  currentGame: GameDto;

   @Output() win = new EventEmitter<boolean>();

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
      .subscribe( response => { this.currentGame = response; if(this.currentGame.winner) this.win.emit(true) ; else this.win.emit(false) ;} );
  }

  createGame(): void {
    this.gameService.createGame(this.setTennisId)
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

  get setWon(): boolean {
      return this._setWon;
  }

  @Input()
  set setWon(setWon: boolean) {
    this._setWon = setWon;
  }
}
