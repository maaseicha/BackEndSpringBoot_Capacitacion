package com.bit.proyecto.configuration;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class TokenLogin {
    List<String> tokenList;

    public TokenLogin() {
        tokenList = new ArrayList<>();
    }

    public String addToken(String auto) {
        String auxAuth = auto.substring(6,auto.length());
        String auth = auxAuth + ":" + System.currentTimeMillis();
        UUID uui = UUID.nameUUIDFromBytes(auth.getBytes());

        if (!tokenList.contains(uui.toString())) {
            tokenList.add(uui.toString());
        }
        return uui.toString();
    }
    public boolean exist(String token) {
        return tokenList.contains(token);
    }

    public void removeToken(String token) {
        if (tokenList == null) {
            return;
        }
        if (tokenList.contains(token)) {
            tokenList.remove(token);
        }
    }

    public List<String> getTokenList() {
        return tokenList;
    }
}
