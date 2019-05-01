package com.sa.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.sa.model.Game;
import com.sa.model.Player;
import com.sa.service.GameService;
import com.sa.service.PlayerService;
import org.junit.Assert;


@RunWith(SpringRunner.class)
@SpringBootTest
public class GameTest {
		
	@Autowired
	private PlayerService playerService;
			
	@Autowired
	private GameService gameService;
		
	@Test
	public void testWinGame(){
		Player player_a = new Player("Player A");
		Player player_b = new Player("Player B");
		
		playerService.savePlayer(player_a);
		playerService.savePlayer(player_b);
		
		Game game = new Game();
		
		gameService.addPoint(game, player_a);
		gameService.addPoint(game, player_a);
		gameService.addPoint(game, player_a);
		gameService.addPoint(game, player_a);
		
		gameService.addPoint(game, player_b);
		
		Assert.assertTrue(player_a.equals(game.getWinner()));
		
	}
}
