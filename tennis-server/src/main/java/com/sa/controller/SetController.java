package com.sa.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sa.dto.SetTennisDto;
import com.sa.model.Player;
import com.sa.model.SetTennis;
import com.sa.service.PlayerService;
import com.sa.service.SetTennisService;
import com.sa.util.Constants;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class SetController {

	@Autowired
	private SetTennisService setTennisService;
	
	@Autowired
	private PlayerService playerService;
	
	private ModelMapper modelMapper = new ModelMapper();
	
	@RequestMapping(value = Constants.CREATE_SET, method = RequestMethod.GET)
	public SetTennisDto createSetTennis(){
		List<Player> players =  playerService.getAllPlayers();
		return convertToDto(setTennisService.createSetTennis(players));
	}
	
	private SetTennisDto convertToDto(SetTennis setTennis) {
		SetTennisDto setTennisDto = modelMapper.map(setTennis, SetTennisDto.class);	    
		setTennisDto.setSetTennisOrder(setTennisService.getSetOrder(setTennis));
	    return setTennisDto;
	}
}
