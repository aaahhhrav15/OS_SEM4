package processScheduling;
import java.util.Scanner;

class FCFS {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of processes: ");
        int n = scanner.nextInt();
        int time=0;

        int[] at = new int[n]; 
        int[] bt = new int[n]; 
        int[] ct = new int[n]; 
        int[] tat = new int[n];
        int[] wt = new int[n];

        int totalTat = 0, totalWt = 0;

        for (int i = 0; i < n; i++) {
            System.out.printf("Enter the arrival time and burst time of process P%d: ", i + 1);
            at[i] = scanner.nextInt();
            bt[i] = scanner.nextInt();
        }

        ct[0] = bt[0] + at[0]; 
        for (int i = 1; i < n; i++) 
        {
            time=0;
            if(at[i]>ct[i-1])
            {
                time=at[i]-ct[i-1];
            }
            ct[i] = ct[i - 1] + bt[i] + time;
        }

        
        for (int i = 0; i < n; i++) {
            tat[i] = ct[i] - at[i];
            wt[i] = tat[i] - bt[i];
            totalTat += tat[i];
            totalWt += wt[i];
        }

        // Print the results
        System.out.println("Process\tAT\tBT\tCT\tTAT\tWT");
        for (int i = 0; i < n; i++) {
            System.out.printf("P%d\t%d\t%d\t%d\t%d\t%d\n", i + 1, at[i], bt[i], ct[i], tat[i], wt[i]);
        }

        System.out.printf("Average Turnaround Time: %.2f\n", (float) totalTat / n);
        System.out.printf("Average Waiting Time: %.2f\n", (float) totalWt / n);
        scanner.close();
    }
}