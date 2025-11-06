
package day04arraylistownimpl;

public class Day04ArrayListOwnImpl {

    public static void main(String[] args) {
        CustomArrayOfInts intsArray = new CustomArrayOfInts();
        intsArray.add(3);
        intsArray.add(5);
        intsArray.add(7);
        intsArray.add(2);
        intsArray.add(4);
        System.out.println("Array: " + intsArray);
        intsArray.insertValueAtIndex(9, 2);
        System.out.println("Array: " + intsArray);
        intsArray.deleteByIndex(3);
        System.out.println("Array: " + intsArray);
        System.out.println("[3] = " + intsArray.get(3));
    }
    
}
