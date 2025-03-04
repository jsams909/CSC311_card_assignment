package com.example.csc311_card_assignment;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

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
    void drawCards(ActionEvent event) {

        cards.generateCards();
        firstCard.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("images/" + cards.deck.get(0)))));
        secondCard.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("images/" + cards.deck.get(1)))));
        thirdCard.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("images/" + cards.deck.get(2)))));
        fourthCard.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("images/" + cards.deck.get(3)))));

    }}

