package com.sa.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;


@Entity
@Table(name = "PLAYER")
public class Player implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id 
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "PLAYER_ID")
	private Long id;
	
	@Column(name = "NAME")
	private String name;

	
	@ManyToMany(
	        cascade = {
	            CascadeType.PERSIST,
	            CascadeType.MERGE
	        }
	    )
	    @JoinTable(name = "SET_TENNIS_PLAYER",
	        joinColumns = @JoinColumn(name = "SET_ID"),
	        inverseJoinColumns = @JoinColumn(name = "PLAYER_ID")
	    )
    private Collection<SetTennis> sets = new ArrayList<SetTennis>();			
	
	public Player() {
		
	}
	
	public Player(String name) {
		super();		
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
	
	public Collection<SetTennis> getSets() {
		return sets;
	}

	public void setSets(Collection<SetTennis> sets) {
		this.sets = sets;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Player )) return false;
        return id != null && id.equals(((Player) o).getId());
    }
	
    @Override
    public int hashCode() {
        return id.hashCode();
    }

	
}
