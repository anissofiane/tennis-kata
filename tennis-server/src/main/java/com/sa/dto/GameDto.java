package com.sa.dto;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public class GameDto {

	private Long id;
	private Collection<ScoreGameDto> scores;	
	private PlayerDto winner;
	private Map<Long, Collection<ScoreGameDto>> scoresMap;
	private Collection<PlayerDto> players;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Collection<ScoreGameDto> getScores() {
		return scores;
	}
	public void setScores(Collection<ScoreGameDto> scores) {
		this.scores = scores;
	}
	
	public PlayerDto getWinner() {
		return winner;
	}
	public void setWinner(PlayerDto winner) {
		this.winner = winner;
	}
	public Map<Long, Collection<ScoreGameDto>> getScoresMap() {
		return scoresMap;
	}
	public void setScoresMap(Map<Long,Collection<ScoreGameDto>> scoresMap) {
		this.scoresMap = scoresMap;
	}
	public Collection<PlayerDto> getPlayers() {
		return players;
	}
	public void setPlayers(Collection<PlayerDto> players) {
		this.players = players;
	}
	
	
}
