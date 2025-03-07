package com.example.csc311_card_assignment;

import javafx.scene.image.Image;


import java.util.*;

public class Cards {

    String answer;

    public enum CardValue {
        placeHolder, Ace, Two, Three, Four, Five, Six,
        Seven, Eight, Nine, Ten, Jack, Queen, King
    }

    ;

    public enum CardSuit {Clubs, Spades, Hearts, Diamonds}

    ;
     final CardValue cardValue;
    final CardSuit cardSuit;

    public Cards(CardValue cardValue, CardSuit cardSuit) {
        this.cardValue = cardValue;
        this.cardSuit = cardSuit;
    }

    public CardValue getCardValue() {
        return cardValue;
    }

    public CardSuit getCardSuit() {
        return cardSuit;
    }

    public String toString() {
        return String.format("%s: %s", cardValue, cardSuit);
    }

    
    public void cardsToObject(){
    int count = 0;
    
        for(CardSuit c : CardSuit.values()){
        for(CardValue v : CardValue.values()){
            cards[count]= String.valueOf(new Cards(cardValue,cardSuit));
            count++;
        }}
    }

    String[] cards = {"2_of_clubs.png", "3_of_clubs.png", "4_of_clubs.png", "5_of_clubs.png",
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

    public void Shuffle() {
        for (int i = 0; i < cards.length; i++) {
            int index = random.nextInt(cards.length);
            Image card = new Image("file:" + cards[index]);

             //int firstCard = random.nextInt(cards.length);
             //int secondCard = random.nextInt(cards.length);
             //int thirdCard = random.nextInt(cards.length);
             //int fourthCard = random.nextInt(cards.length);
         }

         }
     }
/**

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
 }}*/






