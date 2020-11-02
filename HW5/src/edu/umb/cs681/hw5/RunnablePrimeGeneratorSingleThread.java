package edu.umb.cs681.hw5;

public class RunnablePrimeGeneratorSingleThread extends PrimeGenerator implements Runnable {
	public RunnablePrimeGeneratorSingleThread(long from, long to) {
		super(from, to);
	}
	
	public void run() {
		generatePrimes();
	}
	
	public static void main(String[] args) {
		
		PrimeGenerator gen = new PrimeGenerator(1L, 2000000L);
		gen.generatePrimes();
		gen.getPrimes().forEach( (Long prime)-> System.out.print(prime + ", ") );
		System.out.println("\n" + gen.getPrimes().size() + " prime numbers are found with single thread");
	}

}
