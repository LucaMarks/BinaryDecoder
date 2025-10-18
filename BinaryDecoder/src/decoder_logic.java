public class decoder_logic {



    /*
     * method to clean and format the input function
     */
    public static String  clean_inputs ( String function) {
        String input = function.toLowerCase();

        char [] output = input.toCharArray(); // char array so we can isolate the inputs
        String formated_input = "";

        // remove all spaces
        for(int i =0; i < output.length; i ++) {
            if(output[i] == ' ') {
                continue;
            }
            formated_input += output[i];

        }

        return formated_input;
    }

    /*
     * method to check if a operater is still there
     */
    public static boolean still_there ( char opp, char[] func) {
        for(int i =0; i < func.length; i++) {
            if(func[i]==opp) {
                return true;
            }
        }
        return false;
    }


    /*
     * method pop an index out of an array
     */
    public static char []  pop ( char [] func , int index) {

        char [] function = func;
        char [] function_temp = new char[function.length-1];
        int offset =0;

        for(int i = 0; i < function.length; i ++) {
            if(i!=index) {
                function_temp[i-offset] = function[i];

            }else {
                offset = 1;
            }

        }

        return function_temp;
    }

    /*
     * method to test the expretion over one case
     */
    public static boolean test (int numb, String function_input) {
        //stores binary
        int [] inputs = new int [27]; // digits

        //stores the eq
        //in the form of a pos
        char [] function = clean_inputs(function_input).toCharArray();

        String b_numb = Integer.toBinaryString(numb + 67108864);

        //this is 1's and 0's binary i guess
        char [] b_numb_array = b_numb.toCharArray();

        // populate the input array with data
        for(int i = 1; i < 27; i ++) {
            inputs[i] = (int)(b_numb_array[i])-48;
        }

        // populate the binary values into the function char array
        for(int i = function.length-1; i > 0; i --) {

            if (function[i] >96 && function[i]<123) {
                function[i] =  (char) (inputs[(int)(function[i])-97]+ 48);
            }
        }

        // this part is the logic that solves the function if the output is 1 return true etc
        // split by ors and solve -----------------------------------------------------

        //split function by the plus sign
        // the \\ denotes literal, otherwise + is not taken literally and has a different meaning

        String stringFunction = function.toString();
        String[] splitFunction = stringFunction.split("\\+");
        char[][] charFunction = new char[splitFunction.length][];


        //treat splitFunction as a 2d array
        for(int i = 0; i < splitFunction.length; i++){
            char[] currSplit = splitFunction[i].toCharArray();
            //add currSplit to charFunction since charFunction is a 2d array
            charFunction[i] = currSplit;

        }

        // split by ors and solve -----------------------------------------------------

        //now we have our eq broken up by into AND gates
        //check the function that has the lowest length
        //we only need to check 1 function since each function is separated by or gates
        //sort the array based on length
        //bubble sort idk any other sorting algs
        charFunction = bubbleSort(charFunction);

        //start with the first item in the list since it has the smallest length
        for(int i = 0; i < charFunction.length; i++){

            //inputs contains the binary values where inputs[25] is A, and works down

            //setup for each product function
            boolean skipNext = false;
            for(int j = 0; j < charFunction[i].length; i++){
                //return either a 0 or a 1 for each letter in the char[]
                //check the index right after to consider the case we have a x'
                if(skipNext){skipNext = false; continue;}
                if(charFunction[i][j + 1] == '\''){
                      boolean conv = letterConversion(charFunction[i][j], true, inputs);
                }else{boolean conv = letterConversion(charFunction[i][j], false, inputs);}

            }


        }

        return false;
    }

    public static boolean letterConversion(char letter, boolean inversed, int[] inputs){

        boolean val = false;
        int letterIndex = -1;

        switch(letter){
            case 'a': letterIndex = 25;break;
            case 'b': letterIndex = 24;break;
            case 'c': letterIndex = 23;break;
            case 'd': letterIndex = 22;break;
            case 'e': letterIndex = 21;break;
            case 'f': letterIndex = 20;break;
            case 'g': letterIndex = 19;break;
            case 'h': letterIndex = 18;break;
            case 'i': letterIndex = 17;break;
            case 'j': letterIndex = 16;break;
            case 'k': letterIndex = 15;break;
            case 'l': letterIndex = 14;break;
            case 'm': letterIndex = 13;break;
            case 'n': letterIndex = 12;break;
            case 'o': letterIndex = 11;break;
            case 'p': letterIndex = 10;break;
            case 'q': letterIndex = 9; break;
            case 'r': letterIndex = 8; break;
            case 's': letterIndex = 7; break;
            case 't': letterIndex = 6; break;
            case 'u': letterIndex = 5; break;
            case 'v': letterIndex = 4; break;
            case 'w': letterIndex = 3; break;
            case 'x': letterIndex = 2; break;
            case 'y': letterIndex = 1; break;
            case 'z': letterIndex = 0; break;
        }

        //use letter index to see if the value at inputs is either 0 or 1, that is the value for the given letter
        try {
            if(inputs[letterIndex] == 0){val = false;}else
            if(inputs[letterIndex] == 1){val = true;}
        }catch(Exception e){System.out.printf("There was an issue with finding the index num for '%s'\n", letter); e.printStackTrace();}

        return val;
    }



    public static char[][] bubbleSort(char[][] list){
        boolean swapped;
        for(int i = 0; i < list.length - 1; i++){
            swapped = false;

            //the last item always gets sorted out
            for(int j = 0; j < list.length - 1 - i; j++){
                if(list[j].length > list[j + 1].length){
                    char[] buff = list[j];

                    list[j] = list[j + 1];
                    list[j + 1] = buff;
                    swapped = true;
                }
            }
            if(!swapped){break;}
        }
        return list;
    }



    /*
     * method to ouput the test
     */
    public static void output (int num_inputs,String func ) {
        int max = (int)(Math.pow(2, num_inputs) - 1); // max value posible
        System.out.println ("#|Z Y X W V U T S R Q P O N M L K J I H G F E D C B A | output");


        // iterate through all numbers
        for(int i =0; i <= max; i ++) {

            char [] temp = Integer.toBinaryString(i +67108864).toCharArray();
            String numb ="";

            for(int j =1; j < 27; j ++) {
                numb+= temp[j]+ " ";
            }

            // print out a formated output
            System.out.print (i + "|" + numb+ " =");

            if(decoder_logic.test( i, func)) {
                System.out.print ("1");
            }else {
                System.out.print ("0");
            }

            System.out.println();
        }
    }

    /**
     * self testing main
     */
    public static void main(String[] args) {

        String func = "ab'+a";

        //output(4,func);

        System.out.println ("--------------------------------");

        // a= 1 b=1 c =1
        test(3,func);








    }


}
