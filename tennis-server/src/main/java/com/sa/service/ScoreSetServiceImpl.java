package com.sa.service;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.sa.model.Player;
import com.sa.model.ScoreSet;
import com.sa.model.SetTennis;
import com.sa.repository.ScoreSetRepository;

@Service
public class ScoreSetServiceImpl implements ScoreSetService {

	@Autowired
	private ScoreSetRepository scoreSetRepository;
	
	@Override
	public void addScore(SetTennis setTennis, Player player, int scoreValue) {
		ScoreSet scoreSet = new ScoreSet(player); 
		scoreSet.setScoreValue(scoreValue);
		scoreSet.setSetTennis(setTennis);
		scoreSetRepository.save(scoreSet);
	}

	@Override
	public ScoreSet getLastScorSet(SetTennis setTennis, Player player) {
		Specification<ScoreSet> scoresSet  = (scoreSet, cq, cb) -> cb.equal(scoreSet.get("setTennis").get("id"), setTennis.getId());
		Specification<ScoreSet> scoresSetPlayer  = (scoreSet, cq, cb) -> cb.equal(scoreSet.get("player").get("id"), player.getId());
				
		ScoreSet score = scoreSetRepository.findAll(scoresSet.and(scoresSetPlayer), Sort.by(Sort.Direction.DESC,"id")).get(0);
					
		return score;
	}

	@Override
	public Collection<ScoreSet> getScoresSetByPlayer(SetTennis setTennis, Player player) {
		
		Specification<ScoreSet> scoresSet  = (scoreSet, cq, cb) -> cb.equal(scoreSet.get("setTennis").get("id"), setTennis.getId());
		Specification<ScoreSet> scoresSetPlayer  = (scoreSet, cq, cb) -> cb.equal(scoreSet.get("player").get("id"), player.getId());
				
		List<ScoreSet> scores = scoreSetRepository.findAll(scoresSet.and(scoresSetPlayer), Sort.by(Sort.Direction.ASC,"id"));
		
		return scores;
	}

}
