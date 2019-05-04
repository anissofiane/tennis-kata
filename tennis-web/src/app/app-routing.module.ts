import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { PlayerComponent } from './player/player.component';
import { GameComponent } from './game/game.component';
import { SetComponent } from './set/set.component';

const routes: Routes = [
    { path:'', component: PlayerComponent},
    { path:'', component: GameComponent},
    { path:'', component: SetComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
