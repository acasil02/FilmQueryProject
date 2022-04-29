package com.skilldistillery.filmquery.entities;

import java.util.Objects;

public class Film {
	private int filmId;

	public Film() {
	}

	public Film(int filmId) {
		super();
		this.filmId = filmId;
	}

	public int getFilmId() {
		return filmId;
	}

	public void setFilmId(int filmId) {
		this.filmId = filmId;
	}

	@Override
	public String toString() {
		return "Film [filmId=" + filmId + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(filmId);
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
		return filmId == other.filmId;
	}

}
