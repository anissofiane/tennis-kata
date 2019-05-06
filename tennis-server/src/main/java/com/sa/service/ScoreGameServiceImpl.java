package com.sa.service;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.sa.model.Game;
import com.sa.model.Player;
import com.sa.model.ScoreGame;
import com.sa.repository.ScoreGameRepository;

@Service
public class ScoreGameServiceImpl implements ScoreGameService {

	@Autowired
	private ScoreGameRepository scoreGameRepository;
	
	@Override
	public void addScore(Game game, Player player, String scoreValue) {
		ScoreGame scoreGame = new ScoreGame(player);		
		scoreGame.setScoreValue(scoreValue);
		scoreGame.setGame(game);
		scoreGameRepository.save(scoreGame);
	}

	@Override
	public ScoreGame getLastScoreGame(Game game, Player player) {
		
		Specification<ScoreGame> scoresGame  = (scoreGame, cq, cb) -> cb.equal(scoreGame.get("game").get("id"), game.getId());
		Specification<ScoreGame> scoresGamePlayer  = (scoreGame, cq, cb) -> cb.equal(scoreGame.get("player").get("id"), player.getId());
				
		ScoreGame score = scoreGameRepository.findAll(scoresGame.and(scoresGamePlayer), Sort.by(Sort.Direction.DESC,"id")).get(0);
					
		return score;
	}

	@Override
	public Collection<ScoreGame> getScoresGameByPlayer(Game game, Player player) {
		
		Specification<ScoreGame> scoresGame  = (scoreGame, cq, cb) -> cb.equal(scoreGame.get("game").get("id"), game.getId());
		Specification<ScoreGame> scoresGamePlayer  = (scoreGame, cq, cb) -> cb.equal(scoreGame.get("player").get("id"), player.getId());			
		
		List<ScoreGame> scores = scoreGameRepository.findAll(scoresGame.and(scoresGamePlayer), Sort.by(Sort.Direction.ASC,"id"));
							
		return scores;
	}
	
}
