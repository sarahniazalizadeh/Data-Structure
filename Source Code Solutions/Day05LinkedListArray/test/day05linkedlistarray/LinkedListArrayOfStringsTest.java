package day05linkedlistarray;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class LinkedListArrayOfStringsTest {

    @Before
    public void setUp() {
    }

    @Test
    public void testAddToStringToArray() {
        System.out.println("add toString toArray");
        LinkedListArrayOfStrings instance = new LinkedListArrayOfStrings();
        instance.add("Jerry");
        instance.add("Terry");
        instance.add("Barry");
        instance.add("Larry");
        instance.add("Marry");
        assertEquals(5, instance.getSize());
        assertEquals("[Jerry,Terry,Barry,Larry,Marry]", instance.toString());
    }
    
    @Test
    public void testAddGet() {
        System.out.println("add get");
        LinkedListArrayOfStrings instance = new LinkedListArrayOfStrings();
        instance.add("Jerry");
        instance.add("Terry");
        instance.add("Barry");
        instance.add("Larry");
        instance.add("Marry");
        assertEquals("Jerry", instance.get(0));
        assertEquals("Larry", instance.get(3));
        assertEquals("Marry", instance.get(4));
        try {
            instance.get(5);
            fail();
        } catch (ArrayIndexOutOfBoundsException ex) {
            // pass
        }
        try {
            instance.get(-1);
            fail();
        } catch (ArrayIndexOutOfBoundsException ex) {
            // pass
        }
    }
    
    @Test
    public void testAddInsert() {
        System.out.println("add insert");
        LinkedListArrayOfStrings instance = new LinkedListArrayOfStrings();
        instance.add("Jerry");
        instance.add("Terry");
        instance.insertValueAtIndex("Barry",0); // insert at the start
        assertEquals("[Barry,Jerry,Terry]", instance.toString());
        instance.insertValueAtIndex("Marry",3); // really an add - insert at the very end
        assertEquals("[Barry,Jerry,Terry,Marry]", instance.toString());
        instance.insertValueAtIndex("Zarry",2); // insert in the middle
        assertEquals("[Barry,Jerry,Zarry,Terry,Marry]", instance.toString());
    }
    
     @Test
    public void testAddDelete() {
        System.out.println("add delete");
        LinkedListArrayOfStrings instance = new LinkedListArrayOfStrings();
        instance.add("Jerry"); // 0
        instance.add("Terry");
        instance.add("Barry");
        instance.add("Marry"); // 3
        instance.add("Larry");
        instance.add("Garry"); // 5
        instance.deleteByIndex(5);
        assertEquals("[Jerry,Terry,Barry,Marry,Larry]", instance.toString());
        instance.add("Harry"); // if we forgot to adjust end pointer next line will throw NPE
        assertEquals("[Jerry,Terry,Barry,Marry,Larry,Harry]", instance.toString());
        instance.deleteByIndex(3);
        assertEquals("[Jerry,Terry,Barry,Larry,Harry]", instance.toString());
        instance.deleteByIndex(0);
        assertEquals("[Terry,Barry,Larry,Harry]", instance.toString());
    }
    
    @Test
    public void testAddDeleteAdd() {
        System.out.println("add delete");
        LinkedListArrayOfStrings instance = new LinkedListArrayOfStrings();
        instance.add("Jerry");
        instance.add("Terry");
        instance.add("Barry"); // 2
        instance.deleteByIndex(2);
        assertEquals("[Jerry,Terry]", instance.toString());
        instance.add("Marry");
        assertEquals("[Jerry,Terry,Marry]", instance.toString());
    }
}
