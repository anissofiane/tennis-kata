package com.sa.test;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.sa.model.Game;
import com.sa.model.Player;
import com.sa.model.SetTennis;
import com.sa.service.GameService;
import com.sa.service.PlayerService;
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
}
