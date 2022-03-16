package baseball;

import camp.nextstep.edu.missionutils.Console;

import java.util.ArrayList;

public class Player {

    ArrayList<Integer> userNumberList;
    public static final int ONE_HUNDRED = 100;
    public static final int TEN = 10;
    public static final int ZERO = 0;
    public static final String USER_INPUT_GUIDE_MESSAGE = "숫자를 입력해주세요 : ";

    public Player() {
        userNumberList = new ArrayList<Integer>(3);
    }

    public boolean userGuessNumber(Checker checker) {
        System.out.print(USER_INPUT_GUIDE_MESSAGE);
        String userNumber = Console.readLine();

        ifListIsNotEmpty(checker);
        checker.userInputChecker(userNumber, 3);

        int userNumberToInt = Integer.parseInt(userNumber);

        userNumberList.add(userNumberToInt / ONE_HUNDRED);
        userNumberList.add((userNumberToInt % ONE_HUNDRED) / TEN);
        userNumberList.add((userNumberToInt % ONE_HUNDRED) % TEN);

        return false;
    }

    public void ifListIsNotEmpty(Checker checker) {
        boolean flag = false;
        while (!userNumberList.isEmpty()) {
            userNumberList.remove(ZERO);
            flag = true;
        }
        if (flag) {
            checker.initialVisitArr();
        }
    }

    public ArrayList<Integer> getUserNumberList() {
        return userNumberList;
    }
}
