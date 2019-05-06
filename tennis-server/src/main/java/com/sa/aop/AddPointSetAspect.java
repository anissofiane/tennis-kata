package com.sa.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sa.model.Game;
import com.sa.service.GameService;
import com.sa.service.SetTennisService;

@Aspect
@Component
public class AddPointSetAspect {

	@Autowired
	private GameService gameService;
	
	@Autowired
	private SetTennisService setTennisService;
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
			 
	
	@Around("@annotation(AddPointSet)")
	public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
		
		Object proceed = joinPoint.proceed();
		
		if (proceed instanceof Game) {
			Game g = (Game) proceed;
			Game game = gameService.getGameById(g.getId());
			logger.debug("AOP Update Score Set id = " + game.getSetTennis().getId() );
			setTennisService.updateScore(game.getSetTennis());
		}
		
	    return proceed;
	}
}
