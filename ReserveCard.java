package com.splendor.core;
public class ReserveCard extends Action {
    private DevelopmentCard card;

    public ReserveCard(DevelopmentCard card) {
        this.card = card;
    }

    @Override
    public boolean isValid(Player player, Board board) {
        // Player can reserve at most 3 cards. UML shows reservedCards as a List.
        return player.getReservedCards().size() < 3;
    }

    @Override
    public void takeAction(Player player, Board board) {
        // Add to player's reserved list
        player.reserve(card);
        
        // In Splendor, reserving gives 1 Gold token (joker) if any are available.
        int[] supply = board.getGemBank().getSupply();
        if (supply[5] > 0) { // Assuming index 5 corresponds to Gold
            player.getWealth().addToken(5, 1);
            supply[5]--;
        }
    }
}
