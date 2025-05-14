package com.codingninjas.EVotingSystem.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
public class Vote {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;

	// In Vote entity (inverse side)
	@OneToOne
	@JoinColumn(name = "user_id")
	@JsonBackReference
	private User user;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "election_id")
	@JsonBackReference
	private Election election;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "electionChoice_id")
	private ElectionChoice electionChoice;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Election getElection() {
		return election;
	}

	public void setElection(Election election) {
		this.election = election;
	}

	public ElectionChoice getElectionChoice() {
		return electionChoice;
	}

	public void setElectionChoice(ElectionChoice electionChoice) {
		this.electionChoice = electionChoice;
	}

}
