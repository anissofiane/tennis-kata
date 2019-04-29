package com.sa.service;

import java.util.List;

import com.sa.dto.PlayerDto;
import com.sa.model.Player;

public interface PlayerService {

	Player getPlayerById(Long id);
	void savePlayer(Player playerDto);
	List<Player> getAllPlayers(); 
	
}
