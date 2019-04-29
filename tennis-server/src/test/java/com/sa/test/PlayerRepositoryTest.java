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
		
		Player p = playerRepository.save(new Player("player_b"));
		Optional<Player> p1 = playerRepository.findById(p.getId());
		Assert.assertTrue(p1.isPresent());
		
	}
}
