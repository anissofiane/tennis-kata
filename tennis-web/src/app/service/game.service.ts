import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { PlayerDto } from './player.service';

export class GameDto {
    constructor(
        public id: string,
        public scores: ScoreGameDto[],
        public scoresMap: Map<string, ScoreGameDto []>,
        public players: PlayerDto [],
        public winner: PlayerDto,
        public gameOrder: string,
    ) { }
}

export class ScoreGameDto {
    constructor(
        public id: string,
        public scoreValue: string,
        public player: PlayerDto
    ) { }
}

@Injectable({
  providedIn: 'root'
})
export class GameService {

  constructor(private httpClient: HttpClient) { }

   getCurrentGame(setTennisId: string) {
      return this.httpClient.get<GameDto>('http://localhost:8080/game/currentGame' + '/' + setTennisId);
  }

  addPoint(gameId: string, playerId: string) {
      return this.httpClient.get<GameDto>('http://localhost:8080/game/addPoint' + '/' + gameId + '/' + playerId);
  }

  createGame(setTennisId: string) {
      return this.httpClient.get<GameDto>('http://localhost:8080/game/createGame' + '/' + setTennisId);
  }
}
