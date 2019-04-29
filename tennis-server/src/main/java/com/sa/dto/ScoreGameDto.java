package com.sa.dto;

public class ScoreGameDto {

	private Long id;
	private int scoreValue;
	private PlayerDto playerDto;
	private GameDto gameDto;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public int getScoreValue() {
		return scoreValue;
	}
	public void setScoreValue(int scoreValue) {
		this.scoreValue = scoreValue;
	}
	public PlayerDto getPlayerDto() {
		return playerDto;
	}
	public void setPlayerDto(PlayerDto playerDto) {
		this.playerDto = playerDto;
	}
	public GameDto getGameDto() {
		return gameDto;
	}
	public void setGameDto(GameDto gameDto) {
		this.gameDto = gameDto;
	}
	
	
	
}
