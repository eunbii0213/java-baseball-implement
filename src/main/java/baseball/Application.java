package baseball;

import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;

import java.util.ArrayList;

public class Application {


    public static void main(String[] args) {
        //TODO: 숫자 야구 게임 구현
        startGame();
    }

    public static void startGame() {

        Computer computer = new Computer();
        // 난수 세 개 입력받기 : 1~9의 값을 랜덤으로(중복을 허용하지 않고) 고릅니다.
        computer.getRandomNum();

        //게임 진행
        while (true) {

            Player player = new Player();

            //Player의 입력값
            player.UserGuessNumber();

            //strike와 ball을 계산합니다.
            computer.CalStrikeAndBall(player);
            //결과를 프린트합니다.
            computer.printResult(computer.getBall(), computer.getStrike());

            //strike가 3개라면 종료하고 재시작 혹은 종료여부를 결정합니다.
            if (computer.getStrike() == 3) {
                computer.ReOrEnd(player);
            }

            //Player가 숫자를 맞추지 못했다면 ball과 strike를 초기화합니다.
            computer.setBall(0);
            computer.setStrike(0);
        }

    }

}
