package tmGame.gameOverConditions;

import java.time.Clock;
import java.time.Duration;

public class TimeUp implements GameOverCondition{
//Only one instance of timeup is created at the start of the game. Currently WIP
    private Clock startTime;
    private Clock maxTime; 
    private long gameLength = 3; 

    public TimeUp(Clock startTime)
    {
        //This clock is the time that the player started.
        this.startTime = startTime;
        maxTime = Clock.offset(startTime, Duration.ofMinutes(gameLength)); //testing something
    }

    @Override
    public boolean isGameOver() {
        //Calculate current time and subtract -- work in progress
        Clock currTime = Clock.systemUTC();
        int compareResults = currTime.instant().compareTo(maxTime.instant());
        if(compareResults == 0 || compareResults == 1)
        {
            //Game over
            return true;
        }
        return false;
    }
}
