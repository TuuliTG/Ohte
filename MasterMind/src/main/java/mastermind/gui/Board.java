/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mastermind.gui;


import java.util.ArrayList;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import mastermind.domain.Constants;
import mastermind.domain.Piece;
import mastermind.domain.Tile;
import mastermind.gamelogic.GameLogic;

/**
 * Sets up the game board and its logic
 * @author tgtuuli
 */
public class Board {
    
    private GameLogic game;
    private Group tileGroup;
    private Group pieceGroup;
    private Group feedBackPlaceGroup;
    private int activeRow;
    private int height;
    private int guessesLeft;
    private Label gameOver;
    private Pane pane;
    private Tile[][] tiles = new Tile[Constants.WIDTH][Constants.HEIGHT];
    
    public Board() {
        this.height = Constants.HEIGHT;
        
    }
    
    public Board(int height) {
        this.height = height;
       
    }

    private Tile[][] getTiles() {
        return tiles;
    }

    private Tile getCurrentActiveFeedbackTile() {
        Tile t = tiles[Constants.WIDTH - 1][activeRow];
        return this.tiles[Constants.WIDTH - 1][activeRow];
    }
    
    private Piece makePiece(int x, int y) {
        Piece piece = new Piece(Constants.PIECE_SIZE, 
                            Color.GREY, x * Constants.TILE_SIZE + Constants.TILE_SIZE / 2, 
                            y * Constants.TILE_SIZE + Constants.TILE_SIZE / 2);
        return piece;
    }
    
    private void setUpTilesAndPieces() {
        this.pieceGroup = new Group();
        this.feedBackPlaceGroup = new Group();
        this.tileGroup = new Group();
        for (int y = 0; y < this.height; y++) {
            for (int x = 0; x < Constants.WIDTH; x++) {
                if (x < Constants.WIDTH - 1) {
                    Tile tile = setUpTile(x, y);
                    Piece piece = makePiece(x, y);
                    pieceGroup.getChildren().add(piece);
                    tile.setPiece(piece);
                } else {
                    Tile tile = setUpTile(x, y);
                    ArrayList<Piece> pieces = this.setFeedbackPieces(x, y);
                    tile.setFeedbackPieces(pieces);
                }
            }
        }
    }

    private Tile setUpTile(int x, int y) {
        Tile tile = new Tile(x, y);
        tile.setPlace(x);
        tiles[x][y] = tile;
        tileGroup.getChildren().add(tile);
        return tile;
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
    
    public boolean gameIsOver() {
        return game.gameIsOver();
    }
    
    public Pane getPane() {
        return pane;
    }
    
    public int getActiveRow() {
        return activeRow;
    }
    
     public GameLogic getGame() {
        return game;
    }
     
    
     
    public Label getGameOver() {
        return gameOver;
    }
    
    public void giveFeedback() {
        int[] feedback = game.getFeedback();
        boolean gameIsOver = game.gameIsOver();
       
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
    
    public String[] guessedColors() {
        String[] guess = new String[4];
        for (int i = 0; i < 4; i++) {
            guess[i] = tiles[i][activeRow].getPiece().getColorInText();
        }
        return guess;
    }
    
    public int getGuessesLeft() {
        return guessesLeft;
    }
    
    
    
    public void setAllBrown() {
        for (int i = 0; i < 5; i++) {
            Tile t = tiles[i][activeRow];
            t.setFillDark();
        }
    }
    
    public void setHeight(int height) {
        this.height = height;
    }
    
    public void setNextActiveRow() {
        this.activeRow++;
        this.guessesLeft--;
        
    }
    
    public void setOtherOnRowBrown(int activeTile) {
        for (int i = 0; i < 5; i++) {
            Tile t = tiles[i][activeRow];
            if (i != activeTile) {
                t.setFillDark();
            } 
        }
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
}
