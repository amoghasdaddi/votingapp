package com.example.votingapp.controller;

import com.example.votingapp.model.Candidate;
import com.example.votingapp.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/votes")
public class VoteController {

    @Autowired
    private VoteService voteService;

    @PostMapping("/entercandidate")
    public void enterCandidate(@RequestParam String name) {
        voteService.addCandidate(name);
    }

    @PostMapping("/castvote")
    public String castVote(@RequestParam String name) {
        Optional<Candidate> candidate = voteService.castVote(name);
        return candidate.map(c -> "Vote casted for " + c.getName() + ". Current vote count: " + c.getVoteCount())
                .orElse("Candidate not found");
    }

    @GetMapping("/countvote")
    public String countVote(@RequestParam String name) {
        Optional<Candidate> candidate = voteService.getCandidate(name);
        return candidate.map(c -> c.getName() + " has " + c.getVoteCount() + " votes.")
                .orElse("Candidate not found");
    }

    @GetMapping("/listvote")
    public Map<String, Integer> listVote() {
        return voteService.getAllCandidates().entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, entry -> entry.getValue().getVoteCount()));
    }

    @GetMapping("/getwinner")
    public String getWinner() {
        Optional<Candidate> winner = voteService.getWinner();
        return winner.map(c -> "The winner is " + c.getName() + " with " + c.getVoteCount() + " votes.")
                .orElse("No candidates found");
    }
}
