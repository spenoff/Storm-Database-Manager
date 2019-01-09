
package sbm;

import java.util.Comparator;
import java.util.function.Function;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;

/**
 * Compares two storms based on the amount of precipitation
 *
 * @author Spencer Nisonoff
 */
public class PrecipitationComparator implements Comparator{

    /**
     * Compares two Storm objects based in precipitation
     * @param o1 the first Storm object
     * @param o2 the second Storm object
     * @return 0 if the two Storm objects have the same precipitation, 1 if the first Storm object has greater
     * precipitation than the second Storm object, or -1 if second Storm object has greater precipitation than the first
     * Storm object.
     */
    public int compare(Object o1, Object o2) {
        Storm e1 = (Storm) o1;

        Storm e2 = (Storm) o2;

        if (e1.getPrecipitation() == e2.getPrecipitation())

            return 0;

        else if (e1.getPrecipitation() > e2.getPrecipitation())

            return 1;

        else

            return -1;
    }
}
