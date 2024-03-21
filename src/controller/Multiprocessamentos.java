package controller;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class Multiprocessamentos extends Thread {
	private Semaphore TED;
	private int iD;
	private int contador;
	private Random random;

	public Multiprocessamentos(int x,Semaphore semaphore) {
		this.iD = x;
		this.TED = semaphore;
		this.random = new Random();
		this.contador = 0;
	}
	public void run() {
		if (iD % 3 == 0) {
			processoA();
		}
		if (iD % 3 == 1) {
			processoB();
		}
		if (iD % 3 == 2) {
			processoC();
		}
	}

	private void transassaoA() {
		try {
			TED.acquire();
			System.out.println("Realizando transassão: " + iD + "...");
			sleep(1000);
			contador++;
			if (contador < 2) {
				System.out.println("Transassão "+iD+" terminada");
				TED.release();
				processoA();
			}else {
				System.err.println("Processamento "+iD+" terminado");
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			TED.release();
		}

	}

	private void processoA() {
		try {
			long x = random.nextLong(200, 1000);
			System.out.println("Calculando o processo: " + iD);
			sleep(x);
			transassaoA();
		} catch (InterruptedException e) {
			e.getStackTrace();
		}
	}

	private void processoB() {
		try {
			long x = random.nextLong(500, 1500);
			System.out.println("Calculando o processo: " + iD);
			sleep((long) x);
			transassaoB();
		} catch (InterruptedException e) {
			e.getStackTrace();
		}

	}

	private void transassaoB() {
		try {
			TED.acquire();
			System.out.println("Realizando transassão: " + iD + "...");
			sleep(1500);
			contador++;
			if (contador < 3) {
				System.out.println("Transassão "+ iD+" terminada");
				TED.release();
				processoA();
			}else {
				System.err.println("Processamento "+iD+" terminado");
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			TED.release();
		}

	}

	private void processoC() {
		try {
			long x = random.nextLong(1000, 2000);
			System.out.println("Calculando o processo: " + iD);
			sleep((long) x);
			transassaoC();
		} catch (InterruptedException e) {
			e.getStackTrace();
		}
	}
	private void transassaoC() {
		try {
			TED.acquire();
			System.out.println("Realizando transassão: " + iD + "...");
			sleep(1500);
			contador++;
			if (contador < 3) {
				System.out.println("Transassão "+iD+" terminado");
				TED.release();
				processoA();
			}else {
				System.err.println("Processamento "+iD+" terminado");
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			TED.release();
		}
	}
}
