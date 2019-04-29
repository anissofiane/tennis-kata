package com.sa.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sa.model.Player;
import com.sa.repository.PlayerRepository;

@Service
public class PlayerServiceImpl implements PlayerService {

	@Autowired
	private PlayerRepository playerRepository;
	
	@Override
	public Player getPlayerById(Long id) {
		Optional<Player> player = playerRepository.findById(id);
		return player.get();
	}

	@Override
	public void savePlayer(Player player) {
		playerRepository.save(player);
	}

	@Override
	public List<Player> getAllPlayers() {		
		return playerRepository.findAll();
	}

}
