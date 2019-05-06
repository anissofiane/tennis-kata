package com.sa.service;

import com.sa.model.Game;
import com.sa.model.Player;
import com.sa.model.SetTennis;

public interface GameService {

	public Game createGame(SetTennis setTennis);
	public Game saveGame(Game game);
	public void addPointGame(Game game, Player player);
	public Game addPointGame(Long gameId, Long playerId);
	public Game getGameById(Long gameId);
	public Game getCurrentGame(Long setTennisId);
	public Game getLastGame(Long setTennisId);
	public int getGameOrder(Game game);
}
