package com.sa.service;

import java.util.Collection;

import com.sa.model.Player;
import com.sa.model.ScoreSet;
import com.sa.model.SetTennis;

public interface ScoreSetService {

	public void addScore(SetTennis setTennis, Player player, int scoreValue); 
	public ScoreSet getLastScorSet(SetTennis setTennis, Player player);
	public Collection<ScoreSet> getScoresSetByPlayer(SetTennis setTennis, Player player);
}
