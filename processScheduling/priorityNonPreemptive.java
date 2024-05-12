package processScheduling;
import java.util.Scanner;

public class priorityNonPreemptive {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int[] bt = new int[20];
        int[] p = new int[20];
        int[] at = new int[20]; // Arrival time
        int[] wt = new int[20];
        int[] tat = new int[20];
        int[] pr = new int[20];
        int i, j, n, total = 0, pos, temp, avg_wt, avg_tat;

        System.out.print("Enter Total Number of Processes:");
        n = input.nextInt();

        System.out.println("\nEnter Burst Time, Arrival Time, and Priority");
        for (i = 0; i < n; i++) {
            System.out.println("\nP[" + (i + 1) + "]");
            System.out.print("Burst Time:");
            bt[i] = input.nextInt();
            System.out.print("Arrival Time:");
            at[i] = input.nextInt();
            System.out.print("Priority:");
            pr[i] = input.nextInt();
            p[i] = i + 1;
        }

        // Sorting based on Arrival Time
        for (i = 0; i < n; i++) {
            pos = i;
            for (j = i + 1; j < n; j++) {
                if (at[j] < at[pos])
                    pos = j;
            }

            temp = at[i];
            at[i] = at[pos];
            at[pos] = temp;

            temp = pr[i];
            pr[i] = pr[pos];
            pr[pos] = temp;

            temp = bt[i];
            bt[i] = bt[pos];
            bt[pos] = temp;

            temp = p[i];
            p[i] = p[pos];
            p[pos] = temp;
        }

        wt[0] = 0;
        for (i = 1; i < n; i++) {
            wt[i] = 0;
            for (j = 0; j < i; j++)
                wt[i] += bt[j];

            total += wt[i];
        }

        avg_wt = total / n;
        total = 0;

        System.out.println("\nProcess\t    Burst Time    \tWaiting Time\tTurnaround Time");
        for (i = 0; i < n; i++) {
            tat[i] = bt[i] + wt[i];
            total += tat[i];
            System.out.println("P[" + p[i] + "]\t\t  " + bt[i] + "\t\t    " + wt[i] + "\t\t\t" + tat[i]);
        }

        avg_tat = total / n;
        System.out.println("\nAverage Waiting Time=" + avg_wt);
        System.out.println("Average Turnaround Time=" + avg_tat);

        input.close();
    }
}
