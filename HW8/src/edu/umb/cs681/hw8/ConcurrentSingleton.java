package edu.umb.cs681.hw8;

import java.util.concurrent.locks.ReentrantLock;

public class ConcurrentSingleton {
	
	private ConcurrentSingleton() {};

	private static ConcurrentSingleton instance = null;
	private static ReentrantLock lock = new ReentrantLock();

	// Factory method to create or return the singleton instance
	public static ConcurrentSingleton getInstance() {
		lock.lock();
		try {
			if (instance == null)
				instance = new ConcurrentSingleton();
			return instance;
		} finally {
			lock.unlock();
		}
	}
}