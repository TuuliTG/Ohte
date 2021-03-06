/*
 * The MIT License
 *
 * Copyright 2020 tgtuuli.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package mastermind.gui;

import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.util.Duration;

/**
 * A Class for taking time in the game
 * @author tgtuuli
 */
public class StopWatch {
    
    private boolean gameIsOver;
    private Timeline timeline;
    private final Label minutesLabel, secondsLabel;
    private final IntegerProperty timeMinutes, timeSeconds;
    private Duration minutes, seconds, milliSeconds;
    

    public StopWatch() {
        minutesLabel = new Label();
        secondsLabel = new Label();
        timeMinutes = new SimpleIntegerProperty();
        timeSeconds = new SimpleIntegerProperty();
        minutes = Duration.ZERO;
        seconds = Duration.ZERO;
        milliSeconds = Duration.ZERO;
        configLabel();
        this.gameIsOver = false;
    }
    
    private void configLabel() {
        minutesLabel.setStyle("-fx-font-size: 4em; -fx-border-style:solid; -fx-border-width: 2; -fx-border-color:black;");
        minutesLabel.setMinWidth(80);
        minutesLabel.textProperty().bind(timeMinutes.asString());
        minutesLabel.setTextFill(Color.BLACK);
        secondsLabel.textProperty().bind(timeSeconds.asString());
        secondsLabel.setTextFill(Color.BLACK);
        secondsLabel.setMinWidth(80);
        secondsLabel.setStyle("-fx-font-size: 4em; -fx-border-style:solid; -fx-border-width: 2; -fx-border-color:black;");
    }
    
    public Timeline getTimeline() {
        return timeline;
    }

    public Duration getSeconds() {
        return seconds;
    }

    public IntegerProperty getTimeMinutes() {
        return timeMinutes;
    }
    

    public IntegerProperty getTimeSeconds() {
        return timeSeconds;
    }

    public void setSeconds(Duration seconds) {
        this.seconds = seconds;
    }

    public void setTimeline(Timeline timeline) {
        this.timeline = timeline;
    }

    public Duration getMinutes() {
        return minutes;
    }

    public void setMinutes(Duration minutes) {
        this.minutes = minutes;
    }

    public Label getMinutesLabel() {
        return minutesLabel;
    }

    public Label getSecondsLabel() {
        return secondsLabel;
    }

    public Duration getMilliSeconds() {
        return milliSeconds;
    }

    public void setMilliSeconds(Duration milliSeconds) {
        this.milliSeconds = milliSeconds;
    }

    public void setGameIsOver(boolean gameIsOver) {
        this.gameIsOver = gameIsOver;
    }

    public boolean gameIsOver() {
        return gameIsOver;
    }
}
