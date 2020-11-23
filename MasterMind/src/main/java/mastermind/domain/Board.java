/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mastermind.domain;

import java.util.ArrayList;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import static mastermind.ui.MasterMindApplication2.HEIGHT;
import static mastermind.ui.MasterMindApplication2.PIECE_SIZE;
import static mastermind.ui.MasterMindApplication2.TILE_SIZE;
import static mastermind.ui.MasterMindApplication2.WIDTH;

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
    private Tile[][] tiles = new Tile[WIDTH][HEIGHT];
    private Label roundLabel;
    private Label guessesLeft;
    private Label gameOver;
    
    public Board(Pane pane) {
        root = pane;
        
        root.setPrefSize(WIDTH * TILE_SIZE * 1.5 ,  HEIGHT * TILE_SIZE * 1.5);
        root.getChildren().addAll(tileGroup, pieceGroup, feedBackPlaceGroup);
        
        this.activeRow = 0;
        
        //Set text for round
        roundLabel = new Label();
        roundLabel.setText("ROUND: " + 1);
        roundLabel.setTranslateX(WIDTH * TILE_SIZE + 10);
        roundLabel.setTranslateY(20);
        root.getChildren().add(roundLabel);
        
        //Set text for guesses left
        guessesLeft = new Label();
        guessesLeft.setText("Guesses left: " + HEIGHT);
        guessesLeft.setTranslateX(WIDTH * TILE_SIZE + 10);
        guessesLeft.setTranslateY(40);
        root.getChildren().add(guessesLeft);
        
        //Set gameover Label
        gameOver = new Label();
        roundLabel.setTranslateX(WIDTH * TILE_SIZE + 10);
        roundLabel.setTranslateY(70);
       root.getChildren().add(gameOver);
        
        //Set tiles and pieces:
        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                
                if (x < WIDTH-1) {
                    Tile tile = new Tile(x,y);
                    tile.setIsFeedbackTile(false);
                    Piece piece = new Piece(PIECE_SIZE, false, Color.GREY, x*TILE_SIZE + TILE_SIZE/2, y*TILE_SIZE + TILE_SIZE/2);
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

    public Tile[][] getTiles() {
        return tiles;
    }

    public void setNextActiveRow() {
        this.activeRow++;
        this.roundLabel.setText("ROUND: " + (activeRow + 1));
        this.guessesLeft.setText("Guesses left: " + (HEIGHT - activeRow));
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
        Tile t = tiles[WIDTH-1][activeRow];
        System.out.println(t.getPlace());
        return this.tiles[WIDTH-1][activeRow];
    }
    
    private ArrayList<Piece> setFeedbackPieces(int x, int y){
        Piece piece1 = new Piece(5, false, Color.GREY, x*TILE_SIZE + 10, y*TILE_SIZE + 10);
        Piece piece2 = new Piece(5, false, Color.GREY, x*TILE_SIZE + TILE_SIZE-10, y*TILE_SIZE + TILE_SIZE-10);
        Piece piece3 = new Piece(5, false, Color.GREY, x*TILE_SIZE + 10, y*TILE_SIZE + TILE_SIZE -10);
        Piece piece4 = new Piece(5, false, Color.GREY, x*TILE_SIZE + TILE_SIZE-10, y*TILE_SIZE + 10);
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
    
    
    
}
