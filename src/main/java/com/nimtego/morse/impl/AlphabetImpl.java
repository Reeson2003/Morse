package com.nimtego.morse.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by Pavel Gavrilov
 */
public class AlphabetImpl implements Alphabet {
    private Properties alphabet = new Properties();

    public AlphabetImpl(String properties) {
        try {
            InputStream stream = getClass().getClassLoader().getResourceAsStream(properties);
            alphabet.load(stream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String toMorse(Character c) {
        return alphabet.getProperty(String.valueOf(c).toUpperCase());
    }

    @Override
    public Character fromMorse(String s) {
        return alphabet.entrySet().stream()
                .filter(entry -> entry.getValue().equals(s.trim()))
                .map(e -> (String)e.getKey())
                .map(st -> st.charAt(0))
                .findFirst()
                .orElse(null);
    }
}
