package tmGame;

import java.time.Clock;

public class TimeUp implements GameOverCondition{

    private Clock maxTime; 

    TimeUp(Clock maxTime)
    {
        //this is the time that we give the playe
        this.maxTime = maxTime;
    }

    @Override
    public boolean isGameOver() {
        //Once timer is zero, game is over
        return false;
    }
}
