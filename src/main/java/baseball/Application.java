package baseball;

public class Application {

    public static void main(String[] args) {
        //TODO: 숫자 야구 게임 구현
        Computer computer = makeComputerEntity();
        Player player = makePlayerEntity();
        Checker checker = new Checker();
        startGame(computer, player, checker);
    }

    public static void startGame(Computer computer, Player player, Checker checker) {
        boolean isEnd = false;
        boolean reStrat = true; // true일 때 게임종료
        do {
            initial(computer);

            if (!reStrat) {
                computer.getRandomNum();
                reStrat = true;
            }

            isEnd = inOrOut(computer, player, checker);

            if (computer.isThreeStrike()) {
                isEnd = userGetThreeStrike(computer, checker);
                reStrat = isEnd;
            }
        } while (!isEnd);
    }

    public static boolean userGetThreeStrike(Computer computer, Checker checker) {
        computer.showThreeStrike();
        return computer.reOrEnd(checker);
    }

    public static Computer makeComputerEntity() {
        Computer computer = new Computer();
        computer.getRandomNum();
        return computer;
    }

    public static Player makePlayerEntity() {
        return new Player();
    }

    public static boolean inOrOut(Computer computer, Player player, Checker checker) {
        if (player.userGuessNumber(checker)) {
            return true;
        } else {
            computer.calculateStrikeAndBall(player);
            computer.printResult();
            return false;
        }
    }

    public static void initial(Computer computer) {
        computer.resetStatus();
    }
}
