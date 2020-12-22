package edu.umb.cs681.hw14;

import java.util.concurrent.locks.ReentrantLock;

public class WithdrawRunnable implements Runnable {

	private ThreadSafeBankAccount bankAccount = null;
	private boolean done = false;
	private ReentrantLock lock = new ReentrantLock();
	
	public WithdrawRunnable(ThreadSafeBankAccount bankAccount) {
		this.bankAccount = bankAccount;
	}
	
	public void setDone() {
		lock.lock();
		try {
			done = true;
		} finally {
			lock.unlock();
		}
	}
	@Override
	public void run() {
		while (true) {
			lock.lock();
			try{
				if (done) {
					System.out.println(Thread.currentThread().getId() + " Set as done");
					break;
				}
			} finally {
				lock.unlock();
			}
			bankAccount.withdraw(200);
			try{
				Thread.sleep(1000);
			} catch (InterruptedException exception) {
				System.out.println(exception);
				continue;
			}
		}
	}
}
