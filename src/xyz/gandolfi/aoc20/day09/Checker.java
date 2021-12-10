package xyz.gandolfi.aoc20.day09;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Checker {
    public static boolean findTwoThatSumUpTo(List<Long> inputNums, long sum) {
        Set<Long> seen = new HashSet<>();
        for (long num : inputNums) {
            long diff = sum - num;
            if (seen.contains(diff)) return true;
            seen.add(num);
        }
        return false;
    }

    public static Long findErrorValue(List<Long> inputNums, int preamble) {
        LinkedList<Long> checkList = new LinkedList<>();
        for (int i = 0; i < inputNums.size(); ++i) {
            long num = inputNums.get(i);
            if (i < preamble) {
                checkList.addLast(num);
                continue;
            }
            if (!findTwoThatSumUpTo(checkList, num))
                return num;
            checkList.addLast(num);
            checkList.removeFirst();
        }
        return null;
    }

    public static List<Long> findRangeAddsUpTo(List<Long> inputNums, long addsUpTo) {
        int a = 0, b = 1;
        long sum = inputNums.get(0);

        while (sum != addsUpTo)
            if (sum < addsUpTo) sum += inputNums.get(b++);
            else sum -= inputNums.get(a++);

        return inputNums.stream()
            .skip(a)
            .limit(b - a)
            .toList();
    }

    public static long[] getMinMaxFrom(List<Long> nums) {
        long min = nums.get(0);
        long max = nums.get(0);
        for (Long num : nums) {
            if (num < min) min = num;
            if (num > max) max = num;
        }
        return new long[] {min, max};
    }
}
