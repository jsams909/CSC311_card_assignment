package com.example.csc311_card_assignment;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

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

