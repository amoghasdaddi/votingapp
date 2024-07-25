package com.example.votingapp.controller;

import com.example.votingapp.model.Candidate;
import com.example.votingapp.service.VoteService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(VoteController.class)
public class VoteControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VoteService voteService;

    @Test
    public void testEnterCandidate() throws Exception {
        mockMvc.perform(post("/api/votes/entercandidate?name=Amogh"))
                .andExpect(status().isOk());
        verify(voteService, times(1)).addCandidate("Amogh");
    }

    @Test
    public void testCastVote() throws Exception {
        Candidate candidate = new Candidate("Amogh");
        when(voteService.castVote("Amogh")).thenReturn(Optional.of(candidate));

        mockMvc.perform(post("/api/votes/castvote?name=Amogh"))
                .andExpect(status().isOk())
                .andExpect(content().string("Vote casted for Amogh. Current vote count: 1"));
    }

    @Test
    public void testCountVote() throws Exception {
        when(voteService.getCandidate("Amogh")).thenReturn(Optional.of(new Candidate("Amogh")));

        mockMvc.perform(post("/api/votes/countvote?name=Amogh"))
                .andExpect(status().isOk())
                .andExpect(content().string("Amogh has 0 votes."));
    }

    @Test
    public void testListVote() throws Exception {
        mockMvc.perform(get("/api/votes/listvote"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetWinner() throws Exception {
        Candidate amogh = new Candidate("Amogh");
        amogh.incrementVote();
        when(voteService.getWinner()).thenReturn(Optional.of(amogh));

        mockMvc.perform(get("/api/votes/getwinner"))
                .andExpect(status().isOk())
                .andExpect(content().string("The winner is Amogh with 1 votes."));
    }
}
