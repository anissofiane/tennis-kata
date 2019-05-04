package com.sa.dto;

import com.sa.model.Player;

public class PlayerDto {

	private Long id;
	private String name;
	
	public PlayerDto() {
		
	}
	
	public PlayerDto(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PlayerDto )) return false;
        return id != null && id.equals(((PlayerDto) o).getId());
    }
	
    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
