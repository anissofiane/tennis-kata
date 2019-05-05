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
		POINT_0("0"), POINT_1("15"), POINT_2("30"), POINT_3("40"), POINT_ADV("ADV"), POINT_DEUCE("DEUCE");
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
		
		ScoreGame lastScoreGameA = scoreGameService.getLastScoreGame(game, player);
		ScoreGame lastScoreGameB = null;
		Player otherplayer = null;
						
		for(Player p : game.getSetTennis().getPlayers()){
			if(!player.equals(p)){
				lastScoreGameB = scoreGameService.getLastScoreGame(game, p);
				otherplayer = p;
			}
		}
		ScoreValueEnum[] nextScoreValue = getNextPoint(lastScoreGameA.getScoreValue(), lastScoreGameB.getScoreValue());		
		
		if(nextScoreValue[0] == ScoreValueEnum.POINT_0){
			game.setWinner(player);			
		}
		
		scoreGameService.addScore(game, player, nextScoreValue[0].getPoint());
		
		scoreGameService.addScore(game, otherplayer, nextScoreValue[1].getPoint());
										
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
		
	private ScoreValueEnum[] getNextPoint(String scoreGameA, String scoreGameB){
		ScoreValueEnum[] nextScoreValues = new ScoreValueEnum [2];		
		ScoreValueEnum scoreGameBEnumm = ScoreValueEnum.fromPoint(scoreGameB);
		switch (ScoreValueEnum.fromPoint(scoreGameA)) {
		case POINT_0:
			nextScoreValues [0] = ScoreValueEnum.POINT_1;
			nextScoreValues [1] = scoreGameBEnumm;
			break;
		case POINT_1:
			nextScoreValues [0] = ScoreValueEnum.POINT_2;
			nextScoreValues [1] = scoreGameBEnumm;
			break;		
		case POINT_2:
			nextScoreValues [0] = ScoreValueEnum.POINT_3;
			nextScoreValues [1] = scoreGameBEnumm;
			break;
		case POINT_3:
			if(scoreGameBEnumm == ScoreValueEnum.POINT_3){
				nextScoreValues [0] = ScoreValueEnum.POINT_ADV;
				nextScoreValues [1] = scoreGameBEnumm;
			}else if(scoreGameBEnumm == ScoreValueEnum.POINT_ADV){
				nextScoreValues [0] = ScoreValueEnum.POINT_DEUCE;
				nextScoreValues [1] = ScoreValueEnum.POINT_DEUCE;
			}else {
				nextScoreValues [0] = ScoreValueEnum.POINT_0;
				nextScoreValues [1] = ScoreValueEnum.POINT_0;
			}				
			break;
		case POINT_ADV:
			nextScoreValues [0] = ScoreValueEnum.POINT_0;
			nextScoreValues [1] = ScoreValueEnum.POINT_0;
			break;
		case POINT_DEUCE:
			nextScoreValues [0] = ScoreValueEnum.POINT_ADV;
			nextScoreValues [1] = ScoreValueEnum.POINT_3;
			break;
		default:
			nextScoreValues [0] = ScoreValueEnum.POINT_0;
			nextScoreValues [1] = ScoreValueEnum.POINT_0;
			break;
		}
		
		
		return nextScoreValues;
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
