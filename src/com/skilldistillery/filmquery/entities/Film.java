package com.skilldistillery.filmquery.entities;

import java.util.List;
import java.util.Objects;

public class Film {
	private int filmId;
	private String title;
	private String description;
	private String rating;
	private String releaseYear;
	private int languageId;
	private String filmLanguage;

	private List<Actor> actors;

	public Film() {
	}

	public Film(int filmId, String title, String releaseYear, String rating, String description, String filmLanguage,
			int languageId, List<Actor> actors) {
		super();
		this.filmId = filmId;
		this.title = title;
		this.releaseYear = releaseYear;
		this.rating = rating;
		this.description = description;
		this.languageId = languageId;
		this.filmLanguage = filmLanguage;
		this.actors = actors;
	}

	public int getFilmId() {
		return filmId;
	}

	public void setFilmId(int filmId) {
		this.filmId = filmId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public String getReleaseYear() {
		return releaseYear;
	}

	public void setReleaseYear(String releaseYear) {
		this.releaseYear = releaseYear;
	}

	public int getLanguageId() {
		return languageId;
	}

	public void setLanguageId(int languageId) {
		this.languageId = languageId;
	}

	public String getFilmLanguage() {
		return filmLanguage;
	}

	public void setFilmLanguage(String filmLanguage) {
		this.filmLanguage = filmLanguage;
	}

	public List<Actor> getActors() {
		return actors;
	}

	public void setActors(List<Actor> actors) {
		this.actors = actors;
	}

	@Override
	public String toString() {
		return "Film FilmID: " + filmId + ", Title: " + title + ", Release Year: " + releaseYear + ", Rating: " + rating
				+ ", Description: " + description + ", LanguageID: " + languageId + ", FilmLanguage: " + filmLanguage
				+ ", Actors: " + actors;
	}

	@Override
	public int hashCode() {
		return Objects.hash(actors, description, filmId, filmLanguage, languageId, rating, releaseYear, title);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Film other = (Film) obj;
		return Objects.equals(actors, other.actors) && Objects.equals(description, other.description)
				&& filmId == other.filmId && Objects.equals(filmLanguage, other.filmLanguage)
				&& languageId == other.languageId && Objects.equals(rating, other.rating)
				&& Objects.equals(releaseYear, other.releaseYear) && Objects.equals(title, other.title);
	}

}
