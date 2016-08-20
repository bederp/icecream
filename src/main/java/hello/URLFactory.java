package hello;/*
    Created by kinder112 on 20.08.2016.
 */

public class URLFactory {
    private static final String PROD = "http://192.168.0.50:12345/maze-game/";
    private static final String TEST = "https://hackathon-mutineer.c9users.io/";

    public static String getProd(Methods method){
        return PROD + method.name();
    }

    public static String getTest(Methods method){
        return TEST + method.name();
    }


}
