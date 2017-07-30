package br.com.kecyo.deviceapp.http.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@ToString
@AllArgsConstructor
public class HomeDataContract implements Serializable{

    private double longitude;

    private double latitude;
}
