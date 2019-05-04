package com.sa.service;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.sa.model.Game;
import com.sa.model.Player;
import com.sa.model.ScoreGame;
import com.sa.model.SetTennis;
import com.sa.repository.GameRepository;

@Service
public class GameServiceImpl implements GameService {
	
	public  final static int POINT_1 = 15 ;
	public  final static int POINT_2 = 30 ;
	public  final static int POINT_3 = 40 ;
	
	public enum ScoreValueEnum {
		POINT_0("0"),POINT_1("15"),POINT_2("30"),POINT_3("40");
		private final String point;
		private ScoreValueEnum(String point){
			this.point = point;
		}
		
		public String getPoint(){
			return this.point;
		}
		
		public static ScoreValueEnum fromPoint(String point){
			ScoreValueEnum result= null;
			for(ScoreValueEnum scoreValue : values()){
				if(scoreValue.getPoint().equals(point)){
					result = scoreValue;
				}
			}
			
			return result;
		}
	}
	
	@Autowired
	private ScoreGameService scoreGameService;
	
	@Autowired
	private GameRepository gameRepository;
	
	@Autowired
	private PlayerService playerService;
	
	@Override
	public void addPoint(Game game, Player player) {
		boolean gameEnd = false;
		ScoreGame lastScoreGameA = scoreGameService.getLastScoreGame(game, player);
		ScoreValueEnum nextScoreValue = getNextPoint(lastScoreGameA.getScoreValue());		
		
		if(nextScoreValue == ScoreValueEnum.POINT_0){
			game.setWinner(player);
			gameEnd = true;
		}
		
		scoreGameService.addScore(game, player, nextScoreValue.getPoint());
					
		
		for(Player p : game.getSetTennis().getPlayers()){
			if(!player.equals(p)){
				ScoreGame lastScoreGameB = scoreGameService.getLastScoreGame(game, p);
				scoreGameService.addScore(game, p, gameEnd ? "0" : lastScoreGameB.getScoreValue());
			}
		}
						
		saveGame(game);
	}

	@Override
	public Game saveGame(Game game) {
		return gameRepository.save(game);
	}

	@Override
	public Game createGame(Collection<Player> players) {
		Game game = saveGame(new Game());
		for(Player player: players){
			scoreGameService.addScore(game, player, ScoreValueEnum.POINT_0.getPoint());
		}		
		return saveGame(game);		
	}
		
	private ScoreValueEnum getNextPoint(String scoreGame){
		ScoreValueEnum nextScoreValue = ScoreValueEnum.POINT_0;
		switch (ScoreValueEnum.fromPoint(scoreGame)) {
		case POINT_0:
			nextScoreValue = ScoreValueEnum.POINT_1;
			break;
		case POINT_1:
			nextScoreValue = ScoreValueEnum.POINT_2;
			break;		
		case POINT_2:
			nextScoreValue = ScoreValueEnum.POINT_3;
			break;
		case POINT_3:
			nextScoreValue = ScoreValueEnum.POINT_0;
			break;
		default:
			break;
		}
		
		return nextScoreValue;
	}

	@Override
	public Game getGameById(Long gameId) {
		return gameRepository.findById(gameId).get();
	}
	
	@Override
	public Game getCurrentGame(Long setId) {
		Specification<Game> winnerIsNull = (game, cq, cb) -> cb.isNull(game.get("winner"));
		Specification<Game> getGameSet = (game, cq, cb) -> cb.equal(game.get("setTennis").get("id"), setId);
		return gameRepository.findOne(getGameSet.and(winnerIsNull)).get();
	}

	@Override
	public Game addPoint(Long gameId, Long playerId) {
		Game game = getGameById(gameId);
		Player player = playerService.getPlayerById(playerId);
		addPoint(game, player);
		return game;
	}

	@Override
	public int getGameOrder(Game game) {
			
		Specification<Game> setTennisGames  = (g, cq, cb) -> cb.equal(g.get("setTennis").get("id"), game.getSetTennis().getId());
						
		List<Game> games = gameRepository.findAll(setTennisGames);
		
		Comparator<Game> comparator = Comparator.comparing( Game::getId );
			
		List<Game> sortedGames = games.stream().sorted(comparator).collect(Collectors.toList());
		
		return sortedGames.indexOf(game);
		
	}
	
}
