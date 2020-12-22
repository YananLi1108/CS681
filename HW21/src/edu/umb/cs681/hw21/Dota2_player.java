package edu.umb.cs681.hw21;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Dota2_player {
	private String name;
	private int year_of_games, age, num_lose, num_win;
	

	public Dota2_player(String name,int  year_of_games, int age,int  num_lose,int num_win){
		this.name = name;
		this.year_of_games = year_of_games;
		this.num_lose = num_lose;
		this.num_win = num_win;
		
	}

	public String getName() {
		return name;
	}

	public int getYearofGame() {
		return year_of_games;
	}

	public int getCenturies() {
		return age;
	}

	public int getNumoflost() {
		return num_lose;
	}

	public int getNumofWin() {
		return num_win;
	}


	
          @Override
        public String toString() {
        	
        	return this.name +" "+ this.year_of_games+" "+ this.age+" "+this.num_lose+" "+this.num_win;
        }

	public static void main(String[] args) {
		
		List<Dota2_player> list=new ArrayList<Dota2_player>();
		list.add(new Dota2_player("Miracle", 4, 22, 28, 93));
		list.add(new Dota2_player("Mushi", 9, 29, 60, 105));
		list.add(new Dota2_player("W33", 6, 26, 55, 90));
		list.add(new Dota2_player("Maybe", 8, 27, 38, 187));
		list.add(new Dota2_player("Ame", 6, 25, 23,101 ));
		long numberOfTickers = list.stream().count();
        System.out.println("Number of player in list is " + numberOfTickers);

		
        Dota2_player highestPerformance = list.stream()
                .parallel()
                .max(Comparator.comparing(Dota2_player::getYearofGame)).get();
System.out.println("Highest performing pair is " + highestPerformance);

Dota2_player lowestSpread = list.stream()
          .parallel()
          .min(Comparator.comparing(Dota2_player::getNumoflost)).get();
System.out.println("Lowest spread in the pairs is " + lowestSpread);

List<Dota2_player> sortedByPerformance = list.stream()
                        .parallel()
                       .sorted(Comparator.comparing(Dota2_player::getNumofWin))
                       .collect(Collectors.toList());
System.out.println("Sorted by performance");
sortedByPerformance.forEach((Dota2_player Dota2_player) -> System.out.println(Dota2_player.getNumoflost() + ": " + Dota2_player.getNumofWin()));
}
}
