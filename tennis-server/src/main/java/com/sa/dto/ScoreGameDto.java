package com.sa.dto;

public class ScoreGameDto {

	private Long id;
	private String scoreValue;
	private int scoreOrder;
	private PlayerDto player;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getScoreValue() {
		return scoreValue;
	}
	public void setScoreValue(String scoreValue) {
		this.scoreValue = scoreValue;
	}
	public PlayerDto getPlayer() {
		return player;
	}
	public void setPlayer(PlayerDto player) {
		this.player= player;
	}
	public int getScoreOrder() {
		return scoreOrder;
	}
	public void setScoreOrder(int scoreOrder) {
		this.scoreOrder = scoreOrder;
	}
	
}
