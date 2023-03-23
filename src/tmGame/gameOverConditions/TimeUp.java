package tmGame.gameOverConditions;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;

import javax.management.InstanceNotFoundException;

import tmGame.TileMatchingGame;

public class TimeUp implements GameOverCondition{
//Only one instance of timeup is created at the start of the game. Currently WIP
    private Clock clock; 
    private Instant endTime;

    public TimeUp(Clock clock, int seconds)
    {
        //This clock is the time that the player started.
        this.clock = clock;
        this.endTime = clock.instant().plusSeconds(seconds);
    }

    @Override
    public boolean isGameOver(TileMatchingGame game) {
        //Calculate current time and subtract -- work in progress
        long secondsRemaining = Duration.between(clock.instant(), endTime).getSeconds();
        return secondsRemaining <= 0;
    }
}
