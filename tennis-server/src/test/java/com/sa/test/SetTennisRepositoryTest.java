package com.sa.test;

import java.util.List;
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
import com.sa.model.ScoreSet;
import com.sa.model.SetTennis;
import com.sa.repository.GameRepository;
import com.sa.repository.PlayerRepository;
import com.sa.repository.ScoreGameRepository;
import com.sa.repository.ScoreSetRepository;
import com.sa.repository.SetTennisRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SetTennisRepositoryTest {

	@Autowired
	private ScoreSetRepository scoreSetRepository;
	
	@Autowired
	private PlayerRepository playerRepository;
	
	@Autowired
	private ScoreGameRepository scoreGameRepository;
			
	@Autowired
	private GameRepository gameRepository;
		
	@Autowired
	private SetTennisRepository setTennisRepository;
	
	@Test
	public void whenFindingById_then_correct(){
		
		SetTennis setTennis = setTennisRepository.save(new SetTennis());
		
		Player player_a = playerRepository.save(new Player("player_a"));
		setTennis.addPlayer(player_a);
		SetTennis setTennis2 = setTennisRepository.save(setTennis);
		
		Optional<Player> p = playerRepository.findById(player_a.getId());
		Assert.assertTrue(p.isPresent());
		
		ScoreSet score_set_a = scoreSetRepository.save(new ScoreSet(player_a));
		
		setTennis2.addScore(score_set_a);
		
		Optional<ScoreSet> score_set_a_option = scoreSetRepository.findById(score_set_a.getId());
		Assert.assertTrue(score_set_a_option.isPresent());
		
		ScoreGame score_game_a = scoreGameRepository.save(new ScoreGame(player_a));
		
		Optional<ScoreGame> score_game_a_option = scoreGameRepository.findById(score_game_a.getId());
		Assert.assertTrue(score_game_a_option.isPresent());
		
		Game game_a = new Game();
		
		game_a.addScore(score_game_a);
		
		Game game = gameRepository.save(game_a);
		
		setTennis2.addGame(game);
		
		Optional<Game> g = gameRepository.findById(game.getId());
		Assert.assertTrue(g.isPresent());	
		
				
		Optional<SetTennis> set_tennis_option = setTennisRepository.findById(setTennis2.getId());
		Assert.assertTrue(set_tennis_option.isPresent());
		
		
	}
}
