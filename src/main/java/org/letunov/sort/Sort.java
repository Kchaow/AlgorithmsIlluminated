package org.letunov.sort;

import java.util.ArrayList;
import java.util.List;

public class Sort
{
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
}
