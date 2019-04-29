package com.sa.test;

import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.sa.model.Player;
import com.sa.model.ScoreSet;
import com.sa.repository.PlayerRepository;
import com.sa.repository.ScoreSetRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ScoreSetRepositoryTest {

	@Autowired
	private ScoreSetRepository scoreSetRepository;
	
	@Autowired
	private PlayerRepository playerRepository;
	
		
	@Test
	public void whenFindingById_then_correct(){
		
		Player player_a = playerRepository.save(new Player("player_a"));
						
		Optional<Player> p = playerRepository.findById(player_a.getId());
		Assert.assertTrue(p.isPresent());
		
		ScoreSet score_a = scoreSetRepository.save(new ScoreSet(player_a));
		
		Optional<ScoreSet> s = scoreSetRepository.findById(score_a.getId());
		Assert.assertTrue(s.isPresent());
		
				
		
	}
}
