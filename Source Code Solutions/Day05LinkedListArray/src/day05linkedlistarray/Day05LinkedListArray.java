package day05linkedlistarray;

import java.util.Arrays;

public class Day05LinkedListArray {

    public static void main(String[] args) {
        LinkedListArrayOfStrings instance = new LinkedListArrayOfStrings();
        instance.add("Jerry");
        instance.add("Terry");
        instance.add("Barry");
        instance.add("Larry");
        instance.add("Marry");
        System.out.println(instance.toStringWhile());
        System.out.println(Arrays.toString(instance.toArray()));
        instance.insertValueAtIndex("George", 3);
    }

}
