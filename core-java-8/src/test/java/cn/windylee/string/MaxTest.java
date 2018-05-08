package cn.windylee.string;

public class MaxTest {

    private static int[] haveTaked = new int[]{0, 0, 0, 0, 0, 0};

    public static void main(String[] args) {
        int[] array = new int[]{1, 2, 3, 4, 5, 6};
        int w = 10;
        System.out.println(dp(array, 5, w));
        for (int i = 0; i < haveTaked.length; ++i) {
            System.out.println(haveTaked[i]);
        }
    }

    private static int dp(int array[], int index, int weight) {
        if (index < 0) return 0;
        if (array[index] > weight) return dp(array, index - 1, weight);

        int depth1 = dp(array, index - 1, weight);
        int depth2 = dp(array, index - 1, weight - array[index]) + 1;
        if (depth1 > depth2) {
            haveTaked[index] = 0;
            return depth1;
        } else {
            haveTaked[index] = 1;
            return depth2;
        }
    }

}
