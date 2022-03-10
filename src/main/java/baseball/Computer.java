package baseball;

import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;

import java.util.ArrayList;

public class Computer {
    private int strike;
    private int ball;
    private boolean[] visit; //3개의 수는 중복될 수 없으므로 방문여부를 확인하기위한 배열 선언입니다.
    private ArrayList<Integer> randomNumber; //난수를 저장하는 list입니다.

    public Computer() {
        strike = 0;
        ball = 0;
        visit = new boolean[10];
        randomNumber = new ArrayList<Integer>(3);
    }

    //검사작업을 시작합니다.
    public void CalStrikeAndBall(Player player) {
        for (int i = 0; i < 3; i++) {

            if (randomNumber.get(i).equals(player.getUserNumberList().get(i))) {
                setStrike(getStrike() + 1);
            } else if (player.getUserNumberList().contains(randomNumber.get(i))) {
                setBall(getBall() + 1);
            }
        }

    }

    public void ReOrEnd(Player player) throws IllegalArgumentException {

        System.out.println("3개의 숫자를 모두 맞히셨습니다! 게임 종료");
        System.out.println("게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.");
        String userInput = Console.readLine();

        try {

            boolean checker = player.UserInputChecker(userInput, 1);
        } catch (IllegalArgumentException e) {
            //System.out.println(e.toString());
            System.exit(0);
        }
        int userInputToInt = Integer.parseInt(userInput);

        if (userInputToInt == 1) {
            Application.startGame();
        } else if (userInputToInt == 2) {
            System.exit(0);
        }

    }


    public void printResult(int ball, int strike) {

        if (ball != 0 && strike != 0) {
            System.out.println(ball + "볼" + " " + strike + "스트라이크");
        } else if (ball == 0 && strike != 0) {
            System.out.println(strike + "스트라이크");
        } else if (ball != 0 && strike == 0) {
            System.out.println(ball + "볼");
        } else {
            System.out.println("낫싱");
        }
    }


    //컴퓨터가 난수입력받음(숫자가 중복되지 않기위해 visit여부를 체크하며 넣는다)
    public void getRandomNum() {

        for (int i = 0; i < 3; i++) {
            int number = Randoms.pickNumberInRange(1, 9);
            if (!visit[number]) {
                visit[number] = true;
                randomNumber.add(number);
            } else {
                i--;
            }
        }
    }

    public int getStrike() {
        return strike;
    }

    public void setStrike(int strike) {
        this.strike = strike;
    }

    public int getBall() {
        return ball;
    }

    public void setBall(int ball) {
        this.ball = ball;
    }

    public boolean[] getVisit() {
        return visit;
    }

    public void setVisit(boolean[] visit) {
        this.visit = visit;
    }

    public ArrayList<Integer> getRandomNumber() {
        return randomNumber;
    }

    public void setRandomNumber(ArrayList<Integer> randomNumber) {
        this.randomNumber = randomNumber;
    }
}
