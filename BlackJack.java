import java.util.Random;
import java.util.Scanner;

public class BlackJack {
    private int playerMoves;
    private int dealerMoves;
    private int playerCount;
    private int dealerCount;
    private int dealerAces;
    private int playerAces;
    private CardDeck deck;
    private Scanner input;
    private int splitCount;
    private Boolean isSplit;


    public BlackJack(){
        input = new Scanner(System.in);
        deck = new CardDeck();
        playerCount = 0;
        dealerCount = 0;
        playerMoves = 0;
        dealerMoves = 0;
        dealerAces  = 0;
        playerAces  = 0;
        splitCount = 0;
        isSplit = false;
    }

    public static void main(String[] args){
        new BlackJack().play();
    }


    public void play(){
        String command = "";
        dealerDraw();
        dealerDraw();

        System.out.println();
        System.out.println("Dealers Card: ( " + deck.returnDealerCards() + " )");
        System.out.println();
        System.out.println(playerHit());
        System.out.println(playerHit());
        System.out.println("Your Count: ( " + playerCount + " )");
        System.out.println();

        if (playerCount == 21){
            System.out.println();
            System.out.println("( NATURAL BLACKJACK ) You WIN");
            System.out.println();
            return;
        }


        while(!command.equalsIgnoreCase("q")){  
            boolean splitAvailable = !isSplit && canSplit(); 
            if (splitAvailable){
                System.out.println("[h] = Hit, [s] = Stand, [/] = Split, [Q] = Quit");
            }
            else{
                System.out.println("[h] = Hit, [s] = Stand, [Q] = Quit");
            }
            System.out.print("Input: ");
            command = input.nextLine().toLowerCase();

            switch (command){
                case "h":

                    System.out.println();
                    System.out.println("Dealers Card: ( " + deck.returnDealerCards() + " ) [+ 1 hidden]");
                    System.out.println();
                    System.out.println(playerHit());
                    System.out.println("Your Count: ( " + playerCount + " )");
                    System.out.println();
                    
                    if (playerCount > 21){
                        System.out.println();
                        System.out.println("( BUST ) Dealer WINS");
                        System.out.println();
                        command = "q";
                    }
                    else if (playerCount == 21){
                        System.out.println();
                        System.out.println("( BLACKJACK ) You WIN");
                        System.out.println();
                        command = "q";
                    }
                    break;

                case "s":
                    stand();
                    System.out.println();
                    System.out.println("Dealers Cards: ( " + deck.returnDealerCards() + " )");
                    System.out.println("Dealers Count: ( " + dealerCount + " )");
                    System.out.println();
                    System.out.println("You ( STAND ) — Your Count: ( " + playerCount + " )");
                    System.out.println();
                    resolveHand(playerCount, "main hand");
 
                    if (isSplit){
                        System.out.println();
                        playSplitHand();
                    }
                    break;

                case "/":
                    if (!splitAvailable){
                        System.out.println("( Cannot Split — You Need Two cards That Are The Same )");
                        break;
                    }
                    split();
                    break;
                case "q":
                    break;

                default:
                    System.out.println();
                    System.out.println("( InValid Input )");
                    System.out.println();
            }
        }

        System.out.println();
        System.out.println("( Game ended )");
        System.out.println();
        input.close();
    }

    private static int rankValue(String card) {
        String rank = card.split(" ")[0];
        switch (rank) {
            case "2":     return 2;
            case "3":     return 3;
            case "4":     return 4;
            case "5":     return 5;
            case "6":     return 6;
            case "7":     return 7;
            case "8":     return 8;
            case "9":     return 9;
            case "10":    return 10;
            case "Jack":  return 10;
            case "Queen": return 10;
            case "King":  return 10;
            default:      return 0; 
        }
    }

    public String playerHit(){
        Random rand = new Random();
        int cardIndex = rand.nextInt(deck.deckSize());
        String outputCard = deck.getCard(cardIndex);
        deck.removeCard(cardIndex);

        if (outputCard.contains("Ace")){
            playerCount += 11;
            playerAces++;
        }
        else {
            playerCount += rankValue(outputCard);
        }

        if (playerCount > 21 && playerAces > 0 ){
            playerCount -= 10;
            playerAces--;
        }

        playerMoves++;
        deck.playerAppend(outputCard);
        return "Your Cards: ( " + deck.returnPlayerCards() + " )";
    }

    public void dealerDraw(){
        Random rand = new Random();
        int cardIndex = rand.nextInt(deck.deckSize());
        String outputCard = deck.getCard(cardIndex);
        deck.removeCard(cardIndex);

        if (outputCard.contains("Ace")){    
            dealerCount += 11;
            dealerAces++;
        }
        else {
            dealerCount += rankValue(outputCard);
        }

        while(dealerCount > 21 && dealerAces > 0){
            dealerCount -= 10;
            dealerAces--;
        }

        dealerMoves++;
        deck.dealerAppend(outputCard);
    }

    private void stand(){
        while (dealerCount < 17){
            dealerDraw();
        }
    }

    public void split(){
        String splitCard = deck.getPlayersCard(1);
        deck.removePlayerCard(splitCard);
        
        int deductValue = 0;
        if (splitCard.contains("Ace")){
            if (playerAces > 0){
                deductValue = 11;
                playerAces--;
            }
            else {
                deductValue = 1;
            }
        } 
        else {
            deductValue = rankValue(splitCard);
        }
        playerCount -= deductValue;
 
        deck.splitDeckAppend(splitCard);
        splitCount = deductValue;
        isSplit    = true;
 
        System.out.println();
        System.out.println("--- SPLIT ---");
        System.out.println("Dealing a card to your main hand...");
        System.out.println(playerHit());
        System.out.println("Your Count: ( " + playerCount + " )");
        System.out.println();
        System.out.println("Dealing a card to your split hand...");
        System.out.println(splitHit());
        System.out.println("Split Count: ( " + splitCount + " )");
        System.out.println();
    }

    public String splitHit(){
        Random rand   = new Random();
        int cardIndex = rand.nextInt(deck.deckSize());
        String card   = deck.getCard(cardIndex);
        deck.removeCard(cardIndex);
 
        if (card.startsWith("Ace")){
            if (splitCount + 11 <= 21){
                splitCount += 11;
            }
            else {
                splitCount += 1;
            }
        }
        else {
            splitCount += rankValue(card);
        }
 
        deck.splitDeckAppend(card);
        return "Split Hand: ( " + deck.returnSplitDeck() + " )";
    }

    private void playSplitHand(){
        System.out.println("=== Now playing your SPLIT hand ===");
        System.out.println("Split Hand:  ( " + deck.returnSplitDeck() + " )");
        System.out.println("Split Count: ( " + splitCount + " )");
        System.out.println();
 
        String command = "";
        while (!command.equals("q")){
            System.out.println("[h] = Hit, [s] = Stand");
            System.out.print("Input: ");
            command = input.nextLine().toLowerCase();
 
            switch (command){
                case "h":
                    System.out.println();
                    System.out.println(splitHit());
                    System.out.println("Split Count: ( " + splitCount + " )");
                    System.out.println();
 
                    if (splitCount > 21){
                        System.out.println("( SPLIT BUST ) Dealer WINS split hand");
                        System.out.println();
                        command = "q";
                    } else if (splitCount == 21){
                        System.out.println("( BLACKJACK on split hand ) You WIN");
                        System.out.println();
                        command = "q";
                    }
                    break;
 
                case "s":
                    System.out.println();
                    System.out.println("You ( STAND ) on split hand — Split Count: ( " + splitCount + " )");
                    System.out.println("Dealers Count: ( " + dealerCount + " )");
                    System.out.println();
                    resolveHand(splitCount, "split hand");
                    command = "q";
                    break;
 
                default:
                    System.out.println("( Invalid Input )");
            }
        }
    }

    private boolean canSplit(){
        if (deck.playerCardSize() != 2) return false;
        String rank1 = deck.getPlayersCard(0).split(" ")[0];
        String rank2 = deck.getPlayersCard(1).split(" ")[0];
        return rank1.equals(rank2);
    }

    private void resolveHand(int handCount, String label){
        if (dealerCount == 21 || (dealerCount > handCount && dealerCount <= 21)){
            System.out.println("( Dealer WINS " + label + " )");
        } else if (handCount == 21 || handCount > dealerCount || dealerCount > 21){
            System.out.println("( You WIN " + label + " )");
        } else {
            System.out.println("( TIE on " + label + " )");
        }
        System.out.println();
    }

}
