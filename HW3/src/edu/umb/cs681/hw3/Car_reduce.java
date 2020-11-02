package edu.umb.cs681.hw3;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.*;






public class Car_reduce {
	
	
	
	private String model, make;
	private int mileage, year;
	private int price;
	private int dominationCount;

	public Car_reduce(String make, String model, int mileage, int year, int price) {
		this.make = make;
		this.model = model;
		this.mileage = mileage;
		this.year = year;
		this.price = price;
	}

	public String getModel() {
		return model;
	}

	public String getMake() {
		return make;
	}

	public int getMileage() {
		return mileage;
	}

	public int getYear() {
		return year;
	}

	public int getPrice() {
		return price;
	}

	public int getDominationCount() {
		return this.dominationCount;
	}

	public void setDominationCount(ArrayList<Car_reduce> cars) {
		
		for (Car_reduce car : cars) {
			if ((car.getPrice() >= this.getPrice()) && (car.getMileage() >= this.getMileage())
					&& (car.getYear() <= this.getYear())) {
				this.dominationCount++;
			}
		}
		this.dominationCount--; 	
	}
	
          @Override
        public String toString() {
        	
        	return this.make +" "+ this.model+" "+ this.mileage+" "+this.year+" "+this.price;
        }

	public static void main(String[] args) {
		
		List<Car_reduce> list=new ArrayList<Car_reduce>();
		list.add(new Car_reduce("Tesla", "S", 15, 2018, 100000));
		list.add(new Car_reduce("BMW", "X6", 10, 2002, 160000));
		list.add(new Car_reduce("Benz", "S400", 18, 2020, 240000));
		list.add(new Car_reduce("McLaren", "720s", 12, 2019, 1380000));
		list.add(new Car_reduce("McLaren", "720s", 22, 2019, 1380000));
		
		
		//max() with reduce.
		int max_cost = list.stream().map((Car_reduce car) ->car.getPrice() )
				.reduce(0, (result,price)->result > price ? result : price);
		
		System.out.println("Price of most expensive car is $"+max_cost);
		
		
		//min() with reduce
		
		int min_cost = list.stream().map((Car_reduce car) ->car.getPrice() )
				.reduce(1000000000, (result, price)->price>result ? result : price);
		
		System.out.println("Price of cheapest car $"+min_cost);
		
		//count() with reduce
		int y=0;
		int count = list.stream().map(x->y+1).reduce(0,(a,b)->a+b);
		System.out.println("Total number of cars in list:"+count);
		

	}

}
