package org.homelinux.digsim.movies;

import org.homelinux.digsim.account.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author Digsim
 * Created on 04/01/18.
 */
public interface MoviesRepository extends JpaRepository<Movie, Long> {

	List<Movie> findByTitle(String title);

	@Query("select count(m) > 0 from Movie m where m.title = :title")
	boolean exists(@Param("title") String title);

	@Query(value = "select * from Movie m where m.imdbrating>?1", nativeQuery = true)
	List<Movie> findHighRatedMovies(Double ratingLimit);
}
