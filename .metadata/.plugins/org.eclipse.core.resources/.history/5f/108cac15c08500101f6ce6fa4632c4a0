package com.wipro.mohan.entities;

import java.util.Date;

import com.wipro.mohan.enumtype.MovieGenre;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Movie {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long movieId;
	
	private String movieName;
	private Long movieDuration;
	@Enumerated(EnumType.STRING)
	private MovieGenre movieGenre;
	@Temporal(TemporalType.DATE)
	private Date movieYearOfRelease;

}
