package baseball;

import camp.nextstep.edu.missionutils.Console;

public class Checker {

    private boolean[] visit; //유저입력에서의 중복을 체크하는 배열입니다.

    Checker() {
        visit = new boolean[10]; // 0,1,2,3,4,5,6,7,8,9
    }

    //User의 입력을 검사하고 유효하지 않으면 Illegal Exception을 발생시킵니다.
    public void userInputChecker(String userNumber, int length) {

        char[] userNumberToCharArray = userNumber.toCharArray();

        try {
            Integer.parseInt(userNumber);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자만 입력해주세요.");
        }

        if (userNumberToCharArray.length != length) {
            throw new IllegalArgumentException("올바른 길이의 숫자를 입력해주세요.");
        }

        for (int index = 0; index < length; index++) {
            if (userNumberToCharArray[index] - '0' == 0) {
                throw new IllegalArgumentException("1~9 사이의 숫자만 입력가능합니다.");
            }
        }

        //reOrEnd 입력 받을 때는 숫자중복을 검사하지않습니다.
        if (length != 1) {
            if (isUserInputSameNumber(userNumberToCharArray)) {
                throw new IllegalArgumentException("중복되는 숫자는 입력할 수 없습니다.");
            }
        }
    }

    //reOrEnd 입력을 받을 때 체크합니다.
    public int reOrEndUserInput() {

        String userInput = Console.readLine();

        try {
            userInputChecker(userInput, 1);

            int userInputToInt = Integer.parseInt(userInput);
            return userInputToInt;

        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("1혹은 2를 입력해주세요");
        }
    }

    //유저가 111, 222같은 중복되는 숫자 입력을 방지합니다.
    public boolean isUserInputSameNumber(char[] userNumberToCharArray) {

        int index = 0;
        initialVisitArr();

        for (int visitCheckIndex = 0; visitCheckIndex < visit.length; visitCheckIndex++) {
            int target = userNumberToCharArray[index] - '0';

            if (!visit[visitCheckIndex] && target == visitCheckIndex) {
                visit[visitCheckIndex] = true;
                index++;
                visitCheckIndex = -1;
            } else if (visit[visitCheckIndex] && target == visitCheckIndex) {
                return true;
            }

            if (index >= userNumberToCharArray.length) {
                initialVisitArr();
                break;
            }
        }
        return false;
    }

    public void initialVisitArr() {
        for (int index = 0; index < 10; index++) {
            visit[index] = false;
        }
    }
}


