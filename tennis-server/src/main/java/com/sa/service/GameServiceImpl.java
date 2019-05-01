package com.sa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sa.model.Game;
import com.sa.model.Player;

@Service
public class GameServiceImpl implements GameService {

	@Autowired
	private ScoreGameService scoreGameService;
	
	@Override
	public void addPoint(Game game, Player player) {
		
	}

}
