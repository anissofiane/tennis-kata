package com.sa.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "SCORE_GAME")
public class ScoreGame implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id 
	@SequenceGenerator(name="seqScoreGame", sequenceName = "SEQ_SCORE_GAME",  initialValue=1, allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE , generator="seqScoreGame")
	@Column(name = "SCORE_GAME_ID")
	private Long id;

	@Column(name = "SCORE_VALUE")
	private String scoreValue; 
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PLAYER_ID")
	private Player player;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
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

	public String getScoreValue() {
		return scoreValue;
	}

	public void setScoreValue(String scoreValue) {
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
