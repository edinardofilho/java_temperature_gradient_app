package model.services;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import gui.util.Alerts;

public class Gradient {

	private double realTimeValue;
	private double finalValue;
	private double rate;

	private int alarmHours;
	private int alarmMinutes;
	private int alarmSeconds;

	private static SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

	public Gradient(double initialValue, double finalValue, int totalTimeHours, int totalTimeMinutes,
			int totalTimeSeconds, int alarmHours, int alarmMinutes, int alarmSeconds) {
		this.realTimeValue = initialValue;
		this.finalValue = finalValue;
		this.alarmHours = alarmHours;
		this.alarmMinutes = alarmMinutes;
		this.alarmSeconds = alarmSeconds;
		this.rate = (this.realTimeValue - this.finalValue)
				/ ((totalTimeHours * 60 * 60) + (totalTimeMinutes * 60) + totalTimeSeconds);
	}

	public Gradient(double initialValue, double finalValue, double rate, int alarmHours, int alarmMinutes,
			int alarmSeconds) {
		this.realTimeValue = initialValue;
		this.finalValue = finalValue;
		this.rate = rate / (60 * 60);
		this.alarmHours = alarmHours;
		this.alarmMinutes = alarmMinutes;
		this.alarmSeconds = alarmSeconds;
	}

	public void countingGradient(String info, String unit) {
		Calendar now = Calendar.getInstance();

		if (realTimeValue > finalValue) {
			while (realTimeValue > finalValue) {
				Calendar alarm = Calendar.getInstance();
				alarm.add(Calendar.HOUR_OF_DAY, alarmHours);
				alarm.add(Calendar.MINUTE, alarmMinutes);
				alarm.add(Calendar.SECOND, alarmSeconds);
				while (now.getTimeInMillis() < alarm.getTimeInMillis()) {
					now = Calendar.getInstance();
				}
				realTimeValue -= rate * ((alarmHours * 60 * 60) + (alarmMinutes * 60) + alarmSeconds);
				Alerts.showGradient(info, (String.format("%.2f", realTimeValue) + unit), sdf.format(now.getTime()));
			}
			Alerts.showGradient(info, (String.format("%.2f", realTimeValue) + unit), sdf.format(now.getTime()));
		} else {
			while (realTimeValue < finalValue) {
				Calendar alarm = Calendar.getInstance();
				alarm.add(Calendar.HOUR_OF_DAY, alarmHours);
				alarm.add(Calendar.MINUTE, alarmMinutes);
				alarm.add(Calendar.SECOND, alarmSeconds);
				while (now.getTimeInMillis() < alarm.getTimeInMillis()) {
					now = Calendar.getInstance();
				}
				realTimeValue -= rate * ((alarmHours * 60 * 60) + (alarmMinutes * 60) + alarmSeconds);
				Alerts.showGradient(info, (String.format("%.2f", realTimeValue) + unit), sdf.format(now.getTime()));
			}
			Alerts.showGradient(info, (String.format("%.2f", realTimeValue) + unit), sdf.format(now.getTime()));
		}
	}

	public void countingRate(String info, String unit) {
		Calendar now = Calendar.getInstance();

		if (realTimeValue > finalValue) {
			while (realTimeValue > finalValue) {
				Calendar alarm = Calendar.getInstance();
				alarm.add(Calendar.HOUR_OF_DAY, alarmHours);
				alarm.add(Calendar.MINUTE, alarmMinutes);
				alarm.add(Calendar.SECOND, alarmSeconds);
				while (now.getTimeInMillis() < alarm.getTimeInMillis()) {
					now = Calendar.getInstance();
				}
				realTimeValue -= rate * ((alarmHours * 60 * 60) + (alarmMinutes * 60) + alarmSeconds);
				Alerts.showGradient(info, (String.format("%.2f", realTimeValue) + unit), sdf.format(now.getTime()));
			}
			Alerts.showGradient(info, (String.format("%.2f", realTimeValue) + unit), sdf.format(now.getTime()));
		} else {
			while (realTimeValue < finalValue) {
				Calendar alarm = Calendar.getInstance();
				alarm.add(Calendar.HOUR_OF_DAY, alarmHours);
				alarm.add(Calendar.MINUTE, alarmMinutes);
				alarm.add(Calendar.SECOND, alarmSeconds);
				while (now.getTimeInMillis() < alarm.getTimeInMillis()) {
					now = Calendar.getInstance();
				}
				realTimeValue += rate * ((alarmHours * 60 * 60) + (alarmMinutes * 60) + alarmSeconds);
				Alerts.showGradient(info, (String.format("%.2f", realTimeValue) + unit), sdf.format(now.getTime()));
			}
			Alerts.showGradient(info, (String.format("%.2f", realTimeValue) + unit), sdf.format(now.getTime()));
		}
	}
}
