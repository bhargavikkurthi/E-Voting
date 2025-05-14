package com.codingninjas.EVotingSystem.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.codingninjas.EVotingSystem.entities.Election;
import com.codingninjas.EVotingSystem.entities.ElectionChoice;

public interface ElectionChoiceRepository extends JpaRepository<ElectionChoice,Long> {
	
	@Query("Select count(ec) From ElectionChoice ec where ec.election = :election")
	long countByElection(@Param("election") Election election);

	Optional<ElectionChoice> findByNameAndElection(String electionChoiceName, Election election);

	@Query(value = """
            SELECT ec.*
            FROM election_choice ec
            JOIN vote v ON ec.id = v.election_choice_id
            WHERE ec.election_id = :electionId
            GROUP BY ec.id
            ORDER BY COUNT(v.id) DESC
            LIMIT 1
            """, nativeQuery = true)
	ElectionChoice findElectionChoiceWithMaxVotes(@Param("electionId") Long electionId);

	Page<ElectionChoice> findAll(Pageable pageable);

//	native SQL version that handles ties
//	@Query(value = """
//    SELECT ec.*
//    FROM election_choice ec
//    JOIN (
//        SELECT election_choice_id, COUNT(*) AS vote_count
//        FROM vote
//        WHERE election_id = :electionId
//        GROUP BY election_choice_id
//    ) vc ON ec.id = vc.election_choice_id
//    WHERE vc.vote_count = (
//        SELECT MAX(vote_count)
//        FROM (
//            SELECT COUNT(*) AS vote_count
//            FROM vote
//            WHERE election_id = :electionId
//            GROUP BY election_choice_id
//        ) AS counts
//    )
//    """, nativeQuery = true)
//	List<ElectionChoice> findElectionChoicesWithMaxVotes(@Param("electionId") Long electionId);

}
