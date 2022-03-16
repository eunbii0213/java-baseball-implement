package baseball;

import camp.nextstep.edu.missionutils.Console;

import java.util.ArrayList;

public class Checker {
    private ArrayList<Boolean> visit;
    public static final int ZERO = 0;
    public static final int ONE = 1;
    public static final int TEN = 10;
    public static final char CHAR_TO_INT ='0';

    Checker() {
        visit = new ArrayList<>(TEN);
    }

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

        for (int index = ZERO; index < length; index++) {
            if (userNumberToCharArray[index] - CHAR_TO_INT == ZERO) {
                throw new IllegalArgumentException("1~9 사이의 숫자만 입력가능합니다.");
            }
        }

        if (length != ONE) {
            if (isUserInputSameNumber(userNumberToCharArray)) {
                throw new IllegalArgumentException("중복되는 숫자는 입력할 수 없습니다.");
            }
        }
    }

    public int reOrEndUserInput() {
        String userInput = Console.readLine();

        try {
            userInputChecker(userInput, 1);
            return Integer.parseInt(userInput);

        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("1혹은 2를 입력해주세요");
        }
    }

    public boolean isUserInputSameNumber(char[] userNumberToCharArray) {
        int index = ZERO;
        initialVisitArr();

        for (int visitCheckIndex = ZERO; visitCheckIndex < visit.size(); visitCheckIndex++) {
            int target = userNumberToCharArray[index] - CHAR_TO_INT;

            if (!visit.get(visitCheckIndex) && target == visitCheckIndex) {
                visit.set(visitCheckIndex, true);
                index++;
                visitCheckIndex = -ONE;
            } else if (visit.get(visitCheckIndex) && target == visitCheckIndex) {
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
        int index = ZERO;
        for (boolean isTrue : visit) {
            visit.set(index, false);
            index++;
        }
    }
}


