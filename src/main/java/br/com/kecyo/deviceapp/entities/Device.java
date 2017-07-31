package br.com.kecyo.deviceapp.entities;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Getter
@Builder
@ToString
@Document(collection = "devices")
public class Device implements Serializable {

    @Id
    private String id;

    private String userId;

    private SystemOS systemOS;

    private Home home;

    private Work work;

    private String model;

    private Set<String> appsInstalled;

    private List<Visit>  visits;

    @Version
    private int version;

}
