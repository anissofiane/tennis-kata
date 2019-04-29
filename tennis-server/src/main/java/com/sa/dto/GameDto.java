package com.sa.dto;

import java.util.Collection;

public class GameDto {

	private Long id;
	private Collection<ScoreGameDto> scoresDto;
	private SetTennisDto setTennisDto;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Collection<ScoreGameDto> getScoresDto() {
		return scoresDto;
	}
	public void setScoresDto(Collection<ScoreGameDto> scoresDto) {
		this.scoresDto = scoresDto;
	}
	public SetTennisDto getSetTennisDto() {
		return setTennisDto;
	}
	public void setSetTennisDto(SetTennisDto setTennisDto) {
		this.setTennisDto = setTennisDto;
	}
}
