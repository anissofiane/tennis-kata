package com.sa.dto;

import java.util.Collection;
import java.util.Map;

public class SetTennisDto {

	private Long id;
	
	private Collection<PlayerDto> players;	
	private Collection<GameDto> games;	
	private Collection<ScoreSetDto> scores;	
	private PlayerDto winner;
	private Map<Long, Collection<ScoreSetDto>> scoresMap;
	private int setTennisOrder;
	
	public Collection<ScoreSetDto> getScores() {
		return scores;
	}

	public void setScores(Collection<ScoreSetDto> scores) {
		this.scores = scores;
	}

	public PlayerDto getWinner() {
		return winner;
	}

	public void setWinner(PlayerDto winner) {
		this.winner = winner;
	}

	public Map<Long, Collection<ScoreSetDto>> getScoresMap() {
		return scoresMap;
	}

	public void setScoresMap(Map<Long, Collection<ScoreSetDto>> scoresMap) {
		this.scoresMap = scoresMap;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Collection<PlayerDto> getPlayers() {
		return players;
	}

	public void setPlayers(Collection<PlayerDto> players) {
		this.players = players;
	}

	public Collection<GameDto> getGames() {
		return games;
	}

	public void setGames(Collection<GameDto> games) {
		this.games = games;
	}

	public int getSetTennisOrder() {
		return setTennisOrder;
	}

	public void setSetTennisOrder(int setTennisOrder) {
		this.setTennisOrder = setTennisOrder;
	}
}
