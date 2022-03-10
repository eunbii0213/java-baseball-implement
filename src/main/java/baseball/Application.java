package baseball;

import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;

import java.util.ArrayList;


public class Application {
    static boolean[] visit; //3개의 수는 중복될 수 없으므로 방문여부를 확인하기위한 배열 선언입니다.
    static ArrayList<Integer> randomNumber; //난수를 저장하는 list입니다.
    static int strike, ball;

    public static void main(String[] args) {
        //TODO: 숫자 야구 게임 구현
        startGame();
    }

    //User가 숫자를 입력합니다.
    public static ArrayList UserGuessNumber() throws IllegalArgumentException {

        System.out.print("숫자를 입력해주세요 : ");
        String userNumber = Console.readLine();

        try {
            boolean checker = UserInputChecker(userNumber, 3);
        } catch (IllegalArgumentException e) {
            //System.out.println(e.toString());
            System.exit(0);
        }

        int userNumberToInt = Integer.parseInt(userNumber);

        ArrayList<Integer> userNumberList = new ArrayList<Integer>(3);

        //입력받은 수를 백의자리, 십의자리, 일의자리로 나누어 저장합니다.
        userNumberList.add(userNumberToInt / 100);
        userNumberList.add((userNumberToInt % 100) / 10);
        userNumberList.add((userNumberToInt % 100) % 10);

        return userNumberList;

    }

    //User의 입력을 검사하고 유효하지 않으면 Illegal Exception을 발생시킵니다.
    public static boolean UserInputChecker(String userNumber, int length) throws IllegalArgumentException {

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

    public static void startGame() {
        // 난수 세 개 입력받기 : 1~9의 값을 랜덤으로(중복을 허용하지 않고) 고릅니다.
        randomNumber = new ArrayList<Integer>(3);
        visit = new boolean[10];

        getRandomNum(); //난수를 입력받습니다.
//        for (int i = 0; i < 3; i++) {
//            System.out.println(randomNumber.get(i));
//        }

        //게임 진행
        while (true) {
            strike = 0;
            ball = 0;

            //User의 입력값
            ArrayList<Integer> userNumberList = UserGuessNumber();

            //strike와 ball을 계산합니다.
            CalStrikeAndBall(userNumberList);
            //결과를 프린트합니다.
            printResult(ball, strike);

            if (strike == 3) {
                ReOrEnd();
            }

            ball = 0;
            strike = 0;
        }

    }


    public static void CalStrikeAndBall(ArrayList<Integer> userNumberList) {
        //검사작업을 시작합니다.
        for (int i = 0; i < 3; i++) {
            if (randomNumber.get(i).equals(userNumberList.get(i))) {
                strike++;
            } else if (userNumberList.contains(randomNumber.get(i))) {
                ball++;
            }
        }

    }

    public static void ReOrEnd() throws IllegalArgumentException {

        System.out.println("3개의 숫자를 모두 맞히셨습니다! 게임 종료");
        System.out.println("게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.");
        String userInput = Console.readLine();

        try {
            boolean checker = UserInputChecker(userInput, 1);
        } catch (IllegalArgumentException e) {
            //System.out.println(e.toString());
            System.exit(0);
        }
        int userInputToInt = Integer.parseInt(userInput);

        if (userInputToInt == 1) {
            startGame();
        } else if (userInputToInt == 2) {
            System.exit(0);
        }

    }


    public static void printResult(int ball, int strike) {

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
    public static void getRandomNum() {

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
}
