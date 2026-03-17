public class CardDeck{
    private Array<String> cards;
    private DynamicArray<String> playersCards;
    private DynamicArray<String> dealersCards;
    private DynamicArray<String> splitDeck;


    
    public CardDeck( ){
        cards = new Array<>();
        playersCards = new DynamicArray<>();
        dealersCards = new DynamicArray<>();
        splitDeck = new DynamicArray<>();

    
        cards.append("Ace Of Hearts");
        cards.append("2 Of Hearts");
        cards.append("3 Of Hearts");
        cards.append("4 Of Hearts");
        cards.append("5 Of Hearts");
        cards.append("6 Of Hearts");
        cards.append("7 Of Hearts");
        cards.append("8 Of Hearts");
        cards.append("9 Of Hearts");
        cards.append("10 Of Hearts");
        cards.append("Jack Of Hearts");
        cards.append("Queen Of Hearts");
        cards.append("King Of Hearts");

        cards.append("Ace Of Diamonds");
        cards.append("2 Of Diamonds");
        cards.append("3 Of Diamonds");
        cards.append("4 Of Diamonds");
        cards.append("5 Of Diamonds");
        cards.append("6 Of Diamonds");
        cards.append("7 Of Diamonds");
        cards.append("8 Of Diamonds");
        cards.append("9 Of Diamonds");
        cards.append("10 Of Diamonds");
        cards.append("Jack Of Diamonds");
        cards.append("Queen Of Diamonds");
        cards.append("King Of Diamonds");

        cards.append("Ace Of Clubs");
        cards.append("2 Of Clubs");
        cards.append("3 Of Clubs");
        cards.append("4 Of Clubs");
        cards.append("5 Of Clubs");
        cards.append("6 Of Clubs");
        cards.append("7 Of Clubs");
        cards.append("8 Of Clubs");
        cards.append("9 Of Clubs");
        cards.append("10 Of Clubs");
        cards.append("Jack Of Clubs");
        cards.append("Queen Of Clubs");
        cards.append("King Of Clubs");

        cards.append("Ace Of Spades");
        cards.append("2 Of Spades");
        cards.append("3 Of Spades");
        cards.append("4 Of Spades");
        cards.append("5 Of Spades");
        cards.append("6 Of Spades");
        cards.append("7 Of Spades");
        cards.append("8 Of Spades");
        cards.append("9 Of Spades");
        cards.append("10 Of Spades");
        cards.append("Jack Of Spades");
        cards.append("Queen Of Spades");
        cards.append("King Of Spades");

    }

    public String getCard(int index) {
        return cards.get(index);
    }

    public String removeCard(int index) {
        return cards.remove(index);
    }

    public String removePlayerCard(String cardName) {
        playersCards.remove(cardName);
        return cardName;
    }

    public int deckSize(){
        return cards.size();
    }

    public void playerAppend(String value){
        playersCards.append(value);
    }

    public String getPlayersCard(int index){
        return playersCards.get(index);
    }

    public void dealerAppend(String value){
        dealersCards.append(value);
    }

    public void splitDeckAppend(String value){
        splitDeck.append(value);
    }

    public String returnPlayerCards(){
        return playersCards.toString();
    }

    public String returnDealerCards(){
        return dealersCards.toString();
    }

    public String returnDealerFirstCard() {
        if (dealersCards.size() == 0){
            return "none";
        }
        return dealersCards.get(0);
    }

    public String returnSplitDeck(){
        return splitDeck.toString();
    }

    public int playerCardSize(){
        return playersCards.size();
    }

    public int dealerCardSize(){
        return dealersCards.size();
    }

    public int splitDeckSize(){
        return splitDeck.size();
    }
}
