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

        int[][] processes = new int[n][3];
        for (int i = 0; i < n; i++) 
        {
            processes[i][0] = pid[i];
            processes[i][1] = at[i];
            processes[i][2] = bt[i];
        }

        for (int i = 0; i < n - 1; i++) 
        {
            for (int j = 0; j < n - i - 1; j++) 
            {
                if (processes[j][1] > processes[j + 1][1]) 
                {
                    int[] temp = processes[j];
                    processes[j] = processes[j + 1];
                    processes[j + 1] = temp;
                }
            }
        }

        int time = processes[0][1]; 
        int completed = 0; 
        boolean[] isCompleted = new boolean[n]; 

        while (completed != n) 
        {
            int minIndex = -1;
            int minBurstTime = Integer.MAX_VALUE;

            for (int i = 0; i < n; i++) 
            {
                if (!isCompleted[i] && processes[i][1] <= time && processes[i][2] < minBurstTime) 
                {
                    minIndex = i;
                    minBurstTime = processes[i][2];
                }
            }

            if (minIndex == -1) 
            {
                time++;
                continue;
            }

            ct[minIndex] = time + processes[minIndex][2];
            tat[minIndex] = ct[minIndex] - processes[minIndex][1];
            wt[minIndex] = tat[minIndex] - processes[minIndex][2];
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
            System.out.printf("P%d\t%d\t%d\t%d\t%d\t%d\n", processes[i][0], processes[i][1], processes[i][2], ct[i], tat[i], wt[i]);
        }
        System.out.printf("Average Turnaround Time: %.2f\n", avgTat);
        System.out.printf("Average Waiting Time: %.2f\n", avgWt);
    }
}