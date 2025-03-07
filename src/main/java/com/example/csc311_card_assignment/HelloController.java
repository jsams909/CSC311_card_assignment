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
public enum CardValue{placeHolder, Ace, Two, Three, Four, Five, Six,
    Seven, Eight, Nine, Ten, Jack, Queen, King};

public enum CardSuit{Clubs, Spades, Hearts, Diamonds};

private final CardValue cardValue;
private final CardSuit cardSuit;

public HelloController(CardValue cardValue, CardSuit cardSuit){
    this.cardValue = cardValue;
    this.cardSuit = cardSuit;
}
public CardValue getCardValue(){
    return cardValue;
}
public CardSuit getCardSuit(){
    return cardSuit;
}
public String toString(){
    return String.format("%s: %s", cardValue, cardSuit);
}
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

            String expression = expressionBar.getText();


            double result = evaluateExpression(expression);

            //Google told me this was would fix my error when I just did result ==24
            if (Math.abs(result - 24.0) < 0.0001) {

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Correct Answer");
                alert.setHeaderText("Correct!");
                alert.setContentText("The expression evaluates to 24.");
                alert.showAndWait();
            } else {

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Incorrect");
                alert.setHeaderText("Wrong Answer");
                alert.setContentText("The expression evaluates to " + result + ", not 24.");
                alert.showAndWait();
            }
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Invalid Expression");
            alert.setContentText("Unable to evaluate the expression: " + e.getMessage());
            alert.showAndWait();
        }
    }

//I pulled this method off google to evaluate my expression
    private double evaluateExpression(String expression) {
        try {

            expression = expression.replaceAll("\\s+", "");


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




    Random random = new Random();
    //I used the "Fisher Yates" shuffle algorithm
    public void shuffle(){
        int n = cards.length;
        Random random = new Random();
        for (int i = n - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
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

    }}

