package com.sa.service;

import java.util.Collection;

import com.sa.model.Game;
import com.sa.model.Player;

public interface GameService {

	public Game createGame(Collection<Player> players);
	public Game saveGame(Game game);
	public void addPoint(Game game, Player player);
	public Game addPoint(Long gameId, Long playerId);
	public Game getGameById(Long gameId);
	public Game getCurrentGame(Long setId);
	public int getGameOrder(Game game);
}
