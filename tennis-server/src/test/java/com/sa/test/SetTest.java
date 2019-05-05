package com.sa.test;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.sa.model.Game;
import com.sa.model.Player;
import com.sa.model.ScoreGame;
import com.sa.model.ScoreSet;
import com.sa.model.SetTennis;
import com.sa.service.GameService;
import com.sa.service.PlayerService;
import com.sa.service.ScoreSetService;
import com.sa.service.SetTennisService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SetTest {

	@Autowired
	private SetTennisService setTennisService;
	
	@Autowired
	private PlayerService playerService; 
	
	@Autowired
	private GameService gameService;
	
	@Autowired
	private ScoreSetService scoreSetService;
	
	@Test
	public void getCurrentGame(){
		
		Player player_a = new Player("Player A");
		Player player_b = new Player("Player B");
		
		playerService.savePlayer(player_a);
		playerService.savePlayer(player_b);
						
		SetTennis setTennis = setTennisService.createSetTennis(Arrays.asList(player_a,player_b));
		
		Game currentGame = gameService.getCurrentGame(setTennis.getId());
		
		Assert.assertTrue(currentGame != null);
	}
	
	@Test
	public void win_set(){
		
		Player player_a = new Player("Player A");
		Player player_b = new Player("Player B");
		
		playerService.savePlayer(player_a);
		playerService.savePlayer(player_b);
						
		SetTennis setTennis = setTennisService.createSetTennis(Arrays.asList(player_a,player_b));
		
		setTennisService.addPointSet(setTennis, player_a);
		setTennisService.addPointSet(setTennis, player_a);
		setTennisService.addPointSet(setTennis, player_a);
		setTennisService.addPointSet(setTennis, player_a);
		setTennisService.addPointSet(setTennis, player_a);
		setTennisService.addPointSet(setTennis, player_a);
		
		
		Collection<ScoreSet> scorePlayerA = scoreSetService.getScoresSetByPlayer(setTennis, player_a);
		System.err.println("Player : " + player_a.getName());
		for(ScoreSet score : scorePlayerA){			
			System.err.print(score.getScoreValue() + " ");			
		}
		System.err.println();
		
		
		Collection<ScoreSet> scorePlayerB = scoreSetService.getScoresSetByPlayer(setTennis, player_b);
		System.err.println("Player : " + player_b.getName());
		for(ScoreSet score : scorePlayerB){			
			System.err.print(score.getScoreValue() + " ");			
		}
		System.err.println();
		
		System.err.println();
					
		Assert.assertTrue(player_a.equals(setTennis.getWinner()));
		
		System.err.println("Winner is " + setTennis.getWinner().getName());
	}
	
	@Test
	public void win_set_2(){
		
		Player player_a = new Player("Player A");
		Player player_b = new Player("Player B");
		
		playerService.savePlayer(player_a);
		playerService.savePlayer(player_b);
						
		SetTennis setTennis = setTennisService.createSetTennis(Arrays.asList(player_a,player_b));
		
		setTennisService.addPointSet(setTennis, player_a);
		setTennisService.addPointSet(setTennis, player_a);
		setTennisService.addPointSet(setTennis, player_a);
		setTennisService.addPointSet(setTennis, player_a);
		setTennisService.addPointSet(setTennis, player_a);
		
		setTennisService.addPointSet(setTennis, player_b);
		setTennisService.addPointSet(setTennis, player_b);
		setTennisService.addPointSet(setTennis, player_b);
		setTennisService.addPointSet(setTennis, player_b);
		setTennisService.addPointSet(setTennis, player_b);
		
		setTennisService.addPointSet(setTennis, player_a);
		
		setTennisService.addPointSet(setTennis, player_a);
		
		
		Collection<ScoreSet> scorePlayerA = scoreSetService.getScoresSetByPlayer(setTennis, player_a);
		System.err.println("Player : " + player_a.getName());
		for(ScoreSet score : scorePlayerA){			
			System.err.print(score.getScoreValue() + " ");			
		}
		System.err.println();
		
		
		Collection<ScoreSet> scorePlayerB = scoreSetService.getScoresSetByPlayer(setTennis, player_b);
		System.err.println("Player : " + player_b.getName());
		for(ScoreSet score : scorePlayerB){			
			System.err.print(score.getScoreValue() + " ");			
		}
		System.err.println();
		
		System.err.println();
					
		Assert.assertTrue(player_a.equals(setTennis.getWinner()));
		
		System.err.println("Winner is " + setTennis.getWinner().getName());
	}
	
	@Test
	public void win_set_tie_break(){
		
		Player player_a = new Player("Player A");
		Player player_b = new Player("Player B");
		
		playerService.savePlayer(player_a);
		playerService.savePlayer(player_b);
						
		SetTennis setTennis = setTennisService.createSetTennis(Arrays.asList(player_a,player_b));
		
		setTennisService.addPointSet(setTennis, player_a);
		setTennisService.addPointSet(setTennis, player_a);
		setTennisService.addPointSet(setTennis, player_a);
		setTennisService.addPointSet(setTennis, player_a);
		setTennisService.addPointSet(setTennis, player_a);
		
		setTennisService.addPointSet(setTennis, player_b);
		setTennisService.addPointSet(setTennis, player_b);
		setTennisService.addPointSet(setTennis, player_b);
		setTennisService.addPointSet(setTennis, player_b);
		setTennisService.addPointSet(setTennis, player_b);
		
		setTennisService.addPointSet(setTennis, player_a);
		//tie_break
		setTennisService.addPointSet(setTennis, player_b);
		
		setTennisService.addPointSet(setTennis, player_b);
		
		setTennisService.addPointSet(setTennis, player_a);
		
		setTennisService.addPointSet(setTennis, player_a);
		
		setTennisService.addPointSet(setTennis, player_a);
		
		
		Collection<ScoreSet> scorePlayerA = scoreSetService.getScoresSetByPlayer(setTennis, player_a);
		System.err.println("Player : " + player_a.getName());
		for(ScoreSet score : scorePlayerA){			
			System.err.print(score.getScoreValue() + " ");			
		}
		System.err.println();
		
		
		Collection<ScoreSet> scorePlayerB = scoreSetService.getScoresSetByPlayer(setTennis, player_b);
		System.err.println("Player : " + player_b.getName());
		for(ScoreSet score : scorePlayerB){			
			System.err.print(score.getScoreValue() + " ");			
		}
		System.err.println();
		
		System.err.println();
					
		Assert.assertTrue(player_a.equals(setTennis.getWinner()));
		
		System.err.println("Winner is " + setTennis.getWinner().getName());
	}
}
