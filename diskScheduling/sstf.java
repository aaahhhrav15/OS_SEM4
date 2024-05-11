package diskScheduling;

import java.util.Scanner;

public class sstf 
{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of requests: ");
        int n = scanner.nextInt();

        int[] requests = new int[n];
        System.out.println("Enter the requests: ");
        for (int i = 0; i < n; i++) {
            requests[i] = scanner.nextInt();
        }

        System.out.print("Enter the initial position of the disk head: ");
        int headPosition = scanner.nextInt();

        int totalMovement = 0;
        boolean[] visited = new boolean[n];

        for (int i = 0; i < n; i++) {
            int min = Integer.MAX_VALUE;
            int index = -1;

            for (int j = 0; j < n; j++) {
                if (!visited[j]) {
                    int diff = Math.abs(headPosition - requests[j]);
                    if (diff < min) {
                        min = diff;
                        index = j;
                    }
                }
            }

            visited[index] = true;
            totalMovement += min;
            headPosition = requests[index];
        }

        System.out.println("Total head movement = " + totalMovement);
    }
}