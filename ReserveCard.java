public class ReserveCard extends Action {
    private DevelopmentCard card;

    public ReserveCard(DevelopmentCard card) {
        this.card = card;
    }

    @Override
    public boolean isValid(Player player, Board board) {
        return player.getReservedCards().size() < 3;
    }

    @Override
    public void takeAction(Player player, Board board) {
        player.reserve(card);
        
        int[] supply = board.getGemBank().getSupply();
        if (supply[5] > 0) { 
            player.getWealth().addToken(5, 1);
            supply[5]--;
        }
    }
}
