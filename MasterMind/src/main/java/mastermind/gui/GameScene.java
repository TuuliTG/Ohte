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

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import mastermind.util.Constants;
import mastermind.domain.Options;
import mastermind.domain.PlayerService;
import mastermind.gamelogic.GameLogic;

/**
 * Shows the Main Game scene and interacts with other scenes.
 * @author tgtuuli
 */
public class GameScene {
    
    private Board board;
    private boolean programIsStarting;
    private BorderPane bPane;
    private Button acceptGuessButton;
    private ChoiceBox<Integer> guessesChoicebox;
    private GameLogic game;
    private ImageView arrow;
    private HBox hBox;
    private Label roundLabel;
    private Label guessesLeftLabel;
    private Label playerLabel;
    private Label gameOver; 
    private Label blanksNotAllowed;
    private Menu gameMenu, helpMenu;
    private MenuItem newGame, endGame, scores, help, options;
    private Options newGameOptions;
    private Pane pane;
    private PlayerService playerService;
    private Scene gameScene, optionsScene;
    private Stage window;
    private StopWatch timer;
    private VBox vbox;
    
    /**
     * Constructor for starting a new game with default options.
     * @param stage
     * @param playerService
     * @param gameIsStarting 
     */
    public GameScene(Stage stage, PlayerService playerService, boolean gameIsStarting) {
        this.window = stage;
        board = new Board();
        newGameOptions = new Options();
        this.playerService = playerService;
        if (gameIsStarting) {
            NewGameWindow newGameWindow = new NewGameWindow(playerService);
            newGameWindow.show(window);
        } else {
            this.newGame();
        }
    }
    
    /**
     * Constructor for setting up a new game with new options.
     * @param stage
     * @param options
     * @param playerService 
     */
    public GameScene(Stage stage, Options options, PlayerService playerService) {
        this.window = stage;
        board = new Board();
        this.playerService = playerService;
        newGameOptions = options;
        this.newGame();
    }
    
    
    public void moveArrowDown() {
        this.arrow.setY(arrow.getY() + Constants.TILE_SIZE);
    }
    
    private void setUpGameScene() {
        setMenuBar();
        mouseEvents();
        gameScene = new Scene(bPane);
        window.setTitle("MASTERMIND");
        window.setScene(gameScene);
        
        window.show();
    }
    
    private void setMenuBar() {
        gameMenu = new Menu("Game");
        helpMenu = new Menu("Help");
        setNewGameMenu();
        setOptionsMenu();
        setScoreMenu();
        setEndGame();
        setHelpMenu();
        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(gameMenu, helpMenu);
        this.bPane.setTop(menuBar);
    }
    
    private void setOptionsMenu() {
        this.options = new MenuItem("Options...");
        options.setOnAction(e -> {
            this.timer.getTimeline().pause();
            OptionsWindow optionsWindow = new OptionsWindow(timer, window, gameScene, playerService);
            optionsWindow.displayOptions();
        });
        gameMenu.getItems().add(options);
    }
    
    private void setNewGameMenu() {
        newGame = new MenuItem("New Game");
        newGame.setOnAction(e -> {
            this.timer.getTimeline().stop();
            NewGameWindow newGameWindow = new NewGameWindow(playerService);
            newGameWindow.show(window);
        });
        gameMenu.getItems().add(newGame);
    }
    private void setEndGame() {
        
        this.endGame = new MenuItem("End Game");
        endGame.setOnAction(e -> {
            window.close();
        });
        gameMenu.getItems().add(endGame);
    }
    
    private void setScoreMenu() {
        this.scores = new MenuItem("Score Board");
        scores.setOnAction(e -> {
            timer.getTimeline().pause();
            ScoreBoard scoreBoard = new ScoreBoard(window, timer, playerService, gameScene);
            scoreBoard.displayScoreBoard();
        });
        gameMenu.getItems().add(scores);
    }
    
    private void setHelpMenu() {
        this.help = new MenuItem("game instructions");
        
        help.setOnAction(e -> {
            this.timer.getTimeline().pause();
            HelpWindow helpWindow = new HelpWindow(this.window, this.gameScene, timer);
            helpWindow.show();
        });
        helpMenu.getItems().add(help);
    }
       
    private void mouseEvents() {
          
        bPane.setOnMouseMoved(e -> {
            this.mouseMoved(e.getX(), e.getY());
        });

        bPane.setOnMouseClicked(e -> {
            if (!this.board.gameIsOver()) {
                Tile t = this.board.findTile(e.getX(), e.getY());
                if (t != null) {
                    Piece p = t.getPiece();
                    if (e.getButton() == MouseButton.PRIMARY) {
                        p.setNextColor();
                    } else if (e.getButton() == MouseButton.SECONDARY) {
                        p.setPreviousColor();
                    }
                }
            }
        });
    }
    
    private void mouseMoved(double x, double y) {
        if (!this.board.gameIsOver()) {
            Tile t = board.findTile(x, y);
            if (t != null) {
                t.setFillLight();
                this.board.setOtherOnRowBrown(t.getPlace());
            } else {
                this.board.setAllBrown();
            }
            if (!blanksNotAllowed.getText().equals("")) {
                blanksNotAllowed.setText("");
                blanksNotAllowed.setStyle("-fx-border-style:null; -fx-border-width: 0; "
                    + "-fx-border-color:null;-fx-background-color:null");
            }
        }
    }
        
    private void setUpLabels() {
        blanksNotAllowed = new Label();
        roundLabel = new Label();
        playerLabel = new Label("PLAYER: " + playerService.getCurrentPlayer().getName());
        playerLabel.setStyle("-fx-font-size: 2em;");
        playerLabel.setPadding(new Insets(10));
        guessesLeftLabel = new Label();
        gameOver = new Label();
        roundLabel.setText("ROUND: " + 1);
        roundLabel.setPadding(new Insets(10));
        hBox.getChildren().add(roundLabel);
        guessesLeftLabel.setText("Guesses left: " + this.newGameOptions.getHeight());
        guessesLeftLabel.setPadding(new Insets(10));
        
        hBox.getChildren().add(guessesLeftLabel);
        
        hBox.getChildren().add(gameOver);
    }
          
    private void setUpButtons() {
        this.acceptGuessButton = new Button();
        acceptGuessButton.setText("Accept Guess");
        acceptGuessButton.setPadding(new Insets(10));
        acceptGuessButton.setOnAction(e -> acceptButtonActions());
        
        acceptGuessButton.setLayoutX(Constants.WIDTH * Constants.TILE_SIZE + 100);
        acceptGuessButton.setLayoutY(20);
        hBox.getChildren().add(acceptGuessButton);
    }
           
    private void setArrowSign() {
        
        Image i;
        arrow = new ImageView();
        try {
            ClassLoader classLoader = this.getClass().getClassLoader();
            String imageUrl = classLoader.getResource("public/arrowLeft.png").toExternalForm();
            i = new Image(imageUrl);
            arrow.setImage(i);
        } catch (Exception e) {
            System.out.println("file not found. " + e.getMessage());
        }
        arrow.setX(Constants.TILE_SIZE * Constants.WIDTH + 10);
        arrow.setY(this.board.getPane().getLayoutY() - 10);
        arrow.setFitHeight(70);
        arrow.setFitWidth(70);
        arrow.setRotate(180);
        this.board.getPane().getChildren().add(arrow);
        
    }
    
    private void setNoBlanksAllowedText() {
        this.blanksNotAllowed.setText("Set color to every piece!");
        blanksNotAllowed.setLayoutX(this.board.getPane().getTranslateX() + 50);
        blanksNotAllowed.setLayoutY(this.board.getPane().getTranslateY() + (this.board.getActiveRow() * Constants.TILE_SIZE));
        blanksNotAllowed.setStyle("-fx-font-size: 1em; -fx-border-style:solid; -fx-border-width: 1; "
                + "-fx-border-color:black;-fx-background-color: white");
    }
            
    private void newGame() {
        int height = newGameOptions.getHeight();
        timer = new StopWatch();
        board.setHeight(height);
        board.setUpScene();
        Pane boardPane = board.getPane();
        Insets insets = new Insets(10);
        hBox = new HBox();
        hBox.setPadding(new Insets(10));
        vbox = new VBox(8);
        
        bPane = new BorderPane();
        this.bPane.setBottom(boardPane);
        BorderPane.setMargin(boardPane, insets);
        BorderPane.setMargin(hBox, insets);
        setUpButtons();
        this.setUpLabels();
        vbox.getChildren().add(playerLabel);
        vbox.getChildren().add(hBox);
        boardPane.getChildren().add(blanksNotAllowed);
        this.setArrowSign();
        this.bPane.setCenter(vbox);
        this.startTimer();
        this.setUpGameScene();
        
    }
    
    private void startTimer() {
        if (timer.getTimeline() != null) {
            timer.setMinutes(Duration.ZERO);
            timer.getTimeSeconds().set((int) timer.getSeconds().toMinutes());
            
        } else {
            handleTimer();
        }
        
        this.hBox.getChildren().add(timer.getMinutesLabel());
        this.hBox.getChildren().add(timer.getSecondsLabel());
    }
    
    private void optionsMenuActions() {
        
        this.timer.getTimeline().pause();
        
        OptionsWindow optionsWindow = new OptionsWindow(timer, window, gameScene, playerService);
        optionsWindow.displayOptions();

    }
    
    private void acceptButtonActions() {
        if (!this.board.gameIsOver()) {
            String[] guess = this.board.guessedColors();
            if (guessHasBlanks(guess)) {
                this.setNoBlanksAllowedText();
            } else {
                board.getGame().setGuess(guess);
                this.board.giveFeedback();
                this.board.setNextActiveRow();
                this.roundLabel.setText("ROUND: " + (board.getActiveRow() + 1));
                this.guessesLeftLabel.setText("Guesses left: " + board.getGuessesLeft());

                if (board.getGuessesLeft() == 0 || board.gameIsOver()) {
                    this.endGameActions();
                } else {
                    moveArrowDown();
                }        
            }   
        }
    }
    
    private void endGameActions() {
        if (board.gameIsOver()) {
            setScore();
        }
        this.timer.getTimeline().stop();
        this.timer.setGameIsOver(true);
        GameOverWindow gameOverWindow = new GameOverWindow(board.gameIsOver(), timer, playerService);
        gameOverWindow.showGameOverWindow(window);    
    }
    
    private boolean guessHasBlanks(String[] guess) {
        boolean hasBlanks = false;
        for (int i = 0; i < 4; i++) {
            if (guess[i].equals("Grey")) {
                hasBlanks = true;
            } 
        }
        return hasBlanks;
    }
    
    private void handleTimer() {
        timer.setTimeline(new Timeline(
                new KeyFrame(Duration.millis(1), new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent t) {
                        
                        Duration duration = ((KeyFrame) t.getSource()).getTime();
                        timer.setMinutes(timer.getMinutes().add(duration));
                        timer.setSeconds(timer.getSeconds().add(duration));
                        timer.setMilliSeconds(timer.getMilliSeconds().add(duration));

                        if (timer.getSeconds().greaterThan(Duration.seconds(60))) {
                            timer.setSeconds(Duration.ZERO);
                        }
                        timer.getTimeMinutes().set((int) timer.getMinutes().toMinutes());
                        timer.getTimeSeconds().set((int) timer.getSeconds().toSeconds());

                    }
                })
            ));
            
        timer.getTimeline().setCycleCount(Timeline.INDEFINITE);
        timer.getTimeline().play();
    }
    
    private void setScore() {
        playerService.addGame(timer.getMilliSeconds().toMillis(), this.board.getActiveRow());
        
    }

    public Scene getGameScene() {
        return gameScene;
    }
    
    
}
