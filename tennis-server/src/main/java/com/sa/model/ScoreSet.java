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
@Table(name = "SCORE_SET")
public class ScoreSet implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id 
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "SCORE_SET_ID")
	private Long id;

	@Column(name = "SCORE_VALUE")
	private int scoreValue; 
	
	@OneToOne	
	@JoinColumn(name = "PLAYER_ID" , referencedColumnName = "PLAYER_ID")
	private Player player;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SET_ID")
    private SetTennis setTennis;
	
	
	public ScoreSet() {
	
	}
	
	public ScoreSet(Player player) {
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
	
	public SetTennis getSetTennis() {
		return setTennis;
	}

	public void setSetTennis(SetTennis setTennis) {
		this.setTennis = setTennis;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ScoreSet )) return false;
        return id != null && id.equals(((ScoreSet) o).getId());
    }
	
    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
