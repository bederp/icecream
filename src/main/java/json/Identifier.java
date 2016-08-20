package json;/*
    Created by kinder112 on 20.08.2016.
 */

public class Identifier {
    String teamId = "1df0455f";
    String mazeId;

    public Identifier() {
    }

    public Identifier(String mazeId) {
        this.mazeId = mazeId;
    }

    public String getTeamId() {
        return teamId;
    }

    public String getMazeId() {
        return mazeId;
    }

    public void setMazeId(String mazeId) {
        this.mazeId = mazeId;
    }
}
