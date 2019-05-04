package com.sa.service;

import java.util.Collection;

import com.sa.model.Game;
import com.sa.model.Player;
import com.sa.model.ScoreGame;

public interface ScoreGameService {

	public void addScore(Game game, Player player, String scoreValue);
	public ScoreGame getLastScoreGame(Game game, Player player);
	public Collection<ScoreGame> getScoresGameOfPlayer(Game game, Player player);
}
