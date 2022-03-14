package baseball;

import camp.nextstep.edu.missionutils.Console;

import java.util.ArrayList;

public class Player {

    ArrayList<Integer> userNumberList;

    public Player() {
        userNumberList = new ArrayList<Integer>(3);
    }

    //User가 숫자를 입력합니다.
    public boolean userGuessNumber(Checker checker) throws IllegalArgumentException {
        System.out.print("숫자를 입력해주세요 : ");
        String userNumber = Console.readLine();

        try {
            ifListIsNotEmpty(checker);

            checker.userInputChecker(userNumber, 3);

            int userNumberToInt = Integer.parseInt(userNumber);

            //입력받은 수를 백의자리, 십의자리, 일의자리로 나누어 저장합니다.
            userNumberList.add(userNumberToInt / 100);
            userNumberList.add((userNumberToInt % 100) / 10);
            userNumberList.add((userNumberToInt % 100) % 10);

        } catch (IllegalArgumentException e) {

            return true;
        }

        return false;

    }

    public void ifListIsNotEmpty(Checker checker) {
        //System.out.println("ggggggggggggggg");

        boolean flag = false;
        while (!userNumberList.isEmpty()) {
            userNumberList.remove(0);
            flag = true;
        }
        if (flag) {
            //System.out.println("hhhhhhhhhhhhhhh");
            checker.initVisitArr();
        }
    }


    public ArrayList<Integer> getUserNumberList() {
        return userNumberList;
    }

}
