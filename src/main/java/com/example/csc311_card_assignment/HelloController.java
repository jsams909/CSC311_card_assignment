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
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class HelloController {





    @FXML
    private VBox Main_vbox;

    @FXML
    private TextField hintField;

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
    private int[] currentCardValues = new int[4];
    //I used the "Fisher Yates" shuffle algorithm to stick with just an array
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
    //Pulls cards and calls shuffle method to shuffle them
    void drawCards(MouseEvent event) {

        shuffle();
        firstCard.setImage(new Image(cards[0]));
        fourthCard.setImage(new Image(cards[1]));
        secondCard.setImage(new Image(cards[2]));
        thirdCard.setImage(new Image(cards[3]));
        // Update the current card values
        currentCardValues[0] = getValueFromCardFile(cards[0]);
        currentCardValues[1] = getValueFromCardFile(cards[1]);
        currentCardValues[2] = getValueFromCardFile(cards[2]);
        currentCardValues[3] = getValueFromCardFile(cards[3]);
    }

    private int getValueFromCardFile(String cardFile) {
        String valuePart = cardFile.split("_")[0].toLowerCase();
        switch (valuePart) {
            case "ace": return 1;
            case "jack": return 11;
            case "queen": return 12;
            case "king": return 13;
            default: return Integer.parseInt(valuePart); // for cards 2-10
        }
    }


    @FXML
        // Build a list of expression objects from the current card values
    void hintAction(ActionEvent event) {
        List<Expression> exprList = new ArrayList<>();
        for (int value : currentCardValues) {
            exprList.add(new Expression(value, Integer.toString(value)));
        }
        String solution = solutionSolva(exprList);
        if (solution != null) {
            hintField.setText(solution);
        } else {
            hintField.setText("No solution exists for these cards.");
        }
    }

    private class Expression {
        double value;
        String expression;
        Expression(double value, String expr) {
            this.value = value;
            this.expression = expr;
        }
    }


    private String solutionSolva(List<Expression> expressions) {
        if (expressions.size() == 1) {
            if (Math.abs(expressions.get(0).value - 24) < 1e-6) {
                return expressions.get(0).expression;
            }
            return null;
        }
        // Runs through all possile expressions
        for (int i = 0; i < expressions.size(); i++) {
            for (int j = i + 1; j < expressions.size(); j++) {
                Expression a = expressions.get(i);
                Expression b = expressions.get(j);

                List<Expression> possibleResults = new ArrayList<>();
                possibleResults.add(new Expression(a.value + b.value, "(" + a.expression + "+" + b.expression + ")"));
                possibleResults.add(new Expression(a.value - b.value, "(" + a.expression + "-" + b.expression + ")"));
                possibleResults.add(new Expression(b.value - a.value, "(" + b.expression + "-" + a.expression + ")"));
                possibleResults.add(new Expression(a.value * b.value, "(" + a.expression + "*" + b.expression + ")"));
                if (Math.abs(b.value) > 1e-6) {
                    possibleResults.add(new Expression(a.value / b.value, "(" + a.expression + "/" + b.expression + ")"));
                }
                if (Math.abs(a.value) > 1e-6) {
                    possibleResults.add(new Expression(b.value / a.value, "(" + b.expression + "/" + a.expression + ")"));
                }

                // For each possible result, create a new list and try to solve further.
                for (Expression candidate : possibleResults) {
                    List<Expression> nextList = new ArrayList<>();
                    // Add the remaining expressions.
                    for (int k = 0; k < expressions.size(); k++) {
                        if (k != i && k != j) {
                            nextList.add(expressions.get(k));
                        }
                    }
                    nextList.add(candidate);
                    String solution = solutionSolva(nextList);
                    if (solution != null) {
                        return solution;
                    }
                }
            }
        }
        return null;
    }


}
