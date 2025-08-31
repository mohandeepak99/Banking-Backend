package com.wipro.mohan.controller;


import com.wipro.mohan.entities.Movie;
import com.wipro.mohan.enumtype.MovieGenre;
import com.wipro.mohan.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.wipro.mohan.entities.*;
import com.wipro.mohan.service.*;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/movies")
@RequiredArgsConstructor

public class MovieController {
	
	private final MovieService movieService;
	
	@GetMapping("/genre/{genre}")
    public List<Movie> getMoviesByGenre(@PathVariable String genre){
		MovieGenre parsed = MovieGenre.from(genre);
        return movieService.getMoviesByGenre(parsed);
    }

    @PostMapping("/add")
    public Movie addMovie(@RequestBody Movie movie){
        return  movieService.addMovie(movie);
    }
    
    @GetMapping("/getMovies")
   	public Page<Movie> getMovies(@PageableDefault(size=5,sort="movieId") Pageable pageable)
    {
    	return  movieService.getMovies(pageable);
    }
    
    @GetMapping("/year/{year}")
    public List<Movie> getMoviesByReleaseYear(@PathVariable int year) {
        return movieService.getMoviesByReleaseYear(year);
    }
    
 // Example: GET /api/movies/by-ids?ids=1,2,3
    @GetMapping("/by-ids")
    public List<Movie> getMoviesByIds(@RequestParam List<Long> ids) {
        return movieService.getMoviesByIds(ids);
    }
    
    @GetMapping("/getmovieidsfortheatre")
    public List<Long> getRandomMovieIdsForTheatre(@RequestParam int count) {
        return movieService.getRandomMovieIdsForTheatre(count);  
    }
    
    @PostMapping("/idsOfMovieNames")
    public List<Long> getMoviesByNames(@RequestBody List<String> names) {
        return movieService.getMoviesByNames(names);
    }



    
	

}
