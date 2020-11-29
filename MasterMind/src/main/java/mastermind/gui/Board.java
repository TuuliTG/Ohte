/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mastermind.gui;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import mastermind.domain.Constants;
import mastermind.domain.Piece;
import mastermind.domain.Tile;
import mastermind.gamelogic.GameLogic;



/**
 *
 * @author tgtuuli
 */
public class Board {
    
    private Group tileGroup;
    private Group pieceGroup;
    private Group feedBackPlaceGroup;
    private int activeRow;
    private Tile[][] tiles = new Tile[Constants.WIDTH][Constants.HEIGHT];
    private Label roundLabel;
    private Label guessesLeftLabel;
    private Label gameOver;
    private GameLogic game;
    private Pane pane;
    private int height;
    private int guessesLeft;
    
    
    public Board() {
        this.height = Constants.HEIGHT;
        
    }
    
    public Board(int height) {
       this.height = height;
       
    }

    public Tile[][] getTiles() {
        return tiles;
    }

    public void setNextActiveRow() {
        this.activeRow++;
        this.guessesLeft--;
        
    }
    
    public int getActiveRow() {
        return activeRow;
    }
    
    public void setOtherOnRowBrown(int activeTile) {
        for (int i = 0; i < 5; i++) {
            Tile t = tiles[i][activeRow];
            if (i != activeTile) {
                t.setFillDark();
            } 
        }
    }
    
    public void setAllBrown() {
        for (int i = 0; i < 5; i++) {
            Tile t = tiles[i][activeRow];
            t.setFillDark();
        }
    }
    
    public String[] guessedColors() {
        String[] guess = new String[4];
        for (int i = 0; i < 4; i++) {
            guess[i] = tiles[i][activeRow].getPiece().getColorInText();
        }
        return guess;
    }
    
    public Tile getCurrentActiveFeedbackTile() {
        Tile t = tiles[Constants.WIDTH - 1][activeRow];
        return this.tiles[Constants.WIDTH - 1][activeRow];
    }
    
    public void setUpScene() {
        
        this.pane = new Pane();
        pane.setPrefSize(Constants.WIDTH * Constants.TILE_SIZE * 2 ,  this.height * Constants.TILE_SIZE * 1.5);
        
        this.guessesLeft = this.height;
        
        
        
        this.game = new GameLogic();
        this.activeRow = 0;
        
        this.setUpTilesAndPieces();
        pane.getChildren().addAll(tileGroup, pieceGroup, feedBackPlaceGroup);
        
       
    }
    
   
    private void setUpTilesAndPieces() {
        this.pieceGroup = new Group();
        this.feedBackPlaceGroup = new Group();
        this.tileGroup = new Group();
        for (int y = 0; y < this.height; y++) {
            for (int x = 0; x < Constants.WIDTH; x++) {
                
                if (x < Constants.WIDTH - 1) {
                    Tile tile = new Tile(x, y);
                    tile.setIsFeedbackTile(false);
                    Piece piece = new Piece(Constants.PIECE_SIZE, 
                            Color.GREY, x * Constants.TILE_SIZE + Constants.TILE_SIZE / 2, 
                            y * Constants.TILE_SIZE + Constants.TILE_SIZE / 2);
                    pieceGroup.getChildren().add(piece);
                    tile.setPiece(piece);
                    tiles[x][y] = tile;
                    tile.setPlace(x);
                    tileGroup.getChildren().add(tile);
                } else {
                    Tile tile = new Tile(x, y);
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
    
    public void giveFeedback() {
        int[] feedback = game.getFeedback();
        boolean gameIsOver = game.isGameIsOver();
       
        Tile t = getCurrentActiveFeedbackTile();
        t.setFillLight();
        Piece[] feedbackPieces = t.getFeedbackPieces();
        for (int i = 0; i < 4; i++) {
            if (feedback[i] == 1) {
                feedbackPieces[i].setFeebackPieceColor(Color.BLACK);
            } else if (feedback[i] == 0) {
                feedbackPieces[i].setFeebackPieceColor(Color.WHITE);
            } 
        }
    }
   
    private ArrayList<Piece> setFeedbackPieces(int x, int y) {
        Piece piece1 = new Piece(5, Color.GREY, x * Constants.TILE_SIZE + 10, y * Constants.TILE_SIZE + 10);
        Piece piece2 = new Piece(5, Color.GREY, x * Constants.TILE_SIZE + Constants.TILE_SIZE - 10, y * Constants.TILE_SIZE + Constants.TILE_SIZE - 10);
        Piece piece3 = new Piece(5, Color.GREY, x * Constants.TILE_SIZE + 10, y * Constants.TILE_SIZE + Constants.TILE_SIZE - 10);
        Piece piece4 = new Piece(5, Color.GREY, x * Constants.TILE_SIZE + Constants.TILE_SIZE - 10, y * Constants.TILE_SIZE + 10);
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

    public Pane getPane() {
        return pane;
    }
    
    public boolean gameIsover() {
        return game.isGameIsOver();
    }

    public int getGuessesLeft() {
        return guessesLeft;
    }
    
    public Tile findTile(double x, double y) {
        Tile[][] tiles = this.getTiles();
        int row = this.getActiveRow();
        double boardsStartingPoint = this.getPane().getLayoutY();
        if (y > boardsStartingPoint + row * Constants.TILE_SIZE && y < boardsStartingPoint + row * Constants.TILE_SIZE + Constants.TILE_SIZE) {
            int tileNr = (int) x / Constants.TILE_SIZE;
            if (tileNr < 4) {
                return tiles[tileNr][row];
            }
        }
        return null;
    }

    public Label getGameOver() {
        return gameOver;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public GameLogic getGame() {
        return game;
    }
    
}
