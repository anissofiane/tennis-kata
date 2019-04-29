import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

export class PlayerDto {
    constructor(
        public id:string, 
        public name:string,
    ){}
}

@Injectable({
  providedIn: 'root'
})
export class HttpClientService {

  constructor(private httpClient:HttpClient) { }

    getAllPlayers()
  {
    console.log("test call");
    return this.httpClient.get<PlayerDto[]>('http://localhost:8080/getAllPlayers');
  }

}
