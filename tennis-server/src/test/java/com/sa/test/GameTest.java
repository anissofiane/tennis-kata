package com.sa.test;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.sa.model.Game;
import com.sa.model.Player;
import com.sa.model.ScoreGame;
import com.sa.model.SetTennis;
import com.sa.service.GameService;
import com.sa.service.PlayerService;
import com.sa.service.ScoreGameService;
import com.sa.service.SetTennisService;


@RunWith(SpringRunner.class)
@SpringBootTest
public class GameTest {
		
	@Autowired
	private PlayerService playerService;
			
	@Autowired
	private GameService gameService;

	@Autowired
	private SetTennisService setTennisService;
	
	@Autowired
	private ScoreGameService scoreGameService;
		
	@Test
	public void testWinGame(){
		
		Player player_a = new Player("Player A");
		Player player_b = new Player("Player B");
		
		playerService.savePlayer(player_a);
		playerService.savePlayer(player_b);
		
		SetTennis setTennis = setTennisService.createSetTennis(Arrays.asList(player_a,player_b));
		
		SetTennis setTennis2 = setTennisService.findSetTennis(setTennis.getId());
		
		Game game = gameService.getCurrentGame(setTennis2.getId());
		
		gameService.addPoint(game, player_b);
		
		gameService.addPoint(game, player_a);
		gameService.addPoint(game, player_a);
		gameService.addPoint(game, player_a);
		gameService.addPoint(game, player_a);
		
				
		Collection<ScoreGame> scorePlayerA = scoreGameService.getScoresGameOfPlayer(game, player_a);
		System.err.println("Player : " + player_a.getName());
		for(ScoreGame score : scorePlayerA){			
			System.err.print(score.getScoreValue() + " ");			
		}
		System.err.println();
		
		
		Collection<ScoreGame> scorePlayerB = scoreGameService.getScoresGameOfPlayer(game, player_b);
		
		System.err.println("Player : " + player_b.getName());
		for(ScoreGame score : scorePlayerB){			
			System.err.print(score.getScoreValue() + " ");			
		}
		
		System.err.println();
		
		System.err.println("Winner is " + game.getWinner().getName());
		
		Assert.assertTrue(player_a.equals(game.getWinner()));
		
	}
}
