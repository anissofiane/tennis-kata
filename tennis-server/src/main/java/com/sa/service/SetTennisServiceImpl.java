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
import com.sa.model.SetTennis;
import com.sa.repository.SetTennisRepository;

@Service
public class SetTennisServiceImpl implements SetTennisService{

	@Autowired
	private SetTennisRepository setTennisRepository;
	
	@Autowired
	private GameService gameService;
	
	@Override
	public SetTennis createSetTennis(Collection<Player> players) {
		
		SetTennis setTennis = setTennisRepository.save(new SetTennis());
		
		for(Player player : players)
			setTennis.addPlayer(player);
		
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
	
}
