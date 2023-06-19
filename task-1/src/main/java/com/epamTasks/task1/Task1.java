package com.epamTasks.task1;

import java.util.*;

public class Task1 {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
//        System.out.println(list.size());
//        list.set(0,1);
        System.out.println(list.size());
        list.addAll(0,new ArrayList<>(Arrays.asList(1,2)));
        System.out.println(list.size());
        System.out.println(list);
////
//        list.add(1);
//        list.add(2);
//        list.add(3);
//        list.add(4);
//        ListIterator<Integer> iterator = list.listIterator();
////        System.out.println(iterator.hasNext());
////        System.out.println(iterator.hasPrevious());
////        System.out.println(iterator.nextIndex());
////        System.out.println(iterator.previousIndex());
//
//        System.out.println(iterator.next());
//        System.out.println(iterator.previous());
//        iterator.add(9);
//        System.out.println(list);
//        System.out.println(iterator.next());
//        System.out.println(iterator.previous());
//        System.out.println(iterator.remove());
//        System.out.println(iterator.add(1));
//        System.out.println(iterator.set());
    }
}
