package lesson7.rest;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import lesson7.view.LoginView;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.RequestBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * Базовый класс для получения информации из API
 */
public abstract class BaseApi {
    private static final Logger LOG = LoggerFactory.getLogger(LoginView.class);

    private static final String HOST = "localhost";
    private static final int PORT = 8080;
    private static final String SERVER_ADDRESS = String.format("http://%s:%d", HOST, PORT);

    protected final static ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    protected abstract String getApiPath();

    protected abstract HttpClient getClient();

    protected <T> T getResponse(String method, String additionalUri, JavaType valueType, T defaultValue){
        try {
            return OBJECT_MAPPER.readValue(getContentFromUri(method, additionalUri), valueType);
        } catch (IOException e) {
            LOG.error("Failed to parse response to " + valueType, e);
        }
        return defaultValue;
    }

    private String getFullUri(String additionalUri){
        return String.format("%s/%s/%s/", SERVER_ADDRESS, getApiPath(), additionalUri);
    }

    private InputStream getContentFromUri(String method, String additionalUri) throws IOException {
        RequestBuilder requestBuilder = RequestBuilder.create(method).setUri(getFullUri(additionalUri));
        String base64Auth = Base64.getEncoder().encodeToString("styopa:hash".getBytes(StandardCharsets.UTF_8));
        requestBuilder.addHeader("Authorization", "Basic " + base64Auth);
        HttpResponse response = getClient().execute(requestBuilder.build());
        HttpEntity entity = response.getEntity();
        return entity.getContent();
    }
}
