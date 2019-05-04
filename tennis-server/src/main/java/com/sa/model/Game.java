package com.sa.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "GAME")
public class Game implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id 
	@SequenceGenerator(name="seqGame", sequenceName = "SEQ_GAME",  initialValue=1, allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE , generator="seqGame")
	@Column(name = "GAME_ID")
	private Long id;

	@OneToMany(
	        mappedBy = "game",
	        cascade = CascadeType.MERGE,
	        orphanRemoval = true,
	        fetch = FetchType.LAZY
	    )
    private Collection<ScoreGame> scores = new ArrayList<ScoreGame>();
		
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "SET_ID")
    private SetTennis setTennis;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "PLAYER_ID")
    private Player winner;
	
	public Game() {
	
	}
		
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	 public void addScore(ScoreGame score) {
	        scores.add(score);
	        score.setGame(this);
	 }
	 
     public void removeScore(ScoreGame score) {
        scores.remove(score);
        score.setGame(null);
     }
     
     public SetTennis getSetTennis() {
		return setTennis;
	}

	public void setSetTennis(SetTennis setTennis) {
		this.setTennis = setTennis;
	}

	public Player getWinner() {
		return winner;
	}

	public void setWinner(Player winner) {
		this.winner = winner;
	}

	public Collection<ScoreGame> getScores() {
		return scores;
	}

	@Override
     public boolean equals(Object o) {
         if (this == o) return true;
         if (!(o instanceof Game )) return false;
         return id != null && id.equals(((Game) o).getId());
     }
 	
     @Override
     public int hashCode() {
         return id.hashCode();
     }
        
}
