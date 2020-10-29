package com.micropos.boletaelectronica.modelos;

public class Post {

    private String username;
    private String grant_type;
    private String client_secret;
    private String client_id;
    private String password;

    public String getUsername() {
        return username;
    }

    public String getGrant_type() {
        return grant_type;
    }

    public String getClient_secret() {
        return client_secret;
    }

    public String getClient_id() {
        return client_id;
    }

    public String getPassword() {
        return password;
    }

    public Post(String username, String grant_type, String cliente_secret, String client_id, String password) {
        this.username = username;
        this.grant_type = grant_type;
        this.client_secret = cliente_secret;
        this.client_id = client_id;
        this.password = password;
    }
}
