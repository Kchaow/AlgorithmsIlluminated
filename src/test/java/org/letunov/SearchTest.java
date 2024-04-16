package org.letunov;

import org.junit.jupiter.api.Test;
import org.letunov.search.Search;
import org.letunov.sort.Sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SearchTest
{
    @Test
    public void rSelectTest()
    {
        List<Integer> list1 = new ArrayList<>();
        list1.add(5); list1.add(4); list1.add(1); list1.add(8);
        list1.add(7); list1.add(2); list1.add(6); list1.add(3);
        list1.add(12); list1.add(-1); list1.add(13);
        List<Integer> list2 = new ArrayList<>();
        list2.add(1); list2.add(2); list2.add(3); list2.add(4);
        list2.add(5); list2.add(6); list2.add(7); list2.add(8);
        List<Integer> list3 = new ArrayList<>();
        list3.add(5); list3.add(3); list3.add(4); list3.add(1);
        list3.add(5); list3.add(8); list3.add(7); list3.add(2);
        list3.add(6); list3.add(3); list3.add(1);
        assertAll(
                () -> assertEquals(-1, Search.rSelect(list1, 1)),
                () -> assertEquals(3, Search.rSelect(list2, 3)),
                () -> assertEquals(5, Search.rSelect(list3, 8))
        );
    }
}
