import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { PlayerDto } from './player.service';



export class SetTennisDto {
    constructor(
        public id: string,
        public players: PlayerDto[],
        public scores: ScoreSetDto[],
        public scoresMap: Map<string, ScoreSetDto []>,
        public setTennisOrder: string,
        public winner: PlayerDto,
    ) { }
}

export class ScoreSetDto {
    constructor(
        public id: string,
        public scoreValue: string,
        public player: PlayerDto
    ) { }
}

@Injectable({
  providedIn: 'root'
})
export class SetService {

  constructor(private httpClient: HttpClient) { }

   createSetTennis() {
      return this.httpClient.get<SetTennisDto>('http://localhost:8080/set/createSetTennis');
  }

  getSetTennis(setTennisId: string) {
      return this.httpClient.get<SetTennisDto>('http://localhost:8080/set/getSetTennis' + '/' + setTennisId);
  }

  updateScore(setTennisId: string) {
      return this.httpClient.get<SetTennisDto>('http://localhost:8080/set/updateScore' + '/' + setTennisId);
  }
}
