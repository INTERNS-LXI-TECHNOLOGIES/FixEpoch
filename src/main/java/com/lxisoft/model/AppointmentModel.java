package com.lxisoft.model;

public class AppointmentModel {

    String employeeName;
    String timeSlot;
    String date;
    String providedService;

    public String getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(String timeSlot) {
        this.timeSlot = timeSlot;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getProvidedService() {
        return providedService;
    }

    public void setProvidedService(String providedService) {
        providedService = providedService;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }
}
