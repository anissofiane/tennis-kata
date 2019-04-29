package com.sa.test;

import java.util.Arrays;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.sa.model.Game;
import com.sa.model.Player;
import com.sa.model.ScoreGame;
import com.sa.repository.GameRepository;
import com.sa.repository.PlayerRepository;
import com.sa.repository.ScoreGameRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GameRepositoryTest {

	@Autowired
	private ScoreGameRepository scoreGameRepository;
	
	@Autowired
	private PlayerRepository playerRepository;
	
	@Autowired
	private GameRepository gameRepository;
	
	@Test
	public void whenFindingById_then_correct(){
		
		Player player_a = playerRepository.save(new Player("player_a"));
						
		Optional<Player> p = playerRepository.findById(player_a.getId());
		Assert.assertTrue(p.isPresent());
		
		ScoreGame score_a = scoreGameRepository.save(new ScoreGame(p.get()));
		
		Optional<ScoreGame> s = scoreGameRepository.findById(score_a.getId());
		Assert.assertTrue(s.isPresent());
		
		Game game_a = new Game();
		
		game_a.addScore(score_a);
		
		Game game = gameRepository.save(game_a);

		Optional<Game> g = gameRepository.findById(game.getId());
		Assert.assertTrue(g.isPresent());
	}
}
