package diskScheduling;

import java.util.Scanner;

public class scan 
{
    public static void main(String[] args) 
    {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of requests: ");
        int n = scanner.nextInt();

        int[] requests = new int[n];
        System.out.println("Enter the requests: ");
        for (int i = 0; i < n; i++) 
        {
            requests[i] = scanner.nextInt();
        }

        System.out.print("Enter the initial position of the disk head: ");
        int headPosition = scanner.nextInt();

        System.out.print("Enter the track end number: ");
        int maxTrack = scanner.nextInt();
        int minReq=999;
        for(int i=0;i<n;i++)
        {
            if(requests[i]<minReq)
            {
                minReq=requests[i];
            }
        }

        int totalMovement= maxTrack - headPosition + maxTrack - minReq;

        System.out.println("Total head movement = " + totalMovement);
        scanner.close();
    }
}