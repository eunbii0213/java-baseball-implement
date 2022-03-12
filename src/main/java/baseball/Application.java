package baseball;

public class Application {
    private static final int THREESTRIKE = 3;

    public static void main(String[] args) {
        //TODO: 숫자 야구 게임 구현
        startGame();
    }

    public static void startGame() {

        Computer computer = new Computer();
        // 난수 세 개 입력받기 : 1~9의 값을 랜덤으로(중복을 허용하지 않고) 고릅니다.
        computer.getRandomNum();

        //게임 진행
        Loop1:
        while (true) {
            Player player = new Player();
            Checker checker = new Checker();
            boolean reOrEnd = false;
            computer.setBall(0);
            computer.setStrike(0);

            //Player의 입력값

            if (player.userGuessNumber(checker)) {
                break;

            } else if (computer.calculateStrikeAndBall(player)) {
                //strike와 ball을 계산합니다.
               break;
            } else {
                //결과를 프린트합니다.
                computer.printResult(computer.getBall(), computer.getStrike());
            }

            //strike가 3개라면 종료하고 재시작 혹은 종료여부를 결정합니다.
            if (computer.getStrike() == THREESTRIKE) {
                computer.showThreeStrike();
                reOrEnd = computer.reOrEnd(player, checker);

            }

            if(reOrEnd){
                break;

            }

        }

        System.exit(0);


    }

}
