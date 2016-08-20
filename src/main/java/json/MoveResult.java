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
public class MoveResult {
    private Point position;
    private String details;
    private String outcome;

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getOutcome() {
        return outcome;
    }

    public void setOutcome(String outcome) {
        this.outcome = outcome;
    }

    @Override
    public String toString() {
        return "MoveResult{" + "position=" + position + ", details=" + details + ", outcome=" + outcome + '}';
    }

    public boolean isSucces() {
        return getOutcome().equals("success");
    }
}
