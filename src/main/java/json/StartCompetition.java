package json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/*
    Created by kinder112 on 20.08.2016.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class StartCompetition {
    Point startPoint;
    Point endPoint;

    public Point getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(Point startPoint) {
        this.startPoint = startPoint;
    }

    public Point getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(Point endPoint) {
        this.endPoint = endPoint;
    }

    @Override
    public String toString() {
        return "Start point [" + startPoint + "]\n"
                + "End point [" + endPoint + "]";
    }
}
