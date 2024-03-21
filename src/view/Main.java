package view;

import java.util.concurrent.Semaphore;
import controller.Multiprocessamentos;

public class Main {
	
	public static void main(String[] args) {
		Semaphore semaphore = new Semaphore(1);
	
		for (int x = 1; x<=21;x++) {
			Thread thread = new Multiprocessamentos(x, semaphore);
			thread.start();
		}
	}
}
