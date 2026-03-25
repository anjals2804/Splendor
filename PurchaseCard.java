public class PurchaseCard extends Action {
    private DevelopmentCard card;

    public PurchaseCard(DevelopmentCard card) {
        this.card = card;
    }

    @Override
    public boolean isValid(Player player, Board board) {
        int[] cost = card.getCost();
        int goldRequired = player.getWealth().goldNeeded(cost);
        
        int playerGold = player.getWealth().getTokens()[5];
    }

    @Override
    public void takeAction(Player player, Board board) {

        player.addPoints(card.getPrestigePoints()); 
        
        player.getWealth().addBonus(card.getBonusColor()); 
    }
}
