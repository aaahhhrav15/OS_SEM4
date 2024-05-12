package processScheduling;
import java.util.Scanner;

public class RoundRobin 
{
    public static void main(String[] args) 
    {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Total number of processes in the system: ");
        int n = scanner.nextInt();

        int[] at = new int[n];
        int[] bt = new int[n];
        int[] temp = new int[n];

        for (int i = 0; i < n; i++) 
        {
            System.out.println("\nEnter the Arrival and Burst time of the Process[" + (i + 1) + "]");
            System.out.print("Arrival time is: ");
            at[i] = scanner.nextInt();
            System.out.print("Burst time is: ");
            bt[i] = scanner.nextInt();
            temp[i] = bt[i];
        }

        System.out.print("Enter the Time Quantum for the process: \t");
        int quant = scanner.nextInt();

        System.out.println("\nProcess No \t Arrival Time \t Burst Time \t Completion \t TAT \t Waiting Time ");

        int sum = 0, y = n, wt = 0, tat = 0;
        boolean[] visited = new boolean[n];

        while (y != 0) 
        {
            for (int i = 0; i < n; i++) 
            {
                if (temp[i] > 0 && temp[i] <= quant) 
                {
                    sum += temp[i];
                    temp[i] = 0;
                    visited[i] = true;
                } 
                else if (temp[i] > quant) 
                {
                    temp[i] -= quant;
                    sum += quant;
                }

                if (temp[i] == 0 && visited[i]) 
                {
                    y--;
                    System.out.println("Process No[" + (i + 1) + "] \t\t " + at[i]+ "\t\t" + bt[i] + "\t" + sum + "\t\t" + (sum - at[i]) + "\t\t " + (sum - at[i] - bt[i]));
                    wt += sum - at[i] - bt[i];
                    tat += sum - at[i];
                    visited[i] = false;
                }
            }
        }

        float avg_wt = (float) wt / n;
        float avg_tat = (float) tat / n;

        System.out.println("\n Average Turn Around Time: \t" + avg_tat);
        System.out.println("\n Average Waiting Time: \t" + avg_wt);
        scanner.close();
    }
}