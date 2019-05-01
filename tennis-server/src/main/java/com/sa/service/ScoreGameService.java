package com.sa.service;

import com.sa.model.Game;
import com.sa.model.Player;

public interface ScoreGameService {

	public void addScore(Game game, Player player, int scoreValue);
}
