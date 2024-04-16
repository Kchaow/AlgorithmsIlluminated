package org.letunov.search;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

public class Search
{
    private static Random random = new Random();

    public static <T extends Comparable<T>> T rSelect(final List<T> list, int i)
    {
        List<T> copyOfList = new ArrayList<>(Stream.generate(() -> (T) null).limit(list.size()).toList());
        Collections.copy(copyOfList, list);
        return rSelectRec(copyOfList, i);
    }

    private static <T extends Comparable<T>> T rSelectRec(List<T> list, int i)
    {
        if (list.size() == 1)
            return list.get(0);
        int pIndex = random.nextInt(0, list.size());
        T temp = list.get(pIndex);
        list.set(pIndex, list.get(0));
        list.set(0, temp);
        int j = makePartition(list, 0, list.size());
        if (j == i-1)
            return list.get(pIndex);
        else if (j > i-1)
            return rSelectRec(list.subList(0, j), i);
        else
            return rSelectRec(list.subList(j+1, list.size()), i-j-1);
    }

    static private <T extends Comparable<T>> int makePartition(List<T> list, int l, int r)
    {
        T p = list.get(l);
        int i = l+1;
        for (int j = l+1; j < r; j++)
        {
            if (list.get(j).compareTo(p) < 0)
            {
                T temp = list.get(j);
                list.set(j, list.get(i));
                list.set(i, temp);
                i++;
            }
        }
        T temp = list.get(i-1);
        list.set(i-1, list.get(l));
        list.set(l, temp);
        return i - 1;
    }
}
