package com.ge.fsmp.connector.ras.impl;

import com.ge.fsmp.connector.ras.IOutagesEssentialsReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

/**
 * @author 212464424 on 4/12/16.
 */
@Component
public class OutagesEssentialsReader implements IOutagesEssentialsReader {

    private static final Logger LOG = LoggerFactory.getLogger(OutagesEssentialsReader.class);

    private static final String OUTAGES_ESSENTIALS_JSON = "essentials/outages-essentials.json";

    @Override
    public Map<String, Object> getOutagesEssentials() {
        try {
            String json = new String(Files.readAllBytes(
                    Paths.get(getClass().getClassLoader().getResource(OUTAGES_ESSENTIALS_JSON).toURI().getPath())));
            return JsonParserFactory.getJsonParser().parseMap(json);
        } catch (IOException | URISyntaxException e) {
            LOG.error("Error opening essentials json", e);
            return null;
        }
    }
}
