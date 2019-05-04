package com.sa.service;

import java.util.Collection;

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
	public SetTennis findSetTennis(Long id) {
		return setTennisRepository.findById(id).get();
	}
	
}
