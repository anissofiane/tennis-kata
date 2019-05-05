package com.sa.util;

public class Constants {

	public static final String GET_PLAYER_BY_ID = "/player/getPlayer";
	public static final String GET_ALL_PLAYERS = "/player/getAllPlayers";
	public static final String SAVE_PLAYER = "/player/savePlayer";
	public static final String CREATE_SET = "/set/createSetTennis";
	public static final String CURRENT_GAME = "/game/currentGame/{setTennisId}";
	public static final String ADD_POINT = "/game/addPoint/{gameId}/{playerId}";
	public static final String CREATE_GAME = "/game/createGame/{setTennisId}";
	public static final String GET_SET = "/set/getSetTennis/{setTennisId}";
	public static final String UPDATE_SCORE_SET = "/set/updateScore/{setTennisId}";

}
