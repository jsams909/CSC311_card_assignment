package com.example.csc311_card_assignment;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class HelloController {

//private Cards cardsClass = new Cards();

    @FXML
    private VBox Main_vbox;

    @FXML
    private Button drawButton;

    @FXML
    private ImageView firstCard;

    @FXML
    private ImageView fourthCard;

    @FXML
    private Button hintButton;

    @FXML
    private ImageView secondCard;

    @FXML
    private ImageView thirdCard;

    @FXML
    private Button verifyButton;

    @FXML
    private TextField expressionBar;

    @FXML
    void verifyAction(ActionEvent event) {
        try {
            // Get the text from the text field
            String expression = expressionBar.getText().trim();

            // Create a simple expression evaluator
            // You may need to implement or use a library for this
            double numericResult = evaluateExpression(expression);

            // Check if the result equals 24
            if (Math.abs(numericResult - 24.0) < 0.0001) {
                // Success case
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText("Correct!");
                alert.setContentText("The expression evaluates to 24.");
                alert.showAndWait();
            } else {
                // Incorrect result
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Incorrect");
                alert.setHeaderText("Wrong Answer");
                alert.setContentText("The expression evaluates to " + numericResult + ", not 24.");
                alert.showAndWait();
            }
        } catch (Exception e) {
            // Handle parsing errors or invalid expressions
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Invalid Expression");
            alert.setContentText("Unable to evaluate the expression: " + e.getMessage());
            alert.showAndWait();
        }
    }

    // A simple expression evaluator that handles basic operations
    private double evaluateExpression(String expression) {
        try {
            // Remove all whitespace
            expression = expression.replaceAll("\\s+", "");

            // Use a third-party library or implement your own parser
            // For simplicity, here's a very basic implementation that can handle
            // addition, subtraction, multiplication, and division with proper precedence
            String finalExpression = expression;
            return new Object() {
                int pos = -1, ch;

                void nextChar() {
                    ch = (++pos < finalExpression.length()) ? finalExpression.charAt(pos) : -1;
                }

                boolean eat(int charToEat) {
                    while (ch == ' ') nextChar();
                    if (ch == charToEat) {
                        nextChar();
                        return true;
                    }
                    return false;
                }

                double parse() {
                    nextChar();
                    double x = parseExpression();
                    if (pos < finalExpression.length()) throw new RuntimeException("Unexpected: " + (char)ch);
                    return x;
                }

                // Grammar:
                // expression = term | expression `+` term | expression `-` term
                // term = factor | term `*` factor | term `/` factor
                // factor = `+` factor | `-` factor | `(` expression `)` | number

                double parseExpression() {
                    double x = parseTerm();
                    for (;;) {
                        if      (eat('+')) x += parseTerm(); // addition
                        else if (eat('-')) x -= parseTerm(); // subtraction
                        else return x;
                    }
                }

                double parseTerm() {
                    double x = parseFactor();
                    for (;;) {
                        if      (eat('*')) x *= parseFactor(); // multiplication
                        else if (eat('/')) x /= parseFactor(); // division
                        else return x;
                    }
                }

                double parseFactor() {
                    if (eat('+')) return parseFactor(); // unary plus
                    if (eat('-')) return -parseFactor(); // unary minus

                    double x;
                    int startPos = this.pos;
                    if (eat('(')) { // parentheses
                        x = parseExpression();
                        eat(')');
                    } else if ((ch >= '0' && ch <= '9') || ch == '.') { // numbers
                        while ((ch >= '0' && ch <= '9') || ch == '.') nextChar();
                        x = Double.parseDouble(finalExpression.substring(startPos, this.pos));
                    } else {
                        throw new RuntimeException("Unexpected: " + (char)ch);
                    }

                    return x;
                }
            }.parse();
        } catch (Exception e) {
            throw new RuntimeException("Invalid expression: " + e.getMessage());
        }
    }

    String[] cards={"2_of_clubs.png", "3_of_clubs.png", "4_of_clubs.png", "5_of_clubs.png",
            "6_of_clubs.png", "7_of_clubs.png", "8_of_clubs.png", "9_of_clubs.png", "10_of_clubs.png", "jack_of_clubs.png",
            "queen_of_clubs.png", "king_of_clubs.png", "ace_of_clubs.png", "2_of_diamonds.png",
            "3_of_diamonds.png", "4_of_diamonds.png", "5_of_diamonds.png", "6_of_diamonds.png",
            "7_of_diamonds.png", "8_of_diamonds.png", "9_of_diamonds.png", "10_of_diamonds.png",
            "jack_of_diamonds.png", "queen_of_diamonds.png", "king_of_diamonds.png", "ace_of_diamonds.png",
            "2_of_hearts.png", "3_of_hearts.png", "4_of_hearts.png", "5_of_hearts.png", "6_of_hearts.png",
            "7_of_hearts.png", "8_of_hearts.png", "9_of_hearts.png", "10_of_hearts.png", "jack_of_hearts.png",
            "queen_of_hearts.png", "king_of_hearts.png", "ace_of_hearts.png", "2_of_spades.png",
            "3_of_spades.png", "4_of_spades.png", "5_of_spades.png", "6_of_spades.png", "7_of_spades.png",
            "8_of_spades.png", "9_of_spades.png", "10_of_spades.png", "jack_of_spades.png", "queen_of_spades.png",
            "king_of_spades.png", "ace_of_spades.png"};


    //ArrayList<String> deck = new ArrayList<>();
    //void addDeck(){

    // deck.addAll(Arrays.asList(cards));
    //}
    Random random = new Random();
    public void shuffle(){
        int n = cards.length;
        Random random = new Random();
        for (int i = n - 1; i > 0; i--) {
            int j = random.nextInt(i + 1); // Generate a random index from 0 to i
            // Swap the current element with the randomly selected element
            String temp = cards[i];
            cards[i] = cards[j];
            cards[j] = temp;
        }
    }
    @FXML
    void drawCards(MouseEvent event) {

        shuffle();
        firstCard.setImage(new Image(cards[0]));
        fourthCard.setImage(new Image(cards[1]));
        secondCard.setImage(new Image(cards[2]));
        thirdCard.setImage(new Image(cards[3]));

        //firstCard.setImage(new Image(getClass().getResourceAsStream("images/" + cards.cards[0])));
        //secondCard.setImage(new Image(getClass().getResourceAsStream("images/" + cards.cards[1])));
        //thirdCard.setImage(new Image(getClass().getResourceAsStream("images/" + cards.cards[2])));
        //fourthCard.setImage(new Image(getClass().getResourceAsStream("images/" + cards.cards[3])));

    }}

