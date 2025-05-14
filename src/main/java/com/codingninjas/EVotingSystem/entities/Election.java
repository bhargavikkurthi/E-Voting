package com.codingninjas.EVotingSystem.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Election {
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private long id;
	
	@Column(unique=true)
	private String name;

	@OneToMany(mappedBy = "election", cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<ElectionChoice> electionChoiceList;

	@OneToMany(mappedBy = "election", cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<Vote> voteList;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<ElectionChoice> getElectionChoiceList() {
		return electionChoiceList;
	}

	public void setElectionChoiceList(List<ElectionChoice> electionChoiceList) {
		this.electionChoiceList = electionChoiceList;
	}

	public List<Vote> getVoteList() {
		return voteList;
	}

	public void setVoteList(List<Vote> voteList) {
		this.voteList = voteList;
	}
}
