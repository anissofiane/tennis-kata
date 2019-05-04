package com.sa.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.PostLoad;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.function.Predicate;

@Entity
@Table(name = "SET_TENNIS")
public class SetTennis implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id 
	@SequenceGenerator(name="seqSetTennis", sequenceName = "SEQ_SET_TENNIS",  initialValue=1, allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE , generator="seqSetTennis")
	@Column(name = "SET_ID")
	private Long id;
		
	@ManyToMany(mappedBy = "sets", fetch =  FetchType.EAGER)
    private Collection<Player> players = new ArrayList<Player>();
	 
	@OneToMany(
	        mappedBy = "setTennis",
	        cascade = CascadeType.MERGE,
	        orphanRemoval = true,
	        fetch =  FetchType.LAZY
	    )
    private Collection<Game> games = new ArrayList<Game>();
	
	@OneToMany(
	        mappedBy = "setTennis",
	        cascade = CascadeType.MERGE,
	        orphanRemoval = true,
	        fetch =  FetchType.LAZY
	    )
    private Collection<ScoreSet> scores = new ArrayList<ScoreSet>();
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Collection<Player> getPlayers() {
		return players;
	}

	public void addPlayer(Player player){
		players.add(player);
		player.getSets().add(this);
	}
	
	public void removePLayer(Player player){
		players.remove(player);
		player.getSets().remove(this);
	}

	public Collection<Game> getGames() {
		return games;
	}

	public void addGame(Game game){
		games.add(game);
		game.setSetTennis(this);
	}
	
	public void removeGame(Game game){
		games.remove(game);
		game.setSetTennis(null);
	}
	

	public Collection<ScoreSet> getScores() {
		return scores;
	}

	public void addScore(ScoreSet score){
		scores.add(score);
		score.setSetTennis(this);
	}
	
	public void removeScore(ScoreSet score){
		scores.remove(score);
		score.setSetTennis(null);
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SetTennis )) return false;
        return id != null && id.equals(((SetTennis) o).getId());
    }
	
    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
