import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

export class PlayerDto {
    constructor(
        public id: string,
        public name: string,
    ) {}
}



@Injectable({
  providedIn: 'root'
})
export class PlayerService {

  constructor(private httpClient: HttpClient) { }

    getAllPlayers() {
    return this.httpClient.get<PlayerDto[]>('http://localhost:8080/player/getAllPlayers');
  }

}
