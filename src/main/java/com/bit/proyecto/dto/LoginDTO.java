package com.bit.proyecto.dto;

public class LoginDTO {
    private Integer usuarioLogin;
    private String tokenSession;
    private String emailSession;
    public LoginDTO(){}

    public LoginDTO(Integer usuarioLogin, String tokenSession, String emailSession) {
        this.usuarioLogin = usuarioLogin;
        this.tokenSession = tokenSession;
        this.emailSession = emailSession;
    }

    public Integer getUsuarioLogin() {
        return usuarioLogin;
    }

    public void setUsuarioLogin(Integer usuarioLogin) {
        this.usuarioLogin = usuarioLogin;
    }

    public String getTokenSession() {
        return tokenSession;
    }

    public void setTokenSession(String tokenSession) {
        this.tokenSession = tokenSession;
    }

    public String getEmailSession() {
        return emailSession;
    }

    public void setEmailSession(String emailSession) {
        this.emailSession = emailSession;
    }
}
