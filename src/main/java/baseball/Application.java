package baseball;

public class Application {
    private static final int THREE_STRIKE = 3;

    public static void main(String[] args) {
        //TODO: 숫자 야구 게임 구현
        Computer computer = makeComputerEntity();
        Player player = makePlayerEntity();
        Checker checker = new Checker();
        startGame(computer, player, checker);

    }

    public static void startGame(Computer computer, Player player, Checker checker) {

        //게임 진행
        boolean isEnd = false;
        boolean re = true; // true일 때 게임종료
        do {
            initial(computer, checker);

            if (!re) {
                computer.getRandomNum();
                re = true;
            }

            //Player의 입력값
            isEnd = inOrOut(computer, player, checker);

            //strike가 3개라면 종료하고 재시작 혹은 종료여부를 결정합니다.
            if (computer.getStrike() == THREE_STRIKE) {
                isEnd = userGetThreeStrike(computer, checker);
                re = isEnd;
            }
        } while (!isEnd);

    }

    public static boolean userGetThreeStrike(Computer computer, Checker checker) {
        computer.showThreeStrike();
        //true면 종료함
        boolean isEnd = computer.reOrEnd(checker);

        return isEnd;
    }

    public static Computer makeComputerEntity() {
        Computer computer = new Computer();
        // 난수 세 개 입력받기 : 1~9의 값을 랜덤으로(중복을 허용하지 않고) 고릅니다.
        computer.getRandomNum();
        return computer;
    }

    public static Player makePlayerEntity() {
        Player player = new Player();
        return player;
    }

    //반복문 탈출
    public static boolean inOrOut(Computer computer, Player player, Checker checker) {

        if (player.userGuessNumber(checker)) {
            return true;
        } else {
            //strike와 ball을 계산합니다.
            computer.calculateStrikeAndBall(player);
            //결과를 프린트합니다.
            computer.printResult(computer.getBall(), computer.getStrike());

            return false;
        }
    }

    public static void initial(Computer computer, Checker checker) {
        computer.setBall(0);
        computer.setStrike(0);
        //checker 갱신
        checker = new Checker();
    }

}
