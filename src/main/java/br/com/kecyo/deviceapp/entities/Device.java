package br.com.kecyo.deviceapp.entities;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Document
@Getter
public class Device implements Serializable{

    @Id
    private String id;

    private SystemOS systemOS;

    private String model;

    private Set<String> appsInstalled;

    private List<Visit>  visits;

}
