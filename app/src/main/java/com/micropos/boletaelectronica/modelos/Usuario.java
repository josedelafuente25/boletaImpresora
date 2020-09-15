package com.micropos.boletaelectronica.modelos;

public class Usuario {

    private String name;
    private String email;
    private Long clientId;
    private String clientSecret;
    private String deviceId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public Usuario(){}

    public Usuario(String name, String email, Long clientId, String clientSecret, String deviceId) {
        this.name = name;
        this.email = email;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.deviceId = deviceId;
    }
}
