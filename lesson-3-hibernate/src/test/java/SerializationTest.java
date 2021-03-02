import entity.Agent;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.stream.Collectors;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SerializationTest {
    @Test
    void test_agent_serialization(){
        try {
            String json = getResponseBody(new URL("http://localhost:8080/agent?agent_code=A003"));

            Agent agent = new Agent(json);
            Assertions.assertEquals("Alex", agent.getAgentName());

            json = getResponseBodyPureJava(new URL("http://localhost:8080/agent?agent_code=A003"));

            agent = new Agent(json);
            Assertions.assertEquals("Alex", agent.getAgentName());
        } catch (IOException e) {
            Assertions.fail("Test failed", e);
        }
    }

    private static String getResponseBody(URL url) throws IOException {
        URLConnection con = url.openConnection();
        InputStream in = con.getInputStream();
        String encoding = con.getContentEncoding();
        return IOUtils.toString(in, encoding == null ? "UTF-8" : encoding);
    }

    private static String getResponseBodyPureJava(URL url) throws IOException {
        URLConnection connection = url.openConnection();
        connection.setRequestProperty("accept", "application/json");
        InputStream responseStream = connection.getInputStream();
        return new BufferedReader(new InputStreamReader(responseStream)).lines().collect(Collectors.joining("\n"));
    }
}
