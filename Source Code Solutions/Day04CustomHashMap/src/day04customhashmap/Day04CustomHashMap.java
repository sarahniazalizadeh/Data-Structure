package day04customhashmap;

public class Day04CustomHashMap {

    public static void testHMSS() {
        CustomHashMapStringString hmss = new CustomHashMapStringString();
        hmss.putValue("abc", "Jerry");
        hmss.putValue("bca", "Terry");
        hmss.putValue("aaa", "Barry");
        hmss.putValue("bbb", "Larry");
        hmss.putValue("cba", "Garry");
        hmss.putValue("bca", "Marry"); // overriding existing entry
        hmss.printDebug();
        
        System.out.println("aaa => " + hmss.getValue("aaa"));
        
        /*
        for (Pair<String,String> pair : hmss) {
            System.out.printf("%s => %s\n", pair.key, pair.value);
        }*/
        System.out.println(String.join(",", hmss.getAllKeys()));

        for (String key : hmss) {
            System.out.printf("%s => %s\n", key, hmss.getValue(key));
        }
/*
        for (Pair<String,String> pair : hmss.getKeyValueIterator()) {
            System.out.printf("%s => %s\n", pair.key, pair.value);
        } */        
    }
    
    public static void main(String[] args) {
        // testHMSS();
        CustomHashMap<String,String> hmss = new CustomHashMap<>();
        hmss.putValue("abc", "Jerry");
        hmss.putValue("bca", "Terry");
        hmss.putValue("aaa", "Barry");
        hmss.putValue("bbb", "Larry");
        hmss.putValue("cba", "Garry");
        hmss.putValue("bca", "Marry"); // overriding existing entry
        hmss.printDebug();
        
        for (Pair<String,String> pair : hmss.getAllKeyValPairs()) {
            System.out.printf("%s => %s\n", pair.key, pair.val);
        }
    }
    
}
