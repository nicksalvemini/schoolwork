package gurdle.gui;

import gurdle.CharChoice;
import gurdle.Model;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import util.Observer;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.util.HashMap;

/**
 * The graphical user interface to the Wordle game model in
 * {@link Model}.
 *
 * @author Nick Salvemini
 */
public class Gurdle extends Application
        implements Observer< Model, String > {

    private Model model;

    /** Width of top side boxes, allows for centering based on title */
    private final static int MIN_WIDTH = 85;
    /** Height and width */
    private final static int HEIGHT = 600;
    private final static int WIDTH = 400;
    /** Alphabet in QWERTY order */
    private final static char[][] QWERTY = {{'Q','W','E','R','T','Y','U','I','O','P'},
                                            {'A','S','D','F','G','H','J','K','L'},
                                            {'Z','X','C','V','B','N','M'}};
    /** Possible backgrounds */
    private static HashMap<CharChoice.Status, Background> backgrounds;

    // **************** VIEW ****************
    /** Top info */
    private Label completeGuesses;
    private Label title;
    private Label secret;
    private Label statusMessage;
    /** Guess grid */
    private Label[][] charSlots;
    /** HashMap to look up keys by their letter */
    private HashMap<Character, Button> keyFinder;

    /**
     * Updates the secret word when a new game is called
     */
     private void updateSecret(Model model) {
         secret.setVisible(model.isSecretVisible());
         secret.setText("Secret: " + model.secret());
     }

     /**
     * Updates the guess count when a complete guess is made
     */
     private void updateGuesses(Model model){
         completeGuesses.setText("Guesses: " + model.numAttempts());
     }

     /**
     * Updates the character slots after each guess
     */
     private void updateGuessGrid(Model model, boolean reset, boolean word){
         CharChoice current;
         if(reset){
             for(int r = 0; r < Model.NUM_TRIES; ++r) {
                 for (int c = 0; c < Model.WORD_SIZE; ++c) {
                     current = model.get(r, c);
                     charSlots[r][c].setText(current.toString());
                     charSlots[r][c].setBackground(backgrounds.get(current.getStatus()));
                 }
             }
             updateKeyboard(null, true);
         } else {
             if(word){
                 for (int c = 0; c < Model.WORD_SIZE; ++c) {
                     current = model.get(model.numAttempts(), c);
                     charSlots[model.numAttempts()][c].setText(current.toString());
                     charSlots[model.numAttempts()][c].setBackground(backgrounds.get(current.getStatus()));
                     updateKeyboard(current, false);
                 }
             } else {
                 current = model.get(model.numAttempts(), model.getCharPos());
                 charSlots[model.numAttempts()][model.getCharPos()].setText(current.toString());
             }
         }
     }

     /**
     * Updates the keyboard backgrounds based on letter statuses
     */
     private void updateKeyboard(CharChoice current, boolean reset){
         if(reset)
             for(Button button : keyFinder.values()) button.setBackground(backgrounds.get(CharChoice.Status.EMPTY));
         else if(current.getChar() != ' ') {
             if (keyFinder.get(current.getChar()).getBackground().equals(backgrounds.get(CharChoice.Status.EMPTY)))
                 keyFinder.get(current.getChar()).setBackground(backgrounds.get(current.getStatus()));
             else if(keyFinder.get(current.getChar()).getBackground().equals
                     (backgrounds.get(CharChoice.Status.WRONG_POS)) &&
                        current.getStatus() != CharChoice.Status.EMPTY)
                 keyFinder.get(current.getChar()).setBackground(backgrounds.get(current.getStatus()));
         }
    }

     /**
     * Updates the game status message
     */
     private void updateStatus(Model model){
         statusMessage.setText(model.getStateMessage(model.gameState()));
     }

     @Override
     public void init() {
        model = new Model();
        model.addObserver(this);
        backgrounds = new HashMap<>();
        backgrounds.put(CharChoice.Status.EMPTY,
                new Background(new BackgroundFill(Color.WHITE,null,null)));
        backgrounds.put(CharChoice.Status.WRONG,
                new Background(new BackgroundFill(Color.DARKGRAY,null,null)));
        backgrounds.put(CharChoice.Status.WRONG_POS,
                new Background(new BackgroundFill(Color.BURLYWOOD,null,null)));
        backgrounds.put(CharChoice.Status.RIGHT_POS,
                new Background(new BackgroundFill(Color.LIGHTGREEN,null,null)));
     }

     /**
     * Fills the BorderPane with the layout
     *
     * @return Returns the full pane
     */
     private BorderPane fillBorderPane(){

        BorderPane bPane = new BorderPane();
        // Top / Info
        HBox top = new HBox();

        top.getChildren().add(completeGuesses);
        top.getChildren().add(title);
        top.getChildren().add(secret);

        top.setSpacing((float)WIDTH / 15);
        top.setAlignment(Pos.CENTER);
        bPane.setTop(top);

        // Center / Status and guess grid
        VBox center = new VBox();

        center.getChildren().add(statusMessage);

        GridPane guessGrid = initGrid();
        guessGrid.setAlignment(Pos.CENTER);
        center.getChildren().add(guessGrid);

        center.setAlignment(Pos.CENTER);
        center.setSpacing((float) HEIGHT / 50);
        bPane.setCenter(center);

        // Bottom / Keyboard
        FlowPane keyboard = initKeyboard();
        keyboard.setAlignment(Pos.BOTTOM_CENTER);
        bPane.setBottom(keyboard);

        return bPane;
    }

     /**
     * Initializes a keyboard in standard QWERTY format
     *
     * @return Returns the keyboard as a FlowPane
     */
     private FlowPane initKeyboard(){

        FlowPane keyboard = new FlowPane();
        for(char[] row : QWERTY){
            HBox keyboardRow = new HBox();
            for(char letter : row) {
                keyFinder.get(letter).setOnAction(e -> model.enterNewGuessChar(letter));
                keyboardRow.getChildren().add(keyFinder.get(letter));
            }
            if(row[0] == QWERTY[QWERTY.length-1][0]){
                Button enter = new Button("ENTER");
                enter.setBackground(backgrounds.get(CharChoice.Status.EMPTY));
                enter.setOnAction(e -> model.confirmGuess());

                Button del = new Button("DEL");
                del.setBackground(backgrounds.get(CharChoice.Status.EMPTY));
                del.setOnAction(e -> model.enterNewGuessChar(' '));

                keyboardRow.getChildren().add(0, enter);
                keyboardRow.getChildren().add(del);
            }
            keyboard.getChildren().add(keyboardRow);
        }
        Button cheat = new Button("CHEAT");
        cheat.setBackground(backgrounds.get(CharChoice.Status.EMPTY));
        cheat.setOnAction(e -> model.showSecret());

        Button newGame = new Button("NEW GAME");
        newGame.setBackground(backgrounds.get(CharChoice.Status.EMPTY));
        newGame.setOnAction(e -> model.newGame());

        HBox extras = new HBox(cheat, newGame);
        extras.setSpacing((float)WIDTH / 4);
        keyboard.getChildren().add(extras);

        return keyboard;
    }

    /**
     * Initialize the grid of guesses
     *
     * @return Returns the empty grid
     */
    private GridPane initGrid(){

        GridPane guessGrid = new GridPane();
        for ( int r = 0; r < Model.NUM_TRIES; ++r ) {
            for ( int c = 0; c < Model.WORD_SIZE; ++c ) {
                charSlots[r][c].setMinWidth(40);
                charSlots[r][c].setTextAlignment(TextAlignment.CENTER);
                charSlots[r][c].setStyle( """
                            -fx-padding: 2;
                            -fx-border-style: solid inside;
                            -fx-border-width: 3;
                            -fx-border-insets: 0;
                            -fx-border-radius: 1;
                            -fx-border-color: black;
                """);
                guessGrid.add( charSlots[r][c], c, r );
            }
        }
        guessGrid.setStyle( "-fx-font: 32px Menlo" );
        guessGrid.setHgap((float)WIDTH / 35);

        return guessGrid;
    }

    /**
     * Initialize GUI components
     */
    private void initGUI(){
        completeGuesses = new Label();
        completeGuesses.setMinWidth(MIN_WIDTH);

        title = new Label("GUIRDLE");
        title.setStyle("-fx-font: 32px Menlo");

        secret = new Label();
        secret.setMinWidth(MIN_WIDTH);

        statusMessage = new Label();

        charSlots = new Label[Model.NUM_TRIES][Model.WORD_SIZE];
        for(int r = 0; r < Model.NUM_TRIES; ++r){
            for(int c = 0; c < Model.WORD_SIZE; ++c){
                charSlots[r][c] = new Label();
            }
        }

        keyFinder = new HashMap<>();
        for(char[] row : QWERTY){
            for(char letter : row){
                keyFinder.put(letter, new Button(String.valueOf(letter)));
            }
        }
        for(Button b : keyFinder.values())
            b.setBackground(new Background(new BackgroundFill(Color.WHITE,null,null)));
    }

    @Override
    public void start( Stage mainStage ) {

        initGUI();
        model.newGame();
        Scene scene = new Scene( fillBorderPane() );
        mainStage.setScene( scene );
        mainStage.setTitle("Gurdle");
        mainStage.setHeight(HEIGHT);
        mainStage.setWidth(WIDTH);
        mainStage.show();

    }

    @Override
    public void update(Model model, String message ) {
        String[] updates = message.split("\\s+");
        for(String update : updates){
            switch(update){
                case "secret" -> updateSecret(model);
                case "guesses" -> updateGuesses(model);
                case "guessGrid-letter" -> updateGuessGrid(model,false, false);
                case "guessGrid-word" -> updateGuessGrid(model, false, true);
                case "status" -> updateStatus(model);
                default -> {
                    updateSecret(model);
                    updateGuesses(model);
                    updateGuessGrid(model,true, false);
                    updateStatus(model);
                }
            }
        }
    }

    public static void main( String[] args ) {
        if ( args.length > 1 ) {
            System.err.println( "Usage: java Gurdle [1st-secret-word]" );
        }
        Application.launch( args );
    }
}
