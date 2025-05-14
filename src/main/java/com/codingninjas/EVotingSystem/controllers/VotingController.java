package com.codingninjas.EVotingSystem.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.codingninjas.EVotingSystem.entities.Election;
import com.codingninjas.EVotingSystem.entities.ElectionChoice;
import com.codingninjas.EVotingSystem.entities.User;
import com.codingninjas.EVotingSystem.entities.Vote;
import com.codingninjas.EVotingSystem.services.VotingService;

@RestController
public class VotingController {
	
	@Autowired
	VotingService votingService;


	//	Election Endpoints
	@PostMapping("/election")
	public void addElection(@RequestBody Election election) {
		votingService.addElection(election);
	}

	@GetMapping("/elections")
	public List<Election> getAllElections(){
		return votingService.getAllElections();
	}


	//	ElectionChoice Endpoints
	@PostMapping("/election-choices")
	public void addElectionChoice(@RequestBody ElectionChoice electionChoice) {
		Election election =
				votingService.findElectionByName(electionChoice.getElection().getName());
		electionChoice.setElection(election);
		votingService.addElectionChoice(electionChoice);
	}

	@GetMapping("/election-choices")
	public List<ElectionChoice> getAllElectionChoices() {
		return votingService.getAllElectionChoices();
	}

	@PostMapping("/election-choices/count")
	public long getTotalNumberOfChoicesByElection(@RequestBody Election election) {
		Election actualElection = votingService.findElectionByName(election.getName());
		return votingService.countChoicesByElection(actualElection);
	}


//	User Endpoints
	@GetMapping("/users")
	public List<User> getAllUsers(){
		return votingService.getAllUsers();
	}
	
	@PostMapping("/user")
	public void addUser(@RequestBody User user) {
		votingService.addUser(user);
	}


//	Vote Endpoints
	@GetMapping("/votes")
	public List<Vote> getAllVotes() {
		return votingService.getAllVotes();
	}

	@PostMapping("/vote")
	public void addVote(@RequestBody Vote vote) {
		User user =
				votingService.findUserByName(vote.getUser().getName());
		Election election =
				votingService.findElectionByName(vote.getElection().getName());
		ElectionChoice choice =
				votingService.findElectionChoiceByNameAndElection(
				vote.getElectionChoice().getName(), election);

		vote.setUser(user);
		vote.setElection(election);
		vote.setElectionChoice(choice);
		votingService.addVote(vote);
	}

	@GetMapping("/votes/count")
	public long getTotalVotes() {
		return votingService.countTotalVotes();
	}

	@PostMapping("/votes/count/election")
	public long getTotalNumberOfVotesByElection(@RequestBody Election election) {
		Election actualElection =
				votingService.findElectionByName(election.getName());
		return votingService.countVotesByElection(actualElection );
	}


//	Results Endpoints
	@PostMapping("results/winner")
	public ElectionChoice getWinnerOfElection(@RequestBody Election election) {
		Election actualElection =
				votingService.findElectionByName(election.getName());
		return votingService.findElectionChoiceWithMaxVotes(actualElection);
	}

}
