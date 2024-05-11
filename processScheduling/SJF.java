package processScheduling;

import java.util.Scanner;

class SJF 
{
    public static void main(String[] args) 
    {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of processes: ");
        int n = scanner.nextInt();

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

        for (int i = 0; i < n - 1; i++) 
        {
            for (int j = 0; j < n - i - 1; j++) 
            {
                if (bt[j] > bt[j + 1]) 
                {
                    int temp = bt[j];
                    bt[j] = bt[j + 1];
                    bt[j + 1] = temp;

                    temp = at[j];
                    at[j] = at[j + 1];
                    at[j + 1] = temp;

                    temp = pid[j];
                    pid[j] = pid[j + 1];
                    pid[j + 1] = temp;
                }
            }
        }

        int time = 0;
        int completed = 0;
        boolean[] isCompleted = new boolean[n];

        while (completed != n) 
        {
            int minIndex = -1;
            int minBurstTime = Integer.MAX_VALUE;

            for (int i = 0; i < n; i++) 
            {
                if (!isCompleted[i] && at[i] <= time && bt[i] < minBurstTime) 
                {
                    minIndex = i;
                    minBurstTime = bt[i];
                }
            }

            if (minIndex == -1) 
            {
                time++;
                continue;
            }

            ct[minIndex] = time + bt[minIndex];
            tat[minIndex] = ct[minIndex] - at[minIndex];
            wt[minIndex] = tat[minIndex] - bt[minIndex];
            isCompleted[minIndex] = true;
            completed++;
            time = ct[minIndex];
        }

        float avgTat = 0, avgWt = 0;
        for (int i = 0; i < n; i++) 
        {
            avgTat += tat[i];
            avgWt += wt[i];
        }
        avgTat /= n;
        avgWt /= n;

        System.out.println("Process\tAT\tBT\tCT\tTAT\tWT");
        for (int i = 0; i < n; i++) 
        {
            System.out.printf("P%d\t%d\t%d\t%d\t%d\t%d\n", pid[i], at[i], bt[i], ct[i], tat[i], wt[i]);
        }
        System.out.printf("Average Turnaround Time: %.2f\n", avgTat);
        System.out.printf("Average Waiting Time: %.2f\n", avgWt);

        scanner.close();
    }
}