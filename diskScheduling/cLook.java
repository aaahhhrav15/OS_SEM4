package diskScheduling;

import java.util.Scanner;

public class cLook 
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

        int minReq=999,maxReq=0;
        for(int i=0;i<n;i++)
        {
            if(requests[i]<minReq)
            {
                minReq=requests[i];
            }
            if(requests[i]>maxReq)
            {
                maxReq=requests[i];
            }
        }

        int maxInSmaller=0;
        for(int i=0;i<n;i++)
        {
            if(requests[i]<headPosition && requests[i]>maxInSmaller)
            {
                maxInSmaller=requests[i];
            }
        }

        int totalMovement= maxReq - headPosition + maxReq - minReq + maxInSmaller- minReq;

        System.out.println("Total head movement = " + totalMovement);
        scanner.close();
    }    
}
