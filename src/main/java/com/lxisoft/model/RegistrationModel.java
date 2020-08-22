package com.lxisoft.model;

import com.lxisoft.domain.*;
import com.lxisoft.service.mapper.TimeSlotMapperImpl;

public class RegistrationModel {

    private String firmId;
    private String firmName;
    private String description;
    private byte[] image;
    private String imageContentType;
    private Address address;
    private ProvidedService providedService;
    private TimeSlot timeSlot;
    private Employee employee;
    private Customer customer;
    private City city;
    private State state;
    private PostelCode pin;

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public PostelCode getPin() {
        return pin;
    }

    public void setPin(PostelCode pin) {
        this.pin = pin;
    }

    public String getFirmId() {
        return firmId;
    }

    public void setFirmId(String firmId) {
        this.firmId = firmId;
    }

    public String getFirmName() {
        return firmName;
    }

    public void setFirmName(String firmName) {
        this.firmName = firmName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getImageContentType() {
        return imageContentType;
    }

    public void setImageContentType(String imageContentType) {
        this.imageContentType = imageContentType;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public ProvidedService getProvidedService() {
        return providedService;
    }

    public void setProvidedService(ProvidedService providedService) {
        this.providedService = providedService;
    }

    public TimeSlot getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(TimeSlot timeSlot) {
        this.timeSlot = timeSlot;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
