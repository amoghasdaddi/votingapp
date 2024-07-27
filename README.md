# Voting Application

This is a simple voting application built using Java and Spring Boot. It provides RESTful APIs to manage candidates, cast votes, and retrieve voting results. The data is stored in-memory using local variables.

## Features

- **Enter Candidate**: Add a candidate with an initial vote count of 0.
- **Cast Vote**: Increment the vote count for a specified candidate.
- **Count Vote**: Get the current vote count for a specified candidate.
- **List Votes**: Retrieve all candidates and their vote counts.
- **Get Winner**: Retrieve the candidate with the highest number of votes.
- **Simultaneous Execution**: APIs are designed to be used by multiple users simultaneously.
- **Unit Testing**: Unit tests are implemented for all API endpoints.

## Requirements

- Java 8 or higher
- Maven
- Spring Boot

## Usage Guide

### Starting the Application

1. Clone the repository:
   ```sh
   https://github.com/amoghasdaddi/votingapp.git
