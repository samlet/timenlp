package com.samlet.bridge;

import com.time.nlp.TimeNormalizer;

import javax.inject.Singleton;
import java.net.URISyntaxException;
import java.net.URL;

@Singleton
public class TimeAnalyst {
    TimeNormalizer normalizer;

    public TimeAnalyst() throws URISyntaxException {
        URL url = TimeNormalizer.class.getResource("/TimeExp.m");
        normalizer = new TimeNormalizer(url.toURI().toString());
        normalizer.setPreferFuture(true);
    }

    public TimeNormalizer getNormalizer() {
        return normalizer;
    }
}
