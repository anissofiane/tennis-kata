package com.sa.controller;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sa.dto.ScoreGameDto;
import com.sa.dto.ScoreSetDto;
import com.sa.dto.SetTennisDto;
import com.sa.model.Player;
import com.sa.model.ScoreGame;
import com.sa.model.ScoreSet;
import com.sa.model.SetTennis;
import com.sa.service.PlayerService;
import com.sa.service.ScoreSetService;
import com.sa.service.SetTennisService;
import com.sa.util.Constants;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class SetController {

	@Autowired
	private SetTennisService setTennisService;
	
	@Autowired
	private PlayerService playerService;
	
	@Autowired
	private ScoreSetService scoreSetService;
	
	private ModelMapper modelMapper = new ModelMapper();
	
	@RequestMapping(value = Constants.CREATE_SET, method = RequestMethod.GET)
	public SetTennisDto createSetTennis(){
		List<Player> players =  playerService.getAllPlayers();
		return convertToDto(setTennisService.createSetTennis(players));
	}
	
	@RequestMapping(value = Constants.GET_SET, method = RequestMethod.GET)
	public SetTennisDto getSetTennis(@PathVariable("setTennisId") Long setTennisId){
		SetTennis setTennis = setTennisService.getSetTennisById(setTennisId);		
		return convertToDto(setTennis);
	}
	
	@RequestMapping(value = Constants.UPDATE_SCORE_SET, method = RequestMethod.GET)
	public SetTennisDto updateScoreSet(@PathVariable("setTennisId") Long setTennisId){
		SetTennis setTennis = setTennisService.getSetTennisById(setTennisId);
		setTennisService.updateScore(setTennis);
		return convertToDto(setTennis);
	}
	
	private SetTennisDto convertToDto(SetTennis setTennis) {
		SetTennisDto setTennisDto = modelMapper.map(setTennis, SetTennisDto.class);	    
		HashMap<Long, Collection<ScoreSetDto>> map = new HashMap<Long, Collection<ScoreSetDto>>();
		for(Player player : setTennis.getPlayers() ){			
			Collection<ScoreSet> scores = scoreSetService.getScoresSetByPlayer(setTennis, player);
			Collection<ScoreSetDto> list = scores.stream().map(scoreSet -> modelMapper.map(scoreSet, ScoreSetDto.class)).collect(Collectors.toList());
			map.put(player.getId(), list);
		}
		setTennisDto.setScoresMap(map);
		setTennisDto.setSetTennisOrder(setTennisService.getSetOrder(setTennis));
	    return setTennisDto;
	}
}
