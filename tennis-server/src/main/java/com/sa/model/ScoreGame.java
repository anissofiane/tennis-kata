package com.sa.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "SCORE_GAME")
public class ScoreGame implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id 
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "SCORE_GAME_ID")
	private Long id;

	@Column(name = "SCORE_VALUE")
	private int scoreValue; 
	
	@Column(name = "SCORE_ORDER")
	private int scoreOrder; 
	
	@OneToOne
	@JoinColumn(name = "PLAYER_ID" , referencedColumnName = "PLAYER_ID")
	private Player player;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "GAME_ID")
    private Game game;
	
	public ScoreGame() {
		
	}
	
	public ScoreGame(Player player) {
		super();		
		this.player = player;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getScoreValue() {
		return scoreValue;
	}

	public void setScoreValue(int scoreValue) {
		this.scoreValue = scoreValue;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}
	
	public int getScoreOrder() {
		return scoreOrder;
	}

	public void setScoreOrder(int scoreOrder) {
		this.scoreOrder = scoreOrder;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ScoreGame )) return false;
        return id != null && id.equals(((ScoreGame) o).getId());
    }
	
    @Override
    public int hashCode() {
        return id.hashCode();
    }
	
}
