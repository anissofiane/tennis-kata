import { Component, OnInit } from '@angular/core';
import { PlayerService, PlayerDto } from '../service/player.service';

@Component({
  selector: 'app-player',
  templateUrl: './player.component.html',
  styleUrls: ['./player.component.less']
})
export class PlayerComponent implements OnInit {

  players: PlayerDto[];

  constructor( private playerService: PlayerService) { }

  ngOnInit() {
    this.playerService.getAllPlayers().subscribe(
     response => this.handleSuccessfulResponse(response),
    );
  }

handleSuccessfulResponse(response) {
    this.players= response;
 }

}
