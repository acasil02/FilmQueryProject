package com.skilldistillery.filmquery.app;

import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import com.skilldistillery.filmquery.database.DatabaseAccessor;
import com.skilldistillery.filmquery.database.DatabaseAccessorObject;
import com.skilldistillery.filmquery.entities.Film;

public class FilmQueryApp {

	DatabaseAccessor db = new DatabaseAccessorObject();

	public static void main(String[] args) {
		FilmQueryApp app = new FilmQueryApp();
//		app.test();
		app.launch();
	}

//	private void test() {
//		Film film = db.findFilmById(1);
//		System.out.println(film);
//	}

	private void launch() {
		Scanner input = new Scanner(System.in);

		startUserInterface(input);

		input.close();
	}

	private void startUserInterface(Scanner input) {
		boolean keepDisplaying = true;
		while (keepDisplaying) {

			try {
				displayMenu();
				int selection = input.nextInt();
				input.nextLine();
				switch (selection) {
				case 1:
					searchFilmId(input);
					break;
				case 2:
					searchFilmKeyword(input);
					break;
				case 3:
					System.out.println("Exiting, Thank you!");
					keepDisplaying = false;
					break;
				default:
					System.out.println("Please enter a valid option. Enter 1-3");
					break;
				}
			} catch (InputMismatchException e) {
				System.out.println("Please choose a valid option");
				input.nextLine();
			}
		}

	}

	private void displayMenu() {
		System.out.println("----------------------------------");
		System.out.println("|         Film Query             |");
		System.out.println("|    1: Search Film-ID           |");
		System.out.println("|    2: Search Film Keyword      |");
		System.out.println("|    3: Quit                     |");
		System.out.println("----------------------------------");
	}

	private void searchFilmId(Scanner input) {
		System.out.println("Please enter the Film-ID: ");
		int idInput = input.nextInt();
		Film film = db.findFilmById(idInput);
		printFilm(film);

	}

	private void searchFilmKeyword(Scanner input) {
		System.out.println("Please enter the keyword for the films information: ");
		String keywordInput = input.nextLine();
		List<Film> film = db.findFilmsByKeyword(keywordInput);
		for (int i = 0; i < film.size(); i++) {
			printFilm(film.get(i));
		}

	}

	private void printFilm(Film film) {
		System.out.println("Title: " + film.getTitle() + "\nRelease year: " + film.getReleaseYear() + "\nRating: "
				+ film.getRating() + "\nDescription: " + film.getDescription() + "\nLanguage: " + film.getFilmLanguage()
				+ "\nActors: " + film.getActors());
	}

}
