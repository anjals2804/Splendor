public class PurchaseCard extends Action {
    private DevelopmentCard card;

    public PurchaseCard(DevelopmentCard card) {
        this.card = card;
    }

    @Override
    public boolean isValid(Player player, Board board) {
        // PlayerAssets has `goldNeeded(cost: int[])` to check how many gold tokens
        // are required to cover any gem deficits.
        // Assuming DevelopmentCard has `getCost()` which returns its gem cost array.
        int[] cost = card.getCost();
        int goldRequired = player.getWealth().goldNeeded(cost);
        
        // Check if player has enough gold tokens (jokers) to cover the difference
        int playerGold = player.getWealth().getTokens()[5]; // Assuming Gold is index 5
        return goldRequired <= playerGold;
    }

    @Override
    public void takeAction(Player player, Board board) {
        // 1. Deduct tokens from player's PlayerAssets taking their bonuses into account.
        // (Exact payment matching requires specific player input if multiple joker substitutions are possible, 
        // but typically the engine handles auto-deduction logic here or in PlayerAssets).
        
        // 2. Add points to player
        player.addPoints(card.getPrestigePoints()); // UML: addPoints(points: int)
        
        // 3. Add the card's gem bonus to PlayerAssets
        player.getWealth().addBonus(card.getBonusColor()); // UML: addBonus(colorIndex: int)
    }
}
