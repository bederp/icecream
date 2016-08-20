package client;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import hello.Greeting;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import static org.junit.Assert.assertNotNull;
import org.junit.Rule;
import org.junit.Test;
import hello.HealthCheck;
import hello.StartCompetition;
import org.junit.Before;

/**
 *
 * @author stolawoj
 */
//@Ignore
public class ClientTest {

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(8089);
    private Client client;
    
    @Before
    public void setUp(){
        client = new Client("r1_1", false);
    }

    @Test
    public void shouldReturnValidTime() {
//        stubFor(get(urlEqualTo("/maze-game/HealthCheck"))
//                .withHeader("Accept", equalTo("text/json"))
//                .willReturn(aResponse()
//                        .withStatus(200)
//                        .withHeader("Content-Type", "text/xml")
//                        .withBody("currentTime: '2016-08-20'")));

        HealthCheck hc = client.healthCheck();
        assertNotNull(hc.getCurrentTime());

//        verify(postRequestedFor(urlMatching("/my/resource/[a-z0-9]+"))
//                .withRequestBody(matching(".*<message>1234</message>.*"))
//                .withHeader("Content-Type", notMatching("application/json")));
    }

    @Test
    public void shouldGetGreeting() {
        
        Greeting greet = client.greetTeam();
        assertNotNull(greet);
    }

    @Test
    public void startCompetition() {
        
        StartCompetition start = client.startCompetition();
        assertNotNull(start);
    }

}
