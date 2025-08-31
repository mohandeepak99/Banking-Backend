package com.wipro.mohan.service;

import com.wipro.mohan.entities.Movie;
import com.wipro.mohan.enumtype.MovieGenre;
import com.wipro.mohan.exception.InvalidYearException;
import com.wipro.mohan.repos.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class MovieService {
	
	private final MovieRepository repo;

	public List<Movie> getMoviesByGenre(MovieGenre genre) {
		
		return repo.findByMovieGenre(genre);
	}

	public Movie addMovie(Movie movie) {
		
		return repo.save(movie);
	}

	public Page<Movie> getMovies(Pageable pageable ) {
		
		return repo.findAll(pageable);
	}
	
	public List<Movie> getMoviesByReleaseYear(int year) {
        if (String.valueOf(year).length() != 4) {
            throw new InvalidYearException(
                "Invalid year: " + year + ". Year must be exactly 4 digits"
            );
        }
        LocalDate start = LocalDate.of(year, 1, 1);
        LocalDate end = LocalDate.of(year, 12, 31);
        return repo.findByMovieYearOfReleaseBetween(Date.valueOf(start), Date.valueOf(end));
    }
	
	public List<Movie> getMoviesByIds(List<Long> ids) {
        return repo.findAllById(ids);
    }
	
	public List<Long> getRandomMovieIdsForTheatre(int count) {
        long totalMovies = repo.count();  // Get total count of movies in the DB
        Random random = new Random();
        List<Long> movieIds = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            long randomId = random.nextInt((int) totalMovies) + 1;  // Assuming movie IDs are sequential
            movieIds.add(randomId);
        }

        return movieIds;
    }
	
	public List<Long> getMoviesByNames(List<String> names) {
	    List<Movie> movies = repo.findAllByMovieNameIn(names);
	    return movies.stream()
	                 .map(Movie::getMovieId)
	                 .toList();
	}





}
