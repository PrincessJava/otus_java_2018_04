import org.junit.Before;
import org.junit.Test;
import ru.otus.t1.MyArrayList;

import java.util.Arrays;
import java.util.List;

import static java.util.Collections.*;
import static org.junit.Assert.assertEquals;

public class MyArrayListTest {

    private List<Integer> myList;
    private List<Integer> etalonList;

    @Before
    public void init() {
        myList = new MyArrayList<>();
        etalonList = Arrays.asList(0, 4, 2);
    }

    @Test
    public void AddAllTest() {
        addAll(myList, 0, 4, 2);

        assertEquals(etalonList.size(), myList.size());
        assertEquals(etalonList.get(0), myList.get(0));
    }

    @Test
    public void CopyTest() {
        myList.add(1);
        myList.add(6);
        myList.add(10);

        copy(etalonList, myList);

        assertEquals(etalonList.size(), 3);
        assertEquals(etalonList.get(1), new Integer(6));
    }

    @Test
    public void SortTest() {
        myList.add(4);
        myList.add(2);
        myList.add(0);

        sort(myList);
        sort(etalonList);

        assertEquals(etalonList.size(), myList.size());
    }

}
