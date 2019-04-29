package com.sa.test;

import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.sa.model.Player;
import com.sa.model.ScoreGame;
import com.sa.repository.PlayerRepository;
import com.sa.repository.ScoreGameRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ScoreGameRepositoryTest {

	@Autowired
	private ScoreGameRepository scoreGameRepository;
	
	@Autowired
	private PlayerRepository playerRepository;
	
		
	@Test
	public void whenFindingById_then_correct(){
		
		Player player_a = playerRepository.save(new Player("player_a"));
						
		Optional<Player> p = playerRepository.findById(player_a.getId());
		Assert.assertTrue(p.isPresent());
		
		ScoreGame score_a = scoreGameRepository.save(new ScoreGame(player_a));
		
		Optional<ScoreGame> s = scoreGameRepository.findById(score_a.getId());
		Assert.assertTrue(s.isPresent());
		
				
		
	}
}
