package com.wipro.mohan.repos;

import com.wipro.mohan.entities.Movie;
import com.wipro.mohan.enumtype.MovieGenre;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    // for the single paginated endpoint
    Page<Movie> findAll(Pageable pageable);

    // non-paginated filters
    List<Movie> findByMovieGenre(MovieGenre genre);
    List<Movie> findByMovieYearOfReleaseBetween(Date start, Date end);
    List<Movie> findAllByMovieNameIn(List<String> movieNames);



}
