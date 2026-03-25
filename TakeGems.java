public class TakeGems extends Action {
    private int[] gemsToTake; // Array where indices correspond to GemColor enum ordinals

    public TakeGems(int[] gemsToTake) {
        this.gemsToTake = gemsToTake;
    }

    @Override
    public boolean isValid(Player player, Board board) {
        int totalTaken = 0;
        int colorsTaken = 0;
        boolean takingTwoSame = false;

        for (int i = 0; i < gemsToTake.length; i++) {
            int count = gemsToTake[i];
            if (count > 0) {
                // Cannot take gold tokens this way (assuming Gold is index 5)
                if (i == 5) {
                    return false;
                }
                totalTaken += count;
                colorsTaken++;

                if (count == 2) {
                    takingTwoSame = true;
                    // GemPile has canTakeTwo checking if pile >= 4
                    if (!board.getGemBank().canTakeTwo(i)) {
                        return false;
                    }
                } else if (count > 2) {
                    return false; // Cannot take more than 2 of a single color
                } else if (count == 1) {
                    // Check if there is at least 1 in supply
                    if (board.getGemBank().getSupply()[i] < 1) {
                        return false;
                    }
                }
            }
        }

        // Validity rules:
        // Either 3 different colors (or less if the board is empty)
        // Or exactly 2 of the same color
        if (takingTwoSame) {
            return totalTaken == 2 && colorsTaken == 1;
        } else {
            return totalTaken > 0 && totalTaken <= 3 && colorsTaken == totalTaken;
        }
    }

    @Override
    public void takeAction(Player player, Board board) {
        for (int i = 0; i < gemsToTake.length; i++) {
            if (gemsToTake[i] > 0) {
                // PlayerAssets has addToken method
                player.getWealth().addToken(i, gemsToTake[i]);
                
                // Remove from board supply
                int[] supply = board.getGemBank().getSupply();
                supply[i] -= gemsToTake[i];
            }
        }
    }
}
