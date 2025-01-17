package baseball;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.ArrayList;

public class Computer {

    private int strike;
    private int ball;
    private boolean[] visit; //3개의 수는 중복될 수 없으므로 방문여부를 확인하기위한 배열 선언입니다.
    private ArrayList<Integer> randomNumber; //난수를 저장하는 list입니다.
    private static final String THREE_STRIKE_MESSAGE = "3개의 숫자를 모두 맞히셨습니다! 게임 종료";
    private static final String RE_OR_ENDS_MESSAGE = "게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.";
    private static final int RE_PLAY = 1;
    private static final int END_GAME = 2;
    private static final int THREE_STRIKE = 3;

    public Computer() {
        strike = 0;
        ball = 0;
        visit = new boolean[10];
        randomNumber = new ArrayList<Integer>(3);
    }

    //검사작업을 시작합니다.
    public void calculateStrikeAndBall(Player player) {
        int index = 0;
        for (Integer i : player.userNumberList) {
            if (randomNumber.get(index).equals(i)) {
                strike++;
            } else if (player.getUserNumberList().contains(randomNumber.get(index))) {
                ball++;
            }
            index++;
        }
    }

    public void showThreeStrike() {
        System.out.println(THREE_STRIKE_MESSAGE);
        System.out.println(RE_OR_ENDS_MESSAGE);
    }

    public boolean reOrEnd(Checker checker) {
        int userInputToInt;

        userInputToInt = checker.reOrEndUserInput();
        if (userInputToInt == RE_PLAY) {
            return false;
        } else if (userInputToInt == END_GAME) {
            return true;
        }
        return false;
    }

    public void printResult() {
        String str = "";

        if (ball != 0) {
            str += ball + "볼 ";
        }
        if (strike != 0) {
            str += strike + "스트라이크";
        }
        if (ball == 0 && strike == 0) {
            str += "낫싱";
        }

        System.out.println(str);
    }

    public void getRandomNum() {
        while (!randomNumber.isEmpty()) {
            randomNumber.remove(0);
            initialVisitArr();
        }

        for (int index = 0; index < 3; index++) {
            int number = Randoms.pickNumberInRange(1, 9);
            if (!visit[number]) {
                visit[number] = true;
                randomNumber.add(number);
            } else {
                index--;
            }
        }
    }

    public void initialVisitArr() {
        for (int index = 0; index < visit.length; index++) {
            visit[index] = false;
        }
    }

    public void resetStatus() {
        this.ball = 0;
        this.strike = 0;
    }

    public boolean isThreeStrike() {
        return (this.strike == THREE_STRIKE);
    }

}
