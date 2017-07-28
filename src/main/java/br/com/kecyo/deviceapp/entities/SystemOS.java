package br.com.kecyo.deviceapp.entities;


import lombok.Getter;

import java.io.Serializable;

@Getter
public class SystemOS implements Serializable {

    private String name;

    private String version;

}