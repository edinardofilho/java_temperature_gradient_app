package model.entities;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Gradient {

	private double realTimeValue;
	private double finalValue;
	private double rate;
	
	private int seconds;
	
	private Date date;
	private Date now;
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
	
	public Gradient(double initialValue, double finalValue, int hours, int minutes, int seconds) {
		this.realTimeValue = initialValue;
		this.finalValue = finalValue;
		this.seconds = (hours * 60 * 60) + (minutes * 60) + seconds;
	}

	public Gradient(double initialValue, double finalValue, double rate) {
		this.realTimeValue = initialValue;
		this.finalValue = finalValue;
		this.rate = rate;
	}
	
	public void countingGradient() {
		System.out.println("Iniciando contagem");
		System.out.println("Valor inicial: " + realTimeValue + " Valor final: " + finalValue + " Diferença: " + (finalValue - realTimeValue));
		System.out.println("Tempo: " + seconds + "s");
	}
}
