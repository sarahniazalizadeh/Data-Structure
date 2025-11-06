package day04arraylistownimpl;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class CustomArrayOfIntsTest {
    

    // shared custom array of ints
    CustomArrayOfInts cas;
    
    @Before
    public void beforeEach() {
        int [] casData = { 3, 5, 7, 9};
        cas = new CustomArrayOfInts();
        for (int i = 0; i < casData.length; i++) {
            cas.add(casData[i]);
        }
    }
    
    @Test
    public void addSome() {
        assertEquals(4, cas.size());
        assertEquals("[3,5,7,9]", cas.toString());
        int[] exp = {3, 5, 7, 9};
        for (int i = 0; i < exp.length; i++) {
            assertEquals(exp[i], cas.get(i));
        }
        assertArrayEquals(exp, cas.toArray());
        cas.clear();
        assertEquals(0, cas.size());
    }
/*
    @Test
    void mustThrow() {
        CustomArrayOfInts ca = new CustomArrayOfInts();
        ca.add(3);
        ca.add(5);
        ca.add(7);
        ca.add(9);
        int[] excIdxs = {-100, -2, -1, 4, 5, 10, 100};
        for (int i = 0; i < excIdxs.length; i++) {
            int copyOfI = i;
            Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
                ca.get(excIdxs[copyOfI]);
            });
        }
    } */

    @Test
    public void deleteNotTooFar() {
        // make sure when data (storage) is full delete does not crash due to going too far when copying data
        cas.deleteByIndex(2); // must not throw IndexOutOfBoundsException
        assertEquals("[3,5,9]", cas.toString());
        cas.deleteByIndex(2); // must not throw IndexOutOfBoundsException
        assertEquals("[3,5]", cas.toString());
        
        try {
            cas.deleteByIndex(2); // must throw IndexOutOfBoundsException
            fail();
        } catch (IndexOutOfBoundsException ex) {
            // test passed
        }
        
        try {
            cas.deleteByIndex(-1); // must throw IndexOutOfBoundsException
            fail();
        } catch (IndexOutOfBoundsException ex) {
            // test passed
        }
    }

    @Test
    public void deleteByValue() {
        cas.add(1);
        assertEquals(false, cas.deleteByValue(11));
        assertEquals(true, cas.deleteByValue(9));
        assertEquals("[3,5,7,1]", cas.toString());
    }

    @Test
    public void insertAtIndex() {
        /* CustomArrayOfInts ca = new CustomArrayOfInts();
        ca.add(3);
        ca.add(5);
        ca.add(7); // << insert 4 here, index 2
        ca.add(9); */
        cas.add(1);
        cas.insertValueAtIndex(4, 2);
        assertEquals("[3,5,4,7,9,1]", cas.toString());
        cas.insertValueAtIndex(8, 0);
        assertEquals("[8,3,5,4,7,9,1]", cas.toString());
        cas.insertValueAtIndex(2, 7);
        assertEquals("[8,3,5,4,7,9,1,2]", cas.toString());
        try {
            cas.insertValueAtIndex(99, 9); // must throw IndexOutOfBoundsException
            fail();
        } catch (IndexOutOfBoundsException ex) {
            // test passed
        }
        try {
            cas.insertValueAtIndex(99, -1); // must throw IndexOutOfBoundsException
            fail();
        } catch (IndexOutOfBoundsException ex) {
            // test passed
        }

    }

    @Test
    public void getSlice() {
        CustomArrayOfInts ca = new CustomArrayOfInts();
        ca.add(3);
        ca.add(5);
        ca.add(7); // from index 2, length 3
        ca.add(6);
        ca.add(9);
        ca.add(1);
        int [] exp1 = {7, 6, 9}; // middle
        assertArrayEquals(exp1, ca.getSlice(2, 3));
        int [] exp2 = {3, 5, 7}; //start
        assertArrayEquals(exp2, ca.getSlice(0, 3));
        int [] exp3 = {6, 9, 1}; // end
        assertArrayEquals(exp3, ca.getSlice(3, 3));

        try { // too early start
            ca.getSlice(-1, 3); // must throw IndexOutOfBoundsException
            fail();
        } catch (IndexOutOfBoundsException ex) {
            // test passed
        }
        try { // too far start/end
            ca.getSlice(4, 3); // must throw IndexOutOfBoundsException
            fail();
        } catch (IndexOutOfBoundsException ex) {
            // test passed
        }

    }
   
}
