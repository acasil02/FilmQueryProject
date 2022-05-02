package com.skilldistillery.filmquery.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

public class DatabaseAccessorObject implements DatabaseAccessor {
	private static final String URL = "jdbc:mysql://localhost:3306/sdvid?useSSL=false";
	private final String user = "student";
	private final String pass = "student";

	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.err.println("Error loading my SQL Driver!");
			e.printStackTrace();
		}
	}

	@Override
	public Film findFilmById(int filmId) {
		Film film = null;
		String sqltxt = "SELECT * FROM film WHERE id = ?;";
		try {
			Connection conn = DriverManager.getConnection(URL, user, pass);
			java.sql.PreparedStatement stmt = conn.prepareStatement(sqltxt);
			stmt.setInt(1, filmId);
			ResultSet fResult = stmt.executeQuery();
			if (fResult.next()) {
				film = new Film();
				film.setFilmId(fResult.getInt("id"));
				film.setTitle(fResult.getString("title"));
				film.setReleaseYear(fResult.getString("release_year"));
				film.setRating(fResult.getString("rating"));
				film.setDescription(fResult.getString("description"));
				film.setLanguageId(fResult.getInt("language_id"));
				film.setFilmLanguage(getLanguageForFilm(filmId));
				film.setActors(findActorsByFilmId(filmId));
			} else {
				System.out.println("Film not found containing that ID");
			}
			fResult.close();
			stmt.close();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return film;
	}

	public String getLanguageForFilm(int filmId) {
		String filmLanguage = "";
		String sqltxt = "SELECT language.name FROM language JOIN film ON film.language_id = language.id WHERE film.id = ?;";
		try {
			Connection conn = DriverManager.getConnection(URL, user, pass);
			java.sql.PreparedStatement stmt = conn.prepareStatement(sqltxt);
			stmt.setInt(1, filmId);
			ResultSet fResult = stmt.executeQuery();
			while (fResult.next()) {
				filmLanguage = fResult.getString(1);
			}

			fResult.close();
			stmt.close();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return filmLanguage;
	}

	@Override
	public List<Film> findFilmsByKeyword(String keyword) {
		List<Film> films = new ArrayList<>();
		try {
			Connection conn = DriverManager.getConnection(URL, user, pass);
			String sqltxt = "SELECT * FROM film " + "WHERE title LIKE ? OR description LIKE ?;";
			java.sql.PreparedStatement stmt = conn.prepareStatement(sqltxt);
			stmt.setString(1, "%" + keyword + "%");
			stmt.setString(2, "%" + keyword + "%");
			ResultSet filmResult = stmt.executeQuery();
			if (filmResult.next()) {
				int filmId = filmResult.getInt("id");
				String title = filmResult.getString("title");
				String releaseYear = filmResult.getString("release_year");
				String rating = filmResult.getString("rating");
				String description = filmResult.getString("description");
				int languageId = filmResult.getInt("language_id");
				String filmLanguage = getLanguageForFilm(filmId);
				List<Actor> actor = findActorsByFilmId(filmId);
				Film film = new Film(filmId, title, releaseYear, rating, description, filmLanguage, languageId, actor);
				films.add(film);
			} else {
				System.out.println("That keyword does not show up in any films, please enter another keyword");
			}
			filmResult.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return films;
	}

	public Actor findActorById(int actorId) {
		Actor actor = null;
		String sqltxt = "SELECT * FROM actor WHERE id = ?;";
		try {
			Connection conn = DriverManager.getConnection(URL, user, pass);
			java.sql.PreparedStatement stmt = conn.prepareStatement(sqltxt);
			stmt.setInt(1, actorId);
			ResultSet actorResult = stmt.executeQuery();
			if (actorResult.next()) {
				actor = new Actor();
				actor.setActorId(actorResult.getInt("id"));
				actor.setFirstName(actorResult.getString("first_name"));
				actor.setLastName(actorResult.getString("last_name"));
			} else {
				System.out.println("Actor ID has no information, Enter select a Actor ID");
			}
			actorResult.close();
			stmt.close();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return actor;

	}

	@Override
	public List<Actor> findActorsByFilmId(int filmId) {
		List<Actor> actors = new ArrayList<>();
		try {
			Connection conn = DriverManager.getConnection(URL, user, pass);
			String sqltxt = "SELECT DISTINCT actor.id, actor.first_name, actor.last_name"
					+ " FROM actor JOIN film_actor ON actor.id = film_actor.actor_id"
					+ " JOIN film ON film_actor.film_id = film_id" + " WHERE film_id = ?;";
			java.sql.PreparedStatement stmt = conn.prepareStatement(sqltxt);
			stmt.setInt(1, filmId);
			ResultSet res = stmt.executeQuery();
			while (res.next()) {
				int actorId = res.getInt("id");
				String firstName = res.getString("first_name");
				String lastName = res.getString("last_name");
				Actor actor = new Actor(actorId, firstName, lastName);
				actors.add(actor);
			}
			res.close();
			stmt.close();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return actors;
	}

}
