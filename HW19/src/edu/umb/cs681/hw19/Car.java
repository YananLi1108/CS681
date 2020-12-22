package edu.umb.cs681.hw19;
import java.util.ArrayList;
import java.util.List;

public class Car {

	private int colorCode;

	private int dominationCount;
	private int mileage, year;
	private String model, make;
	private float price;

	public Car(int colorCode, String make, String model, int mileage, float price, int year) {
		this.colorCode = colorCode;
		this.make = make;
		this.model = model;
		this.mileage = mileage;
		this.price = price;
		this.year = year;
	}

	public int getColorCode() {
		return this.colorCode;
    }
    
    public int getDominationCount() {
        return this.dominationCount;
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

    
	public int getPrice() {
        return (int) price;
	}
    
    public int getYear() {
        return year;
    }
    

	public void setDominationCount(List<Car> cars) {
		for (Car car : cars) {
			if ((car.getPrice() >= this.getPrice()) && (car.getMileage() >= this.getMileage())
					&& (car.getYear() <= this.getYear())) {
				this.dominationCount++;
			}
		}
		this.dominationCount--;
	}

	public static void main(String[] args) {

		List<Car> carList = new ArrayList<>();

		carList.add(new Car(000, "Tesla", "ModelX", 90, 100000, 2019));
        carList.add(new Car(001, "BMW", "X6", 100, 150000, 2018));
        carList.add(new Car(002, "McLaren", "720s", 60, 1450000, 2020));
        carList.add(new Car(003, "RandRove", "sport", 25, 270000, 2019));

		carList.forEach((Car car) -> car.setDominationCount(carList));

        Integer colorcode = carList.stream()
                                   .parallel()
                                   .map((Car car) -> car.getColorCode()).reduce(0,
                                        (result, carColorCode) -> result += carColorCode,
                                        (finalResult, intermediateResult) -> finalResult + intermediateResult
                                    );

		System.out.println("The Sum Of Cars is " + colorcode);

        Integer Mileage = carList.stream()
                              .parallel()
                              .map((Car car) -> car.getMileage()).reduce(0,
                                    (result, carMileage) -> {
                                        if (result == 0)
                                            return carMileage;
                                        else if (carMileage < result)
                                            return carMileage;
                                        else
                                            return result;
                                });

		System.out.println("The Minimum Car Mileage is " + Mileage);

        Integer Price = carList.stream()
                            .parallel()
                            .map((Car car) -> car.getPrice()).reduce(0, (result, carPrice) -> {
                                if (result == 0)
                                    return carPrice;
                                else if (carPrice > result)
                                    return carPrice;
                                else
                                    return result;
                            });

		System.out.println("The Maximum Car Price is " + Price);

        Integer Make = carList.stream()
                           .parallel()
                           .map((Car car) -> car.getMake()).reduce(0, 
                                    (result, carMake) -> ++result,
                                    (finalResult, intermediateResult) -> finalResult
                                );

		System.out.println("The Count of Cars is  " + Make);

	}
}