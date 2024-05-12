package processScheduling;

import java.util.Scanner;

class SJF 
{
    public static void main(String[] args) 
    {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of processes: ");
        int n = scanner.nextInt();
        int time=0;

        int[] pid = new int[n];
        int[] at = new int[n];
        int[] bt = new int[n];
        int[] ct = new int[n];
        int[] tat = new int[n];
        int[] wt = new int[n];

        for (int i = 0; i < n; i++) 
        {
            System.out.printf("Enter the arrival time and burst time of process P%d: ", i + 1);
            at[i] = scanner.nextInt();
            bt[i] = scanner.nextInt();
            pid[i] = i + 1;
        }

        

       
        for (int i = 0; i < n; i++) 
        {
            tat[i] = ct[i] - at[i];
            wt[i] = tat[i] - bt[i];
        }
        System.out.println("Process\tAT\tBT\tCT\tTAT\tWT");
        for (int i = 0; i < n; i++) 
        {
            System.out.printf("P%d\t%d\t%d\t%d\t%d\t%d\n", pid[i], at[i], bt[i], ct[i], tat[i], wt[i]);
        }

        scanner.close();
    }
}