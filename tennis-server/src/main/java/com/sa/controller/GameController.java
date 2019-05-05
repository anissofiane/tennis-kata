package com.sa.controller;

import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sa.dto.GameDto;
import com.sa.dto.PlayerDto;
import com.sa.dto.ScoreGameDto;
import com.sa.model.Game;
import com.sa.model.Player;
import com.sa.model.ScoreGame;
import com.sa.service.GameService;
import com.sa.service.ScoreGameService;
import com.sa.service.SetTennisService;
import com.sa.util.Constants;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class GameController {

	
	@Autowired
	private GameService gameService;
	
	@Autowired
	private SetTennisService setTennisService;
	
	@Autowired
	private ScoreGameService scoreGameService;
	
	private ModelMapper modelMapper = new ModelMapper();
	
	
	@RequestMapping(value = Constants.CURRENT_GAME, method = RequestMethod.GET)
	public GameDto getCurrentGame(@PathVariable("setTennisId") Long setTennisId){		
		return convertToDto(gameService.getCurrentGame(setTennisId));
	}
	
	@RequestMapping(value = Constants.ADD_POINT, method = RequestMethod.GET)
	public GameDto addPoint(@PathVariable("gameId") Long gameId, @PathVariable("playerId") Long playerId){
		Game game = gameService.addPointGame(gameId, playerId);
		return convertToDto(game);		
	}
	
	@RequestMapping(value = Constants.CREATE_GAME, method = RequestMethod.GET)
	public GameDto createGame(@PathVariable("setTennisId") Long setTennisId){		
		Game game = setTennisService.createGame(setTennisId);		
		return convertToDto(game);		
	}
	
	private GameDto convertToDto(Game game) {
		GameDto gameDto = modelMapper.map(game, GameDto.class);	
		
		HashMap<Long, Collection<ScoreGameDto>> map = new HashMap<Long, Collection<ScoreGameDto>>();
		Collection<PlayerDto> playersDto = game.getSetTennis().getPlayers().stream().map(player -> modelMapper.map(player, PlayerDto.class)).collect(Collectors.toList());
		
		for(Player player : game.getSetTennis().getPlayers() ){			
			Collection<ScoreGame> scores = scoreGameService.getScoresGameByPlayer(game, player);
			Collection<ScoreGameDto> list = scores.stream().map(scoreGame -> modelMapper.map(scoreGame, ScoreGameDto.class)).collect(Collectors.toList());
			map.put(player.getId(), list);
		}
		gameDto.setGameOrder(gameService.getGameOrder(game));
		gameDto.setPlayers(playersDto);
		gameDto.setScoresMap(map);
		return gameDto;
	}
}
