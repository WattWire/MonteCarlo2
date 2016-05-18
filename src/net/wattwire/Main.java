package net.wattwire;

import java.text.MessageFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.apache.commons.lang.math.RandomUtils.nextInt;


public class Main {

    public static Logger logger = Logger.getLogger("myLogger");

    public static void main(String[] args) {

        runMCsim(10);
        runMCsim(100);
        runMCsim(1000);
        runMCsim(10000);
        runMCsim(100000);
        runMCsim(100000);
        runMCsim(1000000);

    }

public static void runMCsim(int nloops)
{

    final int NGRAB = 3;
    final int NMARBLES = 6;

    int nAllMatched = 0;

    final int BLUE = 1;
    final int WHITE = 2;

    int availbleMarbles;
    int basketPosGrabbed;

    int j;

    //****** Main, Ea Loop = 1 Test *****************
    for (int loop =0; loop < nloops; loop++){

        int[] basket = new int[] { BLUE, BLUE, BLUE, WHITE, WHITE, WHITE };
        int[] grabbed = new int[NGRAB];

        availbleMarbles = NMARBLES;

        boolean allMatched = true;

        //****** Grab One Marble for each g *******
        for (int g = 0; g < NGRAB; g++){

            basketPosGrabbed = nextInt(availbleMarbles);

            grabbed[g] = basket[basketPosGrabbed];

            int[] newBasket = new int[NMARBLES];

            j= 0;

            //*** Remove grabbed marble from basket
            for (int m = 0; m < availbleMarbles; m++){

                if (m != basketPosGrabbed){   //*** Don't copy over selected

//                    logger.log(Level.INFO, "newBasket.length= "+newBasket.length+"   basket.length= "+ basket.length + "   j= "+j+"   m= "+m);

                    newBasket[j] =  basket[m];

                    j++;
                }
            }
            //*** Update basket & available marble count
            basket = newBasket;

            availbleMarbles--; //*** One less marble available from main basket now

        }
        //*** We've grabbed our marbles, let's see if they all match
        for (int g = 1; g < NGRAB; g++){

            if (grabbed[0] != grabbed[g]){

                allMatched = false;
                break;
            }
        }
        //**** Update count if they all matched
        if (allMatched){
            nAllMatched++;
        }
    }
    //***********************************************************
    //*** Print simulation results:

//    double percentAllMatched = nAllMatched / nloops;
    double percentAllMatched = nAllMatched / (double)nloops;

    String pResult = MessageFormat.format("{0,number, 0.00%}",percentAllMatched);

    System.out.println("*** Iterations: " + nloops + "   Times All the Color Marble: "
            +nAllMatched+ "   Percent All Matched: "+pResult);

}



}
