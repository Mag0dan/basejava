package ru.javawebinar.basejava;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MainStream {
    public static void main(String[] args) {
        int[] values = new int[] {1,3,7,3,4,3,5};
        System.out.println(minValue(values));

        final List<Integer> list = new ArrayList<>(Arrays.asList(1,3,6,8,0));
        oddOrEven(list).forEach(System.out::println);
    }

    public static int minValue(int[] values) {
        return Arrays.stream(values).distinct().sorted().reduce((e1, e2) -> e1*10 + e2).orElse(0);
    }

    public static List<Integer> oddOrEven(List<Integer> integers) {
        int mod = integers.stream().reduce(Integer::sum).orElse(0) % 2;
        return integers.stream().filter((e) -> e % 2 != mod).collect(Collectors.toList());
    }
}
