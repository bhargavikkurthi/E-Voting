package com.codingninjas.EVotingSystem.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codingninjas.EVotingSystem.entities.Election;
import com.codingninjas.EVotingSystem.entities.ElectionChoice;
import com.codingninjas.EVotingSystem.entities.User;
import com.codingninjas.EVotingSystem.entities.Vote;
import com.codingninjas.EVotingSystem.repositories.ElectionChoiceRepository;
import com.codingninjas.EVotingSystem.repositories.ElectionRepository;
import com.codingninjas.EVotingSystem.repositories.UserRepository;
import com.codingninjas.EVotingSystem.repositories.VoteRepository;

@Service
public class VotingService {

	@Autowired
	ElectionRepository electionRepository;

	@Autowired
	ElectionChoiceRepository electionChoiceRepository;

	@Autowired
	VoteRepository voteRepository;
	
	@Autowired
	UserRepository userRepository;

	// Election
	public void addElection(Election election) {
		electionRepository.save(election);
	}

	public List<Election> getAllElections() {
		return electionRepository.findAll();
	}

	public Election findElectionByName(String name) {
		return electionRepository.findByName(name)
				.orElseThrow(() -> new RuntimeException("Election not found: " + name));
	}


	// ElectionChoice
	public void addElectionChoice(ElectionChoice electionChoice) {
		electionChoiceRepository.save(electionChoice);
	}

	public List<ElectionChoice> getAllElectionChoices() {
		return electionChoiceRepository.findAll();
	}

	public long countChoicesByElection(Election election) {
		return electionChoiceRepository.countByElection(election);
	}

	public ElectionChoice findElectionChoiceByNameAndElection(String name, Election election) {
		return electionChoiceRepository.findByNameAndElection(name, election)
				.orElseThrow(() -> new RuntimeException("Election choice not found: " + name));
	}

	public ElectionChoice findElectionChoiceWithMaxVotes(Election election) {
		return electionChoiceRepository.findElectionChoiceWithMaxVotes(election.getId());
	}


	// User
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	public void addUser(User user) {
		userRepository.save(user);
	}

	public User findUserByName(String name) {
		return userRepository.findByName(name)
				.orElseThrow(() -> new RuntimeException("User not found: " + name));
	}


	// Vote
	public List<Vote> getAllVotes() {
		return voteRepository.findAll();
	}

	public void addVote(Vote vote) {
		voteRepository.save(vote);
	}

	public long countTotalVotes() {
		return voteRepository.countTotalVotes();
	}

	public long countVotesByElection(Election election) {
		return voteRepository.countVotesByElection(election);
	}

}
