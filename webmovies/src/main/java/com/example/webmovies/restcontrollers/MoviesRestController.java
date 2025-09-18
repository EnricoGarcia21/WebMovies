package com.example.webmovies.restcontrollers;

import com.example.webmovies.entities.Erro;
import com.example.webmovies.entities.Movie;
import com.example.webmovies.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
@CrossOrigin
@RestController
@RequestMapping(value="apis")
public class MoviesRestController {

    @Autowired
    private MovieRepository movieRepository;

    @GetMapping(value = "test")
    public ResponseEntity<Object> test(){
        return ResponseEntity.ok("status ok");
    }

    @GetMapping(value="random-movie")
    public ResponseEntity<Object> getRandomMovie(){
        Random rand = new Random();
        List<Movie> movies=movieRepository.getMovieList();
        return ResponseEntity.ok(movies.get(rand.nextInt(movies.size())));
    }

    @GetMapping(value="list-movies")
    public ResponseEntity<Object> getMovies(){
        return ResponseEntity.ok(movieRepository.getMovieList());
    }

    @GetMapping(value = "get-movie")
    public ResponseEntity<Object> getMovieByTitle(@RequestParam(value = "title") String title){
        Movie movie=null;
        if(title!=null && !title.isEmpty()){
            List<Movie> tmp = movieRepository.getMovieList();
            for(Movie m:tmp)
                if(m.getTitulo().equalsIgnoreCase(title))
                    movie=m;
        }
        if(movie!=null)
            return ResponseEntity.ok(movie);
        else
            return ResponseEntity.badRequest().body(new Erro("filme não encontrado","não foi encontrado na base de dados"));
    }
    @GetMapping(value = "get-movie/{title}")
    public ResponseEntity<Object> getMovieByTitlePath(@PathVariable(value = "title") String title){
        Movie movie=null;
        if(title!=null && !title.isEmpty()){
            List<Movie> tmp = movieRepository.getMovieList();
            for(Movie m:tmp)
                if(m.getTitulo().equalsIgnoreCase(title))
                    movie=m;
        }
        if(movie!=null)
            return ResponseEntity.ok(movie);
        else
            return ResponseEntity.badRequest().body(new Erro("filme não encontrado","não foi encontrado na base de dados"));
    }

    @GetMapping(value = "get-movie-keyword/{title}")
    public ResponseEntity<Object> getMovieByKeyWord(@PathVariable(value = "title") String title){
        Movie movie=null;
        if(title!=null && !title.isEmpty()){
            List<Movie> tmp = movieRepository.getMovieList();
            for(Movie m:tmp)
                if(m.getTitulo().toUpperCase().contains(title.toUpperCase()))
                    movie=m;
        }
        if(movie!=null)
            return ResponseEntity.ok(movie);
        else
            return ResponseEntity.badRequest().body(new Erro("filme não encontrado","não foi encontrado na base de dados"));
    }

    @PostMapping(value = "add-movie")
    public ResponseEntity<Object> addMovie(@RequestBody Movie movie){
       if(movie.getTitulo()!=null && !movie.getTitulo().isEmpty()){
           movieRepository.addMovie(movie);//adicionar um novo filme
           return ResponseEntity.ok(movie);
       }
       return ResponseEntity.badRequest().body(new Erro("Erro no cadastro","Informações incompletas sobre o filme"));
    }

//    public List<Movie> getAllMovies(){
//        List<Movie> movies = Arrays.asList(new Movie("ET",1982,"ficçao"),
//                new Movie("A noite dos Mortos Vivos",1968,"terror"),
//                new Movie("Conan o bárbaro",1982,"aventura"),
//                new Movie("Barbie",2023,"comédia"));
//        return movies;
//    }


}
