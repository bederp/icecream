package hello;/*
    Created by kinder112 on 20.08.2016.
 */

public class StartCompetition {
    Point startPoint;
    Point endPoint;

    public void setStartPoint(Point startPoint) {
        this.startPoint = startPoint;
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
