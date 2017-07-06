package cn.stt.testmain;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.glassfish.jersey.client.JerseyClient;
import org.glassfish.jersey.client.JerseyClientBuilder;
import org.glassfish.jersey.client.JerseyWebTarget;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.core.Response;
import java.net.URI;

/**
 * Created by stt on 2017/7/5.
 */
public class TestMain {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestMain.class);

    public static void main(String[] args) {
        ClientBuilder clientBuilder = JerseyClientBuilder.newBuilder().register(MultiPartFeature.class);
        JerseyClient jerseyClient = (JerseyClient)clientBuilder.build().register(JacksonJsonProvider.class);
        JerseyWebTarget jerseyWebTarget = jerseyClient.target("http://www.99lib.net/book/518/16810.htm");
        JsonNodeFactory factory = new JsonNodeFactory(false);
        ObjectNode objectNode = factory.objectNode();
        Invocation.Builder inBuilder = jerseyWebTarget.request();
        Response response = inBuilder.get(Response.class);
        objectNode = response.readEntity(ObjectNode.class);
        objectNode.put("statusCode", response.getStatus());
        LOGGER.info(objectNode.toString());
    }

    @Test
    public void test1() throws Exception {
        HttpClient httpClient = new DefaultHttpClient();
        URI uri = new URI("http://www.99lib.net/book/518/16810.htm");
        HttpGet httpGet = new HttpGet(uri);
        HttpResponse httpResponse = httpClient.execute(httpGet);
        HttpEntity httpEntity = httpResponse.getEntity();
        String responseContent = EntityUtils.toString(httpEntity, "UTF-8");
        LOGGER.info(responseContent);
    }
}
