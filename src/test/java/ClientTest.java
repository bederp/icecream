/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import static org.junit.Assert.assertNotNull;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;

/**
 *
 * @author stolawoj
 */
//@Ignore
public class ClientTest {

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(8089);

    @Test
    public void exampleTest() {
//        stubFor(get(urlEqualTo("/maze-game/HealthCheck"))
//                .withHeader("Accept", equalTo("text/json"))
//                .willReturn(aResponse()
//                        .withStatus(200)
//                        .withHeader("Content-Type", "text/xml")
//                        .withBody("currentTime: '2016-08-20'")));


        Client client = new Client();
        final String healthCheck = client.healthCheck();
        assertNotNull(healthCheck);

//        verify(postRequestedFor(urlMatching("/my/resource/[a-z0-9]+"))
//                .withRequestBody(matching(".*<message>1234</message>.*"))
//                .withHeader("Content-Type", notMatching("application/json")));
    }

}
