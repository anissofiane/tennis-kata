import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { PlayerDto } from './player.service';



export class SetTennisDto {
    constructor(
        public id: string,
        public players: PlayerDto[],
        public setTennisOrder: string,
    ) { }
}

@Injectable({
  providedIn: 'root'
})
export class SetService {

  constructor(private httpClient: HttpClient) { }

   createSetTennis() {
      return this.httpClient.get<SetTennisDto>('http://localhost:8080/createSetTennis');
  }

}
