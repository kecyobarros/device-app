package br.com.kecyo.deviceapp.usescases.exception;

public class DeviceNotFoundException extends RuntimeException {

    public DeviceNotFoundException(){
        super("Device Not Found!");
    }

}
