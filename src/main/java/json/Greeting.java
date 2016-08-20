/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 *
 * @author stolawoj
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Greeting {
    private String greeting;

    public String getGreeting() {
        return greeting;
    }

    public void setGreeting(String greeting) {
        this.greeting = greeting;
    }

    @Override
    public String toString() {
        return "Greeting{" + "greeting=" + greeting + '}';
    }
    
}
