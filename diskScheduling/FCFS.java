package diskScheduling;
import java.util.Scanner;

public class FCFS {
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
        
        totalMovement += Math.abs(headPosition - requests[0]);
        
        for (int i = 1; i < n; i++) {
            totalMovement += Math.abs(requests[i] - requests[i - 1]);
        }
        
        System.out.println("Total head movement = " + totalMovement);
    }
}