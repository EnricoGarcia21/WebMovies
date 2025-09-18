package com.example.webmovies.repositories;

import com.example.webmovies.entities.Movie;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class MovieRepository {
    private List<Movie> movieList;
    public MovieRepository(){
        movieList = new ArrayList<>();
        movieList.addAll(List.of(new Movie("ET",1982,"ficçao"),
                new Movie("A noite dos Mortos Vivos",1968,"terror"),
                new Movie("Conan o bárbaro",1982,"aventura"),
                new Movie("Barbie",2023,"comédia")));
    }
    public boolean addMovie(Movie movie){
        movieList.add(movie);
        return true;
    }
    public List<Movie> getMovieList() {
        return movieList;
    }
}
