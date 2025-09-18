import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(arr);

        System.out.println(getMean(arr));
        System.out.println(getMedian(arr));
        System.out.println(getFreq(arr));
        System.out.println(getRange(arr));
    }

    private static int getMean(int[] arr) {
        int sum = 0;

        for (int a : arr) {
            sum += a;
        }

        return (int) Math.round((double) sum / arr.length);
    }

    private static int getMedian(int[] arr) {
        return arr[arr.length / 2];
    }

    private static int getFreq(int[] arr) {
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int a : arr) {
            map.put(a, map.getOrDefault(a, 0) + 1);
        }

        int maxFreq = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            maxFreq = Math.max(entry.getValue(), maxFreq);
        }

        List<Integer> maxFreqs = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() == maxFreq) {
                maxFreqs.add(entry.getKey());
            }
        }

        Collections.sort(maxFreqs);

        if (maxFreqs.size() == 1) {
            return maxFreqs.get(0);
        } else {
            return maxFreqs.get(1);
        }
    }

    private static int getRange(int[] arr) {
        return arr[arr.length - 1] - arr[0];
    }
}