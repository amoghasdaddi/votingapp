package com.example.votingapp.service;

import com.example.votingapp.model.Candidate;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class VoteService {
    private final Map<String, Candidate> candidates = new ConcurrentHashMap<>();

    public void addCandidate(String name) {
        candidates.putIfAbsent(name, new Candidate(name));
    }

    public Optional<Candidate> getCandidate(String name) {
        return Optional.ofNullable(candidates.get(name));
    }

    public Map<String, Candidate> getAllCandidates() {
        return Collections.unmodifiableMap(candidates);
    }

    public Optional<Candidate> castVote(String name) {
        Candidate candidate = candidates.get(name);
        if (candidate != null) {
            candidate.incrementVote();
            return Optional.of(candidate);
        }
        return Optional.empty();
    }

    public Optional<Candidate> getWinner() {
        return candidates.values().stream().max(Comparator.comparing(Candidate::getVoteCount));
    }
}
