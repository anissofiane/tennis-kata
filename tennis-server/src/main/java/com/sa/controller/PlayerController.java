package com.sa.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sa.dto.PlayerDto;
import com.sa.model.Player;
import com.sa.service.PlayerService;
import com.sa.util.Constants;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class PlayerController {

	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private PlayerService playerService;
	
    private ModelMapper modelMapper = new ModelMapper();
	
	@RequestMapping(Constants.GET_PLAYER_BY_ID)
	public PlayerDto getPlayerById(@PathVariable Long id) {
		logger.info("get player [id = " + id + "]");
		return convertToDto(playerService.getPlayerById(id));
	}

	@RequestMapping(value = Constants.GET_ALL_PLAYERS, method = RequestMethod.GET, produces = "application/json")
	public List<PlayerDto> getAllPlayers() {
		logger.info("get all players");
		List<Player> players = playerService.getAllPlayers();
		return players.stream().map(player -> convertToDto(player)).collect(Collectors.toList());
	}

	@RequestMapping(value = Constants.SAVE_PLAYER, method = RequestMethod.POST)
	public void saveUser(@RequestBody PlayerDto playerDto) {
		logger.info("save player");
		playerService.savePlayer(convertToEntity(playerDto));
	}

	private PlayerDto convertToDto(Player player) {
		PlayerDto playerDto = modelMapper.map(player, PlayerDto.class);	    
	    return playerDto;
	}
	
	private Player convertToEntity(PlayerDto playerDto){
		Player player = modelMapper.map(playerDto, Player.class);
		if(playerDto.getId() != null){
			Player oldPlayer = playerService.getPlayerById(playerDto.getId());
			player.setId(oldPlayer.getId());			
		}
		return player;
	}
	
}
