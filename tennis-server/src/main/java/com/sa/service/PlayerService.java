package com.sa.service;

import java.util.List;

import com.sa.model.Player;

public interface PlayerService {

	Player getPlayerById(Long id);
	void savePlayer(Player player);
	List<Player> getAllPlayers(); 
	
}
