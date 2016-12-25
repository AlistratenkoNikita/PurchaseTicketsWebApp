/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.com.alistratenko.entities;

import java.io.Serializable;

/**
 *
 * @author Alistratenko Nikita
 */
public class Ticket implements Serializable{

    private String id;
    private String transport;
    private String used_time;

    public Ticket(String id, String transport, String used_time) {
        this.id = id;
        this.transport = transport;
        this.used_time = used_time;
    }

    public String getId() {
        return id;
    }

    public String getTransport() {
        return transport;
    }

    public String getUsed_time() {
        return used_time;
    }

    
    
    @Override
    public String toString() {
        return "Ticket{" + "id=" + id + ", transport=" + transport + ", used_time=" + used_time + '}';
    }

}
