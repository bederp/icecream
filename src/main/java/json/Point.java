package json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import hello.Methods;

/*
    Created by kinder112 on 20.08.2016.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Point {

    private int x;
    private int y;

    public Point() {
    }

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Point(Point p) {
        this(p.getX(), p.getY());
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void moveUp() {
        this.y -= 1;
    }

    public void moveDown() {
        this.y += 1;
    }

    public void moveLeft() {
        this.x -= 1;
    }

    public void moveRight() {
        this.x += 1;
    }

    @Override
    public String toString() {
        return x + " " + y;
    }

    public void move(Methods move) {
        switch (move) {
            case MoveDown:
                this.moveDown();
                break;
            case MoveUp:
                this.moveUp();
                break;
            case MoveLeft:
                this.moveLeft();
                break;
            case MoveRight:
                this.moveRight();
                break;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Point point = (Point) o;

        if (x != point.x) {
            return false;
        }
        return y == point.y;

    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }
}
