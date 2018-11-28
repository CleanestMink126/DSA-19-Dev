public class Trampolines {
    public static int trampoline(int[] nums) {
        int[] DP = new int[nums.length];
        for (int i = nums.length - 2; i >= 0; i--) {
            int distance = Integer.MAX_VALUE;
            for (int j = i + 1; j <= Math.min(i + nums[i], nums.length - 1); j++) {
                if (DP[j] == Integer.MAX_VALUE)
                    continue;
                distance = Math.min(distance, DP[j] + 1);
            }
            DP[i] = distance;
        }
        return DP[0];
    }

    public static int trampoline_greedy(int[] nums) {
        int jumps = 0, curEnd = 0, curFarthest = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            curFarthest = Math.max(curFarthest, i + nums[i]);
            if (i == curEnd) {
                jumps++;
                curEnd = curFarthest;
            }
        }
        return jumps;
    }

    public static int trampoline_BFS(int[] nums) {
        if (nums.length < 2) return 0;
        int level = 0, currentMax = 0, i = 0, nextMax = 0;

        while (currentMax - i + 1 > 0) {        //nodes count of current level>0
            level++;
            for (; i <= currentMax; i++) {    //traverse current level , and update the max reach of next level
                nextMax = Math.max(nextMax, nums[i] + i);
                if (nextMax >= nums.length - 1)
                    return level;   // if last element is in level+1,  then the min jump=level
            }
            currentMax = nextMax;
        }
        return 0;
    }


    public static void main(String[] args) {
        System.out.println(trampoline_BFS(new int[]{2, 3, 1, 1, 4, 1}));
        System.out.println(trampoline_BFS(new int[]{1, 2, 0, 1}));
    }
}