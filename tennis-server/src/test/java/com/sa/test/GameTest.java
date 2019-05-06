package com.sa.test;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
		
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
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
		
		SetTennis setTennis2 = setTennisService.getSetTennisById(setTennis.getId());
		
		Game game = gameService.getCurrentGame(setTennis2.getId());
		game.setSetTennis(setTennis);
		gameService.addPointGame(game, player_b);
		
		gameService.addPointGame(game, player_a);
		gameService.addPointGame(game, player_a);
		gameService.addPointGame(game, player_a);
		gameService.addPointGame(game, player_a);
		
				
		Collection<ScoreGame> scorePlayerA = scoreGameService.getScoresGameByPlayer(game, player_a);
		logger.info("Player : " + player_a.getName());
		for(ScoreGame score : scorePlayerA){			
			logger.info(score.getScoreValue() + " ");			
		}
						
		Collection<ScoreGame> scorePlayerB = scoreGameService.getScoresGameByPlayer(game, player_b);
		
		logger.info("Player : " + player_b.getName());
		for(ScoreGame score : scorePlayerB){			
			logger.info(score.getScoreValue() + " ");			
		}
					
		Assert.assertTrue(player_a.equals(game.getWinner()));
		
		logger.info("Winner is " + game.getWinner().getName());
	}
	
	@Test
	public void testWinGame_deuce(){
		
		Player player_a = new Player("Player A");
		Player player_b = new Player("Player B");
		
		playerService.savePlayer(player_a);
		playerService.savePlayer(player_b);
		
		SetTennis setTennis = setTennisService.createSetTennis(Arrays.asList(player_a,player_b));
		
		SetTennis setTennis2 = setTennisService.getSetTennisById(setTennis.getId());
		
		Game game = gameService.getCurrentGame(setTennis2.getId());
		game.setSetTennis(setTennis);
		
		gameService.addPointGame(game, player_b);
		
		gameService.addPointGame(game, player_a);
		gameService.addPointGame(game, player_a);
		gameService.addPointGame(game, player_a);
		
		gameService.addPointGame(game, player_b);
		gameService.addPointGame(game, player_b);
		gameService.addPointGame(game, player_b);
		
		gameService.addPointGame(game, player_a);
		
		gameService.addPointGame(game, player_a);
		
		gameService.addPointGame(game, player_a);
				
		Collection<ScoreGame> scorePlayerA = scoreGameService.getScoresGameByPlayer(game, player_a);
		logger.info("Player : " + player_a.getName());
		for(ScoreGame score : scorePlayerA){			
			logger.info(score.getScoreValue() + " ");			
		}
				
		Collection<ScoreGame> scorePlayerB = scoreGameService.getScoresGameByPlayer(game, player_b);
		
		logger.info("Player : " + player_b.getName());
		for(ScoreGame score : scorePlayerB){			
			logger.info(score.getScoreValue() + " ");			
		}
						
		Assert.assertTrue(player_a.equals(game.getWinner()));
		
		logger.info("Winner is " + game.getWinner().getName());
		
	}
	
	@Test
	public void testLastGame(){
		
		Player player_a = new Player("Player A");
		Player player_b = new Player("Player B");
		
		playerService.savePlayer(player_a);
		playerService.savePlayer(player_b);
		
		SetTennis setTennis = setTennisService.createSetTennis(Arrays.asList(player_a,player_b));
		
		SetTennis setTennis2 = setTennisService.getSetTennisById(setTennis.getId());
		
		Game game = gameService.getCurrentGame(setTennis2.getId());
		game.setSetTennis(setTennis);
		
		Game g = setTennisService.createGame(setTennis.getId());
		
		Game lastGame = gameService.getLastGame(setTennis.getId());
		
		Assert.assertTrue(g.equals(lastGame));
		
	}
	
}
