package model.entities;

import java.text.SimpleDateFormat;
import java.util.Date;

import gui.util.Alerts;

public class Gradient {

	private double realTimeValue;
	private double finalValue;
	private double rate;

	private Long totalTimeSeconds;
	private Long alarmSeconds;
	private Long date;
	private Long now;

	private static SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");

	public Gradient(double initialValue, double finalValue, int totalTimeHours, int totalTimeMinutes,
			int totalTimeSeconds, int alarmHours, int alarmMinutes, int alarmSeconds) {
		this.realTimeValue = initialValue;
		this.finalValue = finalValue;
		this.totalTimeSeconds = (long) ((totalTimeHours * 60 * 60) + (totalTimeMinutes * 60) + totalTimeSeconds);
		this.alarmSeconds = (long) ((alarmHours * 60 * 60) + (alarmMinutes * 60) + alarmSeconds);
		this.rate = (this.realTimeValue - this.finalValue) / (this.totalTimeSeconds);
	}

	public Gradient(double initialValue, double finalValue, double rate, int alarmHours, int alarmMinutes,
			int alarmSeconds) {
		this.realTimeValue = initialValue;
		this.finalValue = finalValue;
		this.alarmSeconds = (long) ((alarmHours * 60 * 60) + (alarmMinutes * 60) + alarmSeconds);
		this.rate = rate * 60 * 60;
	}

	public void countingGradient(String info, String unit) {
		totalTimeSeconds += (System.currentTimeMillis() / 1000L);
		now = (System.currentTimeMillis() / 1000L);

		while (totalTimeSeconds > now) {
			date = (System.currentTimeMillis() / 1000L) + alarmSeconds;
			now = (System.currentTimeMillis() / 1000L);

			while (date > now) {
				Long sec = (System.currentTimeMillis() / 1000L) + 1;
				while (sec > now) {
					now = (System.currentTimeMillis() / 1000L);
				}
				realTimeValue -= rate;
				now = (System.currentTimeMillis() / 1000L);
			}
			Alerts.showGradient(info, (String.format("%.2f", realTimeValue) + unit), sdf.format(new Date(now * 1000L)));
		}
	}

	public void countingRate(String info) {
		now = (System.currentTimeMillis() / 1000L);

		if (realTimeValue > finalValue) {
			while (realTimeValue > finalValue) {
				date = (System.currentTimeMillis() / 1000L) + alarmSeconds;
				now = (System.currentTimeMillis() / 1000L);

				while (date > now) {
					Long sec = (System.currentTimeMillis() / 1000L) + 1;
					while (sec > now) {
						now = (System.currentTimeMillis() / 1000L);
					}
					realTimeValue -= rate;
					now = (System.currentTimeMillis() / 1000L);
				}
				Alerts.showGradient(info, String.format("%.2f", realTimeValue), sdf.format(new Date(now * 1000L)));
			}
		} else {
			while (realTimeValue < finalValue) {
				date = (System.currentTimeMillis() / 1000L) + alarmSeconds;
				now = (System.currentTimeMillis() / 1000L);

				while (date > now) {
					Long sec = (System.currentTimeMillis() / 1000L) + 1;
					while (sec > now) {
						now = (System.currentTimeMillis() / 1000L);
					}
					realTimeValue += rate;
					now = (System.currentTimeMillis() / 1000L);
				}
				Alerts.showGradient(info, String.format("%.2f", realTimeValue), sdf.format(new Date(now * 1000L)));
			}
		}
	}
}
