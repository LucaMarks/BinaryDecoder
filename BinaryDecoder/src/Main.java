public class Main{

    /*
    given an input from 26 digit binary & a pos eq
    decode

    */
    public static void main(String[] args){

        String string = "AB'+C";
        char[] charString = string.toCharArray();
        String[] str = string.split("\\+");

        for(int i = 0; i < str.length; i++){
            System.out.print(str[i] + ", ");
        }
    }

    //need to return an unkown amount of lists
    public char[][] split(char[] string, char character){

        char[][] splitList = new char[string.length][];

        //counts how many times the list gets split
        int charIndex = 0;

        for(int i = 0; i < string.length; i++){
            if(string[i] == character){
                //split the list atp

            }
        }
        return null;
    }

    //return a list where we chop of everything after the given index
    private char[] breakList(char[] string, int index){

        char[] brokenString = new char[index];

        for(int i = 0; i < index; i++){

        }
        return null;
    }

}
