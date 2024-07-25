package com.example.votingapp;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration(classes = {VotingappApplication.class, VotingappApplicationTests.TestConfig.class})
class VotingappApplicationTests {

	@Configuration
	static class TestConfig {
		// Define any beans or configurations needed for your tests
	}

	@Test
	void contextLoads() {
	}

}
