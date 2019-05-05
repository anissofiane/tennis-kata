package com.sa.service;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.persistence.criteria.JoinType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.sa.model.Game;
import com.sa.model.Player;
import com.sa.model.ScoreSet;
import com.sa.model.SetTennis;
import com.sa.repository.SetTennisRepository;

@Service
public class SetTennisServiceImpl implements SetTennisService{

	@Autowired
	private SetTennisRepository setTennisRepository;
	
	@Autowired
	private GameService gameService;
	
	@Autowired
	private ScoreSetService scoreSetService;
	
	@Override
	public SetTennis createSetTennis(Collection<Player> players) {
		
		SetTennis setTennis = setTennisRepository.save(new SetTennis());
		
		for(Player player : players){
			setTennis.addPlayer(player);
			scoreSetService.addScore(setTennis, player, 0);			
		}
		
		Game game = gameService.createGame(players);		
		setTennis.addGame(game);		
		
		return setTennisRepository.save(setTennis);
	}

	@Override
	public SetTennis getSetTennisById(Long id) {
		return setTennisRepository.findById(id).get();
	}

	@Override
	public Game createGame(Long setTennisId) {
		SetTennis setTennis = getSetTennisById(setTennisId);
		Game game = gameService.createGame(setTennis.getPlayers());		
		setTennis.addGame(game);
		setTennisRepository.save(setTennis);
		return gameService.getGameById(game.getId());
	}

	@Override
	public int getSetOrder(SetTennis setTennis) {
		Iterator<Player> it = setTennis.getPlayers().iterator();
		Specification<SetTennis> sets  = (s, cq, cb) -> cb.equal(s.joinCollection("players", JoinType.LEFT).get("id"), it.next().getId());
		List<SetTennis> setTennisList = setTennisRepository.findAll(sets);
		return setTennisList.indexOf(setTennis);
	}

	@Override
	public void addPointSet(SetTennis setTennis, Player player) {
		
		Player otherPlayer = null;
		for(Player p : setTennis.getPlayers()){
			if(!player.equals(p)){
				otherPlayer = p;
			}
		}
		
		ScoreSet lastScoreSetA = scoreSetService.getLastScorSet(setTennis, player);
		scoreSetService.addScore(setTennis, player, lastScoreSetA.getScoreValue()+1);	
		
		ScoreSet lastScoreSetB = scoreSetService.getLastScorSet(setTennis, otherPlayer);
		scoreSetService.addScore(setTennis, otherPlayer, lastScoreSetB.getScoreValue());
		
		if(lastScoreSetA.getScoreValue() == 5 && lastScoreSetB.getScoreValue() == 6){
			setTennis.setTieBreak(true);
		}else if((lastScoreSetA.getScoreValue() == 5 && lastScoreSetB.getScoreValue() < 5) ||
				(lastScoreSetA.getScoreValue() == 6 && lastScoreSetB.getScoreValue() < 7 && !setTennis.isTieBreak()) ||
				(lastScoreSetA.getScoreValue()>=6 && lastScoreSetB.getScoreValue()>=6 && (lastScoreSetA.getScoreValue() - lastScoreSetB.getScoreValue() > 0) && setTennis.isTieBreak())){
			setTennis.setWinner(player);
		}
	}

	@Override
	public void updateScore(SetTennis setTennis) {
		Game lastGame = gameService.getLastGame(setTennis.getId());
		if(lastGame.getWinner() != null){
			addPointSet(setTennis, lastGame.getWinner());
			setTennisRepository.save(setTennis);
		}
	}
	
}
