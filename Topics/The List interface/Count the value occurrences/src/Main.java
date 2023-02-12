
class Counter {

    public static boolean checkTheSameNumberOfTimes(int elem, List<Integer> list1, List<Integer> list2) {
        // implement the method
        list1.removeIf(n -> n != elem);
        list2.removeIf(n -> n != elem);
        
        return list1.size() == list2.size();
    }
}
