package baseball;

import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;

import java.util.ArrayList;

class Player {

    ArrayList<Integer> userNumberList;

    public Player() {
        userNumberList = new ArrayList<Integer>(3);
    }

    //User가 숫자를 입력합니다.
    public ArrayList UserGuessNumber() throws IllegalArgumentException {

        System.out.print("숫자를 입력해주세요 : ");
        String userNumber = Console.readLine();

        try {
            boolean checker = UserInputChecker(userNumber, 3);
        } catch (IllegalArgumentException e) {
            //System.out.println(e.toString());
            System.exit(0);
        }

        int userNumberToInt = Integer.parseInt(userNumber);

        //입력받은 수를 백의자리, 십의자리, 일의자리로 나누어 저장합니다.
        userNumberList.add(userNumberToInt / 100);
        userNumberList.add((userNumberToInt % 100) / 10);
        userNumberList.add((userNumberToInt % 100) % 10);

        return userNumberList;

    }

    //User의 입력을 검사하고 유효하지 않으면 Illegal Exception을 발생시킵니다.
    public boolean UserInputChecker(String userNumber, int length) throws IllegalArgumentException {

        char[] userNumberToCharArray = userNumber.toCharArray();
        if (userNumberToCharArray.length > length || userNumberToCharArray.length < length) {
            throw new IllegalArgumentException();
        }

        //아스키코드표 기준 0 = 48, 9 = 57. 이 범위안에 있지 않다면 유효한 값이 아니라고 판단합니다.
        for (int i = 0; i < length; i++) {
            if (userNumberToCharArray[i] < 48 || userNumberToCharArray[i] > 57) {
                throw new IllegalArgumentException();
            }
        }
        return true;

    }

    public ArrayList<Integer> getUserNumberList() {
        return userNumberList;
    }

    public void setUserNumberList(ArrayList<Integer> userNumberList) {
        this.userNumberList = userNumberList;
    }
}

class Computer {
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
