package com.redsparkle.foe.utils;

/**
 * Created by hoijima on 19.05.17.
 */
public class Lvlutil {
    //59 lvl starting from 0 , in the end there are 60 lvls
    public static Integer points =0;
    public static boolean targetLvl =false;
    public static Integer minus =0;
    public static String progrsssbar="";
    public static Integer[] lvls = {
            1000,2500,
            3500,4500,
            5500,6500,
            7500,8500,
            9500,10500,
            11500,12500,
            13500,14500,
            15500,16500,
            17500,18500,
            19500,20500,
            21500,22500,
            23500,24500,
            25500,26500,
            27500,28500,
            29500,30500,
            31500,32500,
            33500,34500,
            35500,36500,
            37500,38500,
            39500,40500,
            41500,42500,
            43500,44500,
            45500,46500,
            47500,48500,
            49500,50500,
            51500,52500,
            53500,54500,
            55500,56500,
            57500,58500,
            59500,60500,
            62000
    };
    public static String[] prorgress={
            "[==========]",
            "[|=========]",
            "[||========]",
            "[|||=======]",
            "[||||======]",
            "[|||||=====]",
            "[||||||====]",
            "[|||||||===]",
            "[||||||||==]",
            "[|||||||||=]",
            "[====MAX===]"
    };
    public static String progress(int playerLvl, Integer playerProgress){


        if( playerLvl < 60 ) {
            //< 10%
            if ((float) playerProgress < (float) (lvls[playerLvl] * 0.1)) {
                return prorgress[0];
            } else
                // =>10% <20%
                if ((float) playerProgress >= (float) (lvls[playerLvl] * 0.1) && (float) playerProgress <= (float) (lvls[playerLvl] * 0.2)) {
                    return prorgress[1];
                } else
                    // =>20% <30%
                    if ((float) playerProgress >= (float) (lvls[playerLvl] * 0.2) && (float) playerProgress <= (float) (lvls[playerLvl] * 0.3)) {
                        return prorgress[2];
                    } else
                        // =>30% <40%
                        if ((float) playerProgress >= (float) (lvls[playerLvl] * 0.3) && (float) playerProgress <= (float) (lvls[playerLvl] * 0.4)) {
                            return prorgress[3];
                        } else
                            // =>40% <50%
                            if ((float) playerProgress >= (float) (lvls[playerLvl] * 0.4) && (float) playerProgress <= (float) (lvls[playerLvl] * 0.5)) {
                                return prorgress[4];
                            } else
                                // =>50% <60%
                                if ((float) playerProgress >= (float) (lvls[playerLvl] * 0.5) && (float) playerProgress <= (float) (lvls[playerLvl] * 0.6)) {
                                    return prorgress[5];
                                } else
                                    // =>60% <70%
                                    if ((float) playerProgress >= (float) (lvls[playerLvl] * 0.6) && (float) playerProgress <= (float) (lvls[playerLvl] * 0.7)) {
                                        return prorgress[6];
                                    } else
                                        // =>70% <80%
                                        if ((float) playerProgress >= (float) (lvls[playerLvl] * 0.7) && (float) playerProgress <= (float) (lvls[playerLvl] * 0.8)) {
                                            return prorgress[7];
                                        } else
                                            // =>80% <90%
                                            if ((float) playerProgress >= (float) (lvls[playerLvl] * 0.8) && (float) playerProgress <= (float) (lvls[playerLvl] * 0.9)) {
                                                return prorgress[8];
                                            } else
                                                // =>90% <100%
                                                if ((float) playerProgress >= (float) (lvls[playerLvl] * 0.9) && (float) playerProgress < (float) (lvls[playerLvl])) {
                                                    return prorgress[9];
                                                } else
                                                    // =100%
                                                    if ((float) playerProgress >= (float) (lvls[playerLvl] * 0.9) && (float) playerProgress < (float) (lvls[playerLvl])) {
                                                        return prorgress[9];
                                                    }
        }else {
            return prorgress[10];
        }
        return prorgress[0];
    }

    public static Boolean canLvlup(Integer currentLvl,Integer currentProgress){

        if(currentProgress > lvls[currentLvl]) {
            return true;
        }else return false;
    };

    public static Integer ponitsAvailable(Integer currentLvl,Integer currentProgress){

        while(!targetLvl){
            if(currentProgress > lvls[currentLvl+minus]){
                points=points+10;
                minus=minus+1;
            } else targetLvl=true;
        }
        targetLvl=false;
        return points;
    };
}
