/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author ademi
 */
public class Transaction implements Serializable  {
      private int id;
    private long timeStamp;
    private String sender;
    private String component;
    private String brand;
    private int year;
    private int quantity;
    private String recipient;
    private double amount;

    public Transaction(int id, String sender, String component, String brand, int year, int quantity, String recipient, double amount) {
        this.id = id;
        this.timeStamp = new Date().getTime();
        this.sender = sender;
        this.component = component;
        this.brand = brand;
        this.year = year;
        this.quantity = quantity;
        this.recipient = recipient;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", timeStamp=" + timeStamp +
                ", sender='" + sender + '\'' +
                ", component='" + component + '\'' +
                ", brand='" + brand + '\'' +
                ", year=" + year +
                ", quantity=" + quantity +
                ", recipient='" + recipient + '\'' +
                ", amount=" + amount +
                '}';
    }

    // Getters y setters
    public int getId() {
        return id;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public String getSender() {
        return sender;
    }

    public String getComponent() {
        return component;
    }

    public String getBrand() {
        return brand;
    }

    public int getYear() {
        return year;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getRecipient() {
        return recipient;
    }

    public double getAmount() {
        return amount;
    }
}
