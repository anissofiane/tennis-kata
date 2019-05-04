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
import com.sa.model.SetTennis;
import com.sa.service.GameService;
import com.sa.service.PlayerService;
import com.sa.service.SetTennisService;
import com.sa.util.Constants;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class GameController {

	@Autowired
	private PlayerService playerService;
	
	@Autowired
	private GameService gameService;
	
	private ModelMapper modelMapper = new ModelMapper();
	
	
	@RequestMapping(value = Constants.CURRENT_GAME, method = RequestMethod.GET)
	public GameDto getCurrentGame(@PathVariable("setId") Long setId){		
		return convertToDto(gameService.getCurrentGame(setId));
	}
	
	@RequestMapping(value = Constants.ADD_POINT, method = RequestMethod.GET)
	public GameDto addPoint(@PathVariable("gameId") Long gameId, @PathVariable("playerId") Long playerId){
		Game game = gameService.getGameById(gameId);
		Player player = playerService.getPlayerById(playerId);
		gameService.addPoint(game, player);
		return convertToDto(game);
	}
		
	private GameDto convertToDto(Game game) {
		GameDto gameDto = modelMapper.map(game, GameDto.class);	
		
		HashMap<Long, Collection<ScoreGameDto>> map = new HashMap<Long, Collection<ScoreGameDto>>();
		Collection<PlayerDto> playersDto = game.getSetTennis().getPlayers().stream().map(player -> modelMapper.map(player, PlayerDto.class)).collect(Collectors.toList());
		Comparator<ScoreGameDto> comparator = Comparator.comparing( ScoreGameDto::getScoreOrder );
		for(PlayerDto playerDto : playersDto ){			
			Predicate<ScoreGameDto> predicate = (ScoreGameDto score) -> score.getPlayer().equals(playerDto);
			List<ScoreGameDto> list = gameDto.getScores().stream().filter(predicate).sorted(comparator).collect(Collectors.toList());
			map.put(playerDto.getId(), list);
		}
		
		gameDto.setPlayers(playersDto);
		gameDto.setScoresMap(map);
		return gameDto;
	}
}
