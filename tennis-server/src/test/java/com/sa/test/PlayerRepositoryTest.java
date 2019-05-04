package com.sa.test;



import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.sa.model.Player;
import com.sa.repository.PlayerRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PlayerRepositoryTest {

	@Autowired
	private PlayerRepository playerRepository;
	
	@Test
	public void whenFindingById_then_correct(){		
		
		Player p1 = playerRepository.save(new Player("Player A"));
		Optional<Player> option_p1 = playerRepository.findById(p1.getId());
		Assert.assertTrue(option_p1.isPresent());
		
		Player p2 = playerRepository.save(new Player("Player B"));
		Optional<Player> option_p2 = playerRepository.findById(p2.getId());
		Assert.assertTrue(option_p2.isPresent());
	}
}
