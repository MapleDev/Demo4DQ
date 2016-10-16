package com.kymjs.marqueeviewanim;

import java.util.Random;

/**
 * @author MapleDev
 * @time 16/09/26  17:34
 * @desc ${TODD}
 */
public class UtilTools {


    public static int[] Getrandomarray(int x, int y) {
        Random random = new Random();
        int rstX = random.nextInt(x) + 1;
        int rstY = random.nextInt(y) + 1;

        int[] ints = {rstX, rstY};
        return ints;
    }
}
