package com.sa.dto;

import java.util.Collection;

public class SetTennisDto {

	private Long id;
	
	private Collection<PlayerDto> players;
	
	private Collection<GameDto> games;

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
}
