package org.letunov.sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

public class Sort
{
    private static final Random random = new Random();

    static public <T extends Comparable<T>> List<T> mergeSort(final List<T> list)
    {
        if (list.size() <= 1)
            return list;
        List<T> firstHalf = mergeSort(list.subList(0, list.size()/2));
        List<T> secondHalf = mergeSort(list.subList(list.size()/2, list.size()));
        return merge(firstHalf, secondHalf);
    }

    static private <T extends Comparable<T>> List<T> merge(final List<T> firstList, final List<T> secondList)
    {
        List<T> resultList = new ArrayList<>();
        for (int i = 0, j = 0; i < firstList.size() && j < secondList.size();)
        {
            if (firstList.get(i).compareTo(secondList.get(j)) < 0)
            {
                resultList.add(firstList.get(i));
                i++;
                if (i == firstList.size())
                {
                    resultList.addAll(secondList.subList(j, secondList.size()));
                    break;
                }
            }
            else
            {
                resultList.add(secondList.get(j));
                j++;
                if (j == secondList.size())
                {
                    resultList.addAll(firstList.subList(i, firstList.size()));
                    break;
                }
            }
        }
        return resultList;
    }

    static public <T extends Comparable<T>> List<T> quickSort(final List<T> list)
    {
        List<T> copyOfList = new ArrayList<>(Stream.generate(() -> (T) null).limit(list.size()).toList());
        Collections.copy(copyOfList, list);
        inPlaceQuickSort(copyOfList, 0, copyOfList.size());
        return copyOfList;
    }

    static private <T extends Comparable<T>> void inPlaceQuickSort(List<T> list, int l,int r)
    {
        if (l >= r-1)
            return;
        int i = random.nextInt(l, r);
        T temp = list.get(i);
        list.set(i, list.get(l));
        list.set(l, temp);
        int j = makePartition(list, l, r);
        inPlaceQuickSort(list, l, j);
        inPlaceQuickSort(list, j+1, r);
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
