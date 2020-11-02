package edu.umb.cs681.hw1;

 public class Test {

    public static void main(String[] args) {
        StockQuoteObservable s = new StockQuoteObservable();

        s.addObserver((Observable o, Object obj) -> {
            String ticker = ((StockEvent) obj).getTicker();
            Float quote = ((StockEvent) obj).getQuote();
            System.out.println(" Stock event:");
            System.out.println("Observer 1 - " + ticker + " " + quote);
        });

        s.addObserver((Observable o, Object obj) -> {
            String ticker = ((StockEvent) obj).getTicker();
            Float quote = ((StockEvent) obj).getQuote();
            System.out.println("Observer 2 -: " + ticker + " " + quote);
        });

        System.out.println("Number of observers: " + s.countObservers());

        String MSFTCode = "MSFT";
        Float MSFTValue = 25.0f;

        System.out.println("Add new Stock: " + MSFTCode);
        s.setQuote(MSFTCode, MSFTValue);
        s.notifyObservers(new StockEvent(MSFTCode, MSFTValue));

        MSFTValue = 36.0f;
        System.out.println("MSFT changed");
        s.changeQuote(MSFTCode, MSFTValue);
        s.notifyObservers(new StockEvent(MSFTCode, MSFTValue));
    }
}
