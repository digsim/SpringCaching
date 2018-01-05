package org.homelinux.digsim.movies.actors;

import java.util.List;

/**
 * @author Digsim
 * Created on 03/01/18.
 */
public interface ActorsRepository {//extends Repository<Actor, Long> {

	public List<Actor> getByFirstname(String Name);

	public List<Actor> findAll();

	List<Actor> findGoodOnes();
}
