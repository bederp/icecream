package hello;/*
    Created by kinder112 on 20.08.2016.
 */

import com.google.common.collect.ImmutableMap;

public enum Methods {

    HealthCheck,
    GreatTeam,
    StartCompetition,
    MoveUp,
    MoveDown,
    MoveRight,
    MoveLeft,
    Scan,
    ScanUp,
    ScanDown,
    ScanRight,
    ScanLeft;

    public static final ImmutableMap<Methods,Methods> opposites = ImmutableMap.<Methods, Methods>builder()
            .put(MoveUp, MoveDown)
            .put(MoveDown, MoveUp)
            .put(MoveLeft, MoveRight)
            .put(MoveRight, MoveLeft)
            .build();
}
