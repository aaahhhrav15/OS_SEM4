package processScheduling;
import java.util.Scanner;

public class SRTFPreemptive 
{
    public static void main(String[] args) 
    {
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nEnter the number of Processes: ");
        int n = scanner.nextInt();
        int used[] = new int[n]; 
        int time=0,y=n;

        int[] at = new int[n]; 
        int[] bt = new int[n]; 
        int[] x = new int[n]; 
        int[] waiting = new int[n];
        int[] turnaround = new int[n];
        int[] ct = new int[n];

        for (int i = 0; i < n; i++) 
        {
            System.out.print("\nEnter arrival time of process: ");
            at[i] = scanner.nextInt();
        }

        for (int i = 0; i < n; i++) 
        {
            System.out.print("\nEnter burst time of process: ");
            bt[i] = scanner.nextInt();
            x[i] = bt[i]; 
        }

        int minBurst=999;
        int index=-1;

        while(y!=0)
        {
            for(int i=0;i<n;i++)
            {
                if(at[i]<=time && bt[i]<minBurst && used[i]==0)
                {
                    minBurst=bt[i];
                    index=i;
                }
            }
            if(index==-1)
            {
                time++;
            }
            else
            {
                time++;
                bt[index]--;
                if(bt[index]==0)
                {
                    used[index]=1;
                    ct[index]=time;
                    y--;
                }
                index=-1;
                minBurst=999;
            }
        }

        for (int i = 0; i < n; i++) 
        {
            turnaround[i] = ct[i] - at[i];
            waiting[i] = turnaround[i] - x[i];
        }

        System.out.println("Process\tBurst-Time\tArrival-Time\tWaiting-Time\tTurnaround-Time\tCompletion-Time");
        for (int i = 0; i < n; i++) 
        {
            System.out.println("p" + (i + 1) + "\t\t" + x[i] + "\t\t" + at[i] + "\t\t" + waiting[i] + "\t\t" + turnaround[i] + "\t\t" + ct[i]);
        } 
        scanner.close();
    }
}