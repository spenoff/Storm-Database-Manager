/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sbm;

import java.util.Comparator;
import java.util.function.Function;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;

/**
 * @author Spencer Nisonoff
 *    e-mail: spencer.nisonoff@stonybrook.edu
 *    Stony Brook ID: 111614611
 *    Assignment number: 6
 *    Course: CSE 214
 *    Recitation Number: 14
 */
public class WindSpeedComparator implements Comparator{

    /**
     * Compares two Storm objects based in windspeed
     * @param o1 the first Storm object
     * @param o2 the second Storm object
     * @return 0 if the two Storm objects have the same windspeed, 1 if the first Storm object has greater windspeed
     * than the second Storm object, or -1 if second Storm object has greater windspeed than the first Storm object.
     */
    public int compare(Object o1, Object o2) {
        Storm e1 = (Storm) o1;

        Storm e2 = (Storm) o2;

        if (e1.getWindspeed() == e2.getWindspeed())

            return 0;

            else if (e1.getWindspeed() > e2.getWindspeed())

            return 1;

        else

            return -1;
    }
}
