/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mastermind.domain;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import mastermind.ui.GameLogic;

/**
 *
 * @author tgtuuli
 */
public class Board {
    
    private Pane root;
    private Group tileGroup = new Group();
    private Group pieceGroup = new Group();
    private Group feedBackPlaceGroup = new Group();
    private int activeRow;
    private Tile[][] tiles = new Tile[constants.WIDTH][constants.HEIGHT];
    private Label roundLabel;
    private Label guessesLeft;
    private Label gameOver;
    private ImageView arrow;
    private GameLogic game;
    private Button acceptGuessButton;
    
    public Board(Pane pane) throws FileNotFoundException {
        root = pane;
        
        root.setPrefSize(constants.WIDTH * constants.TILE_SIZE * 2 ,  constants.HEIGHT * constants.TILE_SIZE * 1.5);
        root.getChildren().addAll(tileGroup, pieceGroup, feedBackPlaceGroup);
        
        this.game = new GameLogic();
        this.activeRow = 0;
        
        this.acceptGuessButton = new Button();
        setUpButtons();
        
        roundLabel = new Label();
        
        guessesLeft = new Label();
       
        gameOver = new Label();
        this.setArrowSign();
        this.setUpLabels();
        this.setUpTilesAndPieces();
        
    }

    public Tile[][] getTiles() {
        return tiles;
    }

    public void setNextActiveRow() {
        this.activeRow++;
        this.roundLabel.setText("ROUND: " + (activeRow + 1));
        this.guessesLeft.setText("Guesses left: " + (constants.HEIGHT - activeRow));
    }
    
    public int getActiveRow() {
        return activeRow;
    }
    
    public void setOtherOnRowBrown(int activeTile){
        for (int i = 0; i < 5; i++) {
            Tile t = tiles[i][activeRow];
            if (i != activeTile) {
                t.setFillDark();
            } 
        }
    }
    
    public void setAllBrown(){
        for (int i = 0; i < 5; i++) {
            Tile t = tiles[i][activeRow];
            t.setFillDark();
        }
    }
    
    public String[] guessedColors(){
        String[] guess = new String[4];
        for (int i = 0; i < 4; i++) {
            guess[i]=tiles[i][activeRow].getPiece().getColorInText();
        }
        return guess;
    }
    
    public Tile getCurrentActiveFeedbackTile() {
        Tile t = tiles[constants.WIDTH-1][activeRow];
        System.out.println(t.getPlace());
        return this.tiles[constants.WIDTH-1][activeRow];
    }
    
    private void setArrowSign() throws FileNotFoundException {
        Image image = new Image(new FileInputStream("public/arrowLeft.png"));
        
        arrow = new ImageView(image);
        arrow.setX(constants.TILE_SIZE*constants.WIDTH + 10);
        arrow.setY(-10);
        arrow.setFitHeight(70);
        arrow.setFitWidth(70);
        arrow.setRotate(180);
        root.getChildren().add(arrow);
        
    }
    
    private void setUpTilesAndPieces() {
        
        for (int y = 0; y < constants.HEIGHT; y++) {
            for (int x = 0; x < constants.WIDTH; x++) {
                
                if (x < constants.WIDTH-1) {
                    Tile tile = new Tile(x,y);
                    tile.setIsFeedbackTile(false);
                    Piece piece = new Piece(constants.PIECE_SIZE, false, 
                            Color.GREY, x*constants.TILE_SIZE + constants.TILE_SIZE/2, 
                            y*constants.TILE_SIZE + constants.TILE_SIZE/2);
                    pieceGroup.getChildren().add(piece);
                    tile.setPiece(piece);
                    tiles[x][y] = tile;
                    tile.setPlace(x);
                    tileGroup.getChildren().add(tile);
                } else {
                    Tile tile = new Tile(x,y);
                    tile.setIsFeedbackTile(true);
                    ArrayList<Piece> pieces = this.setFeedbackPieces(x, y);
                    tile.setFeedbackPieces(pieces);
                    tileGroup.getChildren().add(tile);
                    tile.setPlace(x);
                    tiles[x][y] = tile;
                }
            }
                
        }
    }
    
    private void setFeedBackPieces() {
        int[] feedback = game.getFeedback();
        boolean gameIsOver = game.isGameIsOver();
        if (gameIsOver) {
            Label gameover = getGameOver();
            gameover.setText("GAME OVER");
            
        }
        Tile t = getCurrentActiveFeedbackTile();
        System.out.println(t.getPlace());
        t.setFillLight();
        Piece[] feedbackPieces = t.getFeedbackPieces();
        for (int i = 0; i < 4; i++) {
            if (feedback[i] == 1) {
                feedbackPieces[i].setFeebackPieceColor(Color.BLACK);
            } else if (feedback[i] == 0) {
                feedbackPieces[i].setFeebackPieceColor(Color.WHITE);
            } else if (feedback[i] == 2) {
                
            }
        }
    }
    
    private void moveAcceptButtonDown() {
        this.acceptGuessButton.setLayoutY(acceptGuessButton.getLayoutY() + constants.TILE_SIZE);
    }
    
    private void setUpButtons() {
        
        acceptGuessButton.setText("Accept Guess");
        acceptGuessButton.setOnAction(e -> {
            String[] guess = guessedColors();
            game.setGuess(guess);
            this.setFeedBackPieces();
            setNextActiveRow();
            moveArrowDown();
            moveAcceptButtonDown();
            
        });
        
        acceptGuessButton.setLayoutX(constants.WIDTH * constants.TILE_SIZE + 100);
        acceptGuessButton.setLayoutY(20);
        root.getChildren().add(acceptGuessButton);
        
    }
    
    private void setUpLabels() {
        
        roundLabel.setText("ROUND: " + 1);
        roundLabel.setTranslateX(10);
        roundLabel.setTranslateY(constants.HEIGHT * constants.TILE_SIZE + constants.TILE_SIZE);
        root.getChildren().add(roundLabel);
        
        guessesLeft.setText("Guesses left: " + constants.HEIGHT);
        guessesLeft.setTranslateX(010);
        guessesLeft.setTranslateY(constants.HEIGHT * constants.TILE_SIZE + constants.TILE_SIZE + 50);
        root.getChildren().add(guessesLeft);
        
        gameOver.setTranslateX(constants.WIDTH * constants.TILE_SIZE / 2);
        gameOver.setTranslateY(constants.HEIGHT * constants.TILE_SIZE / 2);
        
        root.getChildren().add(gameOver);
    }
    
    private ArrayList<Piece> setFeedbackPieces(int x, int y){
        Piece piece1 = new Piece(5, false, Color.GREY, x*constants.TILE_SIZE + 10, y*constants.TILE_SIZE + 10);
        Piece piece2 = new Piece(5, false, Color.GREY, x*constants.TILE_SIZE + constants.TILE_SIZE-10, y*constants.TILE_SIZE + constants.TILE_SIZE-10);
        Piece piece3 = new Piece(5, false, Color.GREY, x*constants.TILE_SIZE + 10, y*constants.TILE_SIZE + constants.TILE_SIZE -10);
        Piece piece4 = new Piece(5, false, Color.GREY, x*constants.TILE_SIZE + constants.TILE_SIZE-10, y*constants.TILE_SIZE + 10);
        feedBackPlaceGroup.getChildren().add(piece1);
        feedBackPlaceGroup.getChildren().add(piece2);
        feedBackPlaceGroup.getChildren().add(piece3);
        feedBackPlaceGroup.getChildren().add(piece4);
        ArrayList<Piece> p = new ArrayList<>();
        p.add(piece1);
        p.add(piece2);
        p.add(piece3);
        p.add(piece4);
        return p;
    }

    public Label getGameOver() {
        return gameOver;
    }
    
    public void moveArrowDown() {
        this.arrow.setY(arrow.getY()+constants.TILE_SIZE);
    }
    
}
