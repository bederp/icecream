
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author stolawoj
 */
public class Client {
    public String healthCheck(){
        RestTemplate restTemplate = new RestTemplate();
        String localUrl = "https://hackathon-mutineer.c9users.io/StartCompetition";
        String remoteUrl = "http://192.168.0.50:12345/maze-game/HealthCheck";
        
        ResponseEntity<String> response = restTemplate.getForEntity(remoteUrl, String.class);
        
        return response.getBody();
    }
}
