package com.example.csc311_card_assignment;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.Random;

public class Cards {
    ArrayList<String> deck;
    String answer;

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
    public void cards() {
        this.deck = new ArrayList<String>();
        this.answer = "";
    }
    public void generateCards(){
        //this.deck.clear();
    //ImageView[] currentCards = new ImageView[4];
        for(int i = 0; i<4; i++){
        int randomCard = random.nextInt(52);  // Get a random index
            while (this.deck.contains(cards[randomCard])) {
                randomCard = random.nextInt(cards.length);
            }
            this.deck.add(cards[randomCard]);
      // Image card = new Image("file:" + cards[randomCard]);  // Load the random image
        //currentCards[i] = new ImageView(card);
    }}




}
