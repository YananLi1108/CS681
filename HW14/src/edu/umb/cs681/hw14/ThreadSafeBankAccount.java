package edu.umb.cs681.hw14;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadSafeBankAccount {
	private double balance = 0;
	private ReentrantLock lock = new ReentrantLock();
	private Condition sufficientFundsCondition = lock.newCondition();
	private Condition belowUpperLimitFundsCondition = lock.newCondition();

	public ThreadSafeBankAccount() {
	}
	
	public void deposit(double amount) {
		lock.lock();
		try {
			System.out.println("Deposit Lock obtained");
			System.out.println(Thread.currentThread().getId() + " Current balance: " + balance);
			while (balance >= 300) {

				System.out.println(Thread.currentThread().getId() + 
						" Deposit - await(): Balance exceeds the upper limit.");
				belowUpperLimitFundsCondition.await();

			}
			balance += amount;
			System.out.println(Thread.currentThread().getId() + 
					" Deposit: new balance: " + balance);
			sufficientFundsCondition.signalAll();
		} catch (InterruptedException exception) {
			System.out.println(exception);
		} finally {
			lock.unlock();
			System.out.println("Deposit Lock released");
		}
	}
	
	public void withdraw(double amount) {
		lock.lock();
		try {
			System.out.println("Withdraw Locked obtained");
			System.out.println(Thread.currentThread().getId() + " current balance: " + balance);

			while (balance <= 0) {
				System.out.println(Thread.currentThread().getId() + 
						" Withdraw - await(): Insufficient funds");
				sufficientFundsCondition.await();
			}
			balance -= amount;
			System.out.println(Thread.currentThread().getId() + 
					" Withdraw: New balance: " + balance);
			belowUpperLimitFundsCondition.signalAll();
		} catch (InterruptedException exception) {
			System.out.println(exception);
		} finally {
			lock.unlock();
			System.out.println("Withdraw Lock Released");
		}
	}
}
