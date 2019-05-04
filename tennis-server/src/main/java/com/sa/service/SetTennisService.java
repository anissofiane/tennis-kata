package com.sa.service;

import java.util.Collection;

import com.sa.model.Game;
import com.sa.model.Player;
import com.sa.model.SetTennis;

public interface SetTennisService {

	public SetTennis createSetTennis(Collection<Player> players);
	public Game createGame(Long setTennisId);
	public SetTennis getSetTennisById(Long setTennisId);
	public int getSetOrder(SetTennis setTennis);
}
