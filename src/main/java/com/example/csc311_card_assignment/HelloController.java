package com.example.csc311_card_assignment;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

import java.util.List;
import java.util.Objects;

public class HelloController {

private Cards cards = new Cards();

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
    void drawCards(MouseEvent event) {
        Cards.Shuffle(cards);
        //cards.addDeck();

        firstCard.setImage(new Image(getClass().getResourceAsStream("images/" + cards.cards[0])));
        secondCard.setImage(new Image(getClass().getResourceAsStream("images/" + cards.cards[1])));
        thirdCard.setImage(new Image(getClass().getResourceAsStream("images/" + cards.cards[2])));
        fourthCard.setImage(new Image(getClass().getResourceAsStream("images/" + cards.cards[3])));

    }}

