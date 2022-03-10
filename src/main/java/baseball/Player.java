package baseball;

import camp.nextstep.edu.missionutils.Console;

import java.util.ArrayList;

public class Player {

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
