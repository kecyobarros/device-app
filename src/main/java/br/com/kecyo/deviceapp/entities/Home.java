package br.com.kecyo.deviceapp.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@ToString
@AllArgsConstructor
public class Home implements Serializable{

    private double longitude;

    private double latitude;
}
