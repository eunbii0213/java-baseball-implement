package baseball;

import camp.nextstep.edu.missionutils.Console;

public class Checker {

    private boolean[] visit;
    Checker(){
        visit = new boolean[10]; // 0,1,2,3,4,5,6,7,8,9
    }

    //User의 입력을 검사하고 유효하지 않으면 Illegal Exception을 발생시킵니다.
    public void userInputChecker(String userNumber, int length) throws IllegalArgumentException {

        char[] userNumberToCharArray = userNumber.toCharArray();
        if (userNumberToCharArray.length != length) {
            throw new IllegalArgumentException();
        }

        if(isUserInputSameNumber(userNumberToCharArray, length)){
            throw new IllegalArgumentException();
        }

        try{
            Integer.parseInt(userNumber);
        }catch(NumberFormatException e){
            throw new IllegalArgumentException();
        }

    }

    //reOrEnd 입력을 받을 때 체크합니다.
    public int reOrEndUserInput(Player player) throws IllegalArgumentException {

        String userInput = Console.readLine();

        try {
            userInputChecker(userInput, 1);
            int userInputToInt = Integer.parseInt(userInput);
            return userInputToInt;

        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException();
        }

    }

    //유저가 111, 222같은 중복되는 숫자 입력을 방지합니다.
    public boolean isUserInputSameNumber(char[] userNumberToCharArray, int length){

            int index=0;
            for(int j=1;j<visit.length;j++){
                int target = userNumberToCharArray[index]-'0';

                if(!visit[j] && target == j){
                    visit[j]= true;
                    index++;
                    j=1;
                } else if(visit[j]&& target == j){
                    return true;
                }

                if(index>=3){
                    break;
                }
            }

        return false;
    }
}


