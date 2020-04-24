package tictactoe;

import java.util.Scanner;

public class Main {
    private String state=null;
    private int n=3;
    int[][] xArr, oArr, eArr;

    /*public Main () {

        System.out.println("Enter cells: " );
        //Scanner scanner = new Scanner(System.in);
        //this.state = scanner.nextLine();
        this.state = "xxx";
        n = (int) Math.sqrt(this.state.length());
        setArr(this.state);
    }
*/

    public int sum (int[][] arr,int iStart,int iEnd,int jStart,int jEnd) {
        int sumElement=0;
        if ((iStart == iEnd )&&(jStart < jEnd))
        for (int i = iStart; i <= iEnd; i++) {
            for (int j = jStart; j <= jEnd; j++) {
                if (arr[i][j] == 1) sumElement +=1;
            }
        }
        if ((iStart < iEnd )&&(jStart == jEnd ))
            for (int i = iStart; i <= iEnd; i++) {
                for (int j = jStart; j <= jEnd; j++) {
                    if (arr[i][j] == 1) sumElement +=1;
                }
            }
        if ((iStart == jStart )&&(iEnd == jEnd ))
            for (int i = iStart; i <= iEnd; i++) {
                if (arr[i][i] == 1) sumElement +=1;
             }
        if ((iStart == this.n-1 )&&(iEnd == 0)&&(jStart == 0)&&(jEnd == this.n-1))
            for (int i = iStart, j=jStart; i >= iEnd; i--, j++) {

                    if (arr[i][j] == 1)
                        sumElement += 1;

            }
        return  sumElement;
    }

    public void setArr (String s) {
        xArr = new int[n][n];
        oArr = new int[n][n];
        eArr = new int[n][n];
        int pos=0;
        for (int i = 0; i < n ; i++) {
            for (int j = 0; j < n ; j++,pos++) {
                if (s.charAt(pos) == 'X') xArr[i][j] = 1;
                if (s.charAt(pos) == 'O') oArr[i][j] = 1;
                if ((s.charAt(pos) == '_') || (s.charAt(i+j) == ' ')) eArr[i][j] = 1;
            }
        }
    }

        public boolean isSideHasAThreeInARow (int[][] arr){
            boolean is, isFirstRowHasThree,isSecondRowHasThree,isLastRowHasThree,isFirstColHasThree,isSecondColHasThree,isLastColHasThree,isMainDiagHasThree,isSecondDiagThree;
            isMainDiagHasThree = (sum(arr,0,n-1,0,n-1)==n);
            isSecondDiagThree = (sum(arr,n-1,0,0,n-1)==n);
            isFirstRowHasThree = (sum(arr,0,0,0,n-1)==n);
            isSecondRowHasThree = (sum(arr,1,1,0,n-1)==n);
            isLastRowHasThree = (sum(arr,n-1,n-1,0,n-1)==n);
            isFirstColHasThree = (sum(arr,0,n-1,0,0)==n);
            isSecondColHasThree = (sum(arr,0,n-1,1,1)==n);
            isLastColHasThree =  (sum(arr,0,n-1,n-1,n-1)==n);
                if (isMainDiagHasThree || isSecondDiagThree || isFirstRowHasThree || isSecondRowHasThree || isLastRowHasThree || isFirstColHasThree || isSecondColHasThree || isLastColHasThree)
            return is = true;
                else return is = false;
        }
    public boolean xWins (){
        if (this.isSideHasAThreeInARow(this.xArr)) return true;
            else return false;
    }

    public boolean oWins (){
        if (this.isSideHasAThreeInARow(this.oArr)) return true;
        else return false;
    }

    public boolean draw (){
        if ((!((this.isSideHasAThreeInARow(this.xArr)||this.isSideHasAThreeInARow(this.oArr))))&&(!isTheFieldHasEmptyCells())) return true;
        else return false;
    }

    public boolean gameNotFinished (){
        if ((!((this.isSideHasAThreeInARow(this.xArr)||this.isSideHasAThreeInARow(this.oArr))))&&(isTheFieldHasEmptyCells())) return true;
        else return false;
    }

    public boolean impossible (){
        boolean diffNorm, impossible;
        int diff1,diff2;
        diff1 = (sumField(xArr)-sumField(oArr));
        diff2 = (sumField(oArr)-sumField(xArr));
        if (((diff1==0)||(diff1 == 1)) || ((diff2==0)||(diff2 == 1))) diffNorm = true;
        else diffNorm = false;
        impossible = ((xWins() && oWins())) || (!(diffNorm));
        if (impossible) return true;
        else return false;
    }

    public boolean isTheFieldHasEmptyCells (){
    if (sumField(eArr)>0)
        return true;
    else
        return  false;
    }

    public int sumField (int[][] arr) {
        int sum=0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                sum = sum + arr[i][j];
            }
        }
        return sum;
    }

    public void printGame (Main game) {
        for(int i = 0; i < 9; i++){
            System.out.print('-');
        }
        System.out.println();
        String s="";

        for (int i = 0; i < n; i++) {
            System.out.print("|");
            for (int j = 0; j < n; j++) {
                if ((xArr[i][j])==1) s="X";
                if ((oArr[i][j])==1) s="O";
                if ((eArr[i][j])==1) s="_";
                System.out.print(" "+s);

            }
            System.out.println(" |");
        }
            for(int i = 0; i < 9; i++){
            System.out.print('-');
        }
        System.out.println();
    }

    public String printStateOfGame (Main game) {
        String state = null;
     if (game.gameNotFinished()) state = "Game not finished";
     if (game.draw()) state = "Draw";
     if (game.xWins()) state = "X wins";
     if (game.oWins()) state = "O wins";
     if (game.impossible()) state =  "Impossible";
        return  state;
    }

    public void readAndCheckCoordinates (Main game,String s) {
        //System.out.println();
        //System.out.println("Enter the coordinates:");
        Scanner scanner = new Scanner(System.in);
        int number1=0;
        int number2=0;
        do {
            do {
                System.out.println("Enter the coordinates:");
                String input1 = scanner.next();
                String input2 = scanner.next();
                number1 = 0;
                number2 = 0;
                if ((input1.matches("\\d")) && (input2.matches("\\d"))) {
                    number1 = Integer.parseInt(input1);
                    number2 = Integer.parseInt(input2);
                    if ((((number1 < 1) || (number1 > n)) || ((number2 < 1) || (number2 > n)))) {
                        System.out.println("Coordinates should be from 1 to 3!");
                    }
                } else {
                    System.out.println("You should enter numbers!");
                }

            }
            while ((((number1 < 1) || (number1 > n)) || ((number2 < 1) || (number2 > n))));
            if (game.isCellFree(number1, number2)) {
                game.setCell(number1, number2, s);break;
            }
            else System.out.println("This cell is occupied! Choose another one!");
        }
            while (!isCellFree(number1,number2));
    }
    public void setCell(int x, int y, String s){
        int i=n-y;
        int j=x-1;
        if ((s.equals("X"))) xArr[i][j]=1;
        if ((s.equals("O"))) oArr[i][j]=1;
        eArr[i][j]=0;
    }

    public boolean isCellFree(int x, int y){ //the first coordinate goes from left to right and the second coordinate goes from bottom to top
        int i=n-y;
        int j=x-1;
        boolean state=false;
        if (eArr[i][j]==1) state = true;
        if (eArr[i][j]==0) state = false;
        return state;
    }



    public static void main(String[] args) {
        //String[] strState = new String[] {"XXXOO__O_","XOXOXOXXO","XOOOXOXXO","XOXOOXXXO","XO_OOX_X_","XO_XO_XOX","_O_X__X_X"};
        Main game = new Main();
        /*
        System.out.println("Enter cells: " );
        Scanner scanner = new Scanner(System.in);
        game.state =scanner.nextLine();
        */
        game.setArr("_________");
        game.printGame(game);
        do {
            game.readAndCheckCoordinates(game, "X");
            if (game.printStateOfGame(game).equals("X wins")||game.printStateOfGame(game).equals("O wins")||game.printStateOfGame(game).equals("Draw")){
                break;
            }
            game.printGame(game);
            game.readAndCheckCoordinates(game, "O");
            if (game.printStateOfGame(game).equals("X wins")||game.printStateOfGame(game).equals("O wins")||game.printStateOfGame(game).equals("Draw")){
                break;
            }
            game.printGame(game);
        } while (true);
        game.printGame(game);
        System.out.println((game.printStateOfGame(game)));

    }
}
