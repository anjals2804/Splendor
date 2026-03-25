public class TakeGems extends Action {
    private int[] gemsToTake; 
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
                if (i == 5) {
                    return false;
                }
                totalTaken += count;
                colorsTaken++;

                if (count == 2) {
                    takingTwoSame = true;
                    if (!board.getGemBank().canTakeTwo(i)) {
                        return false;
                    }
                } else if (count > 2) {
                    return false; 
                } else if (count == 1) {
                    if (board.getGemBank().getSupply()[i] < 1) {
                        return false;
                    }
                }
            }
        }

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
                player.getWealth().addToken(i, gemsToTake[i]);

                int[] supply = board.getGemBank().getSupply();
                supply[i] -= gemsToTake[i];
            }
        }
    }
}
