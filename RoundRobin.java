import java.util.Scanner;

public class RoundRobin 
{
    public static void main(String[] args) 
    {
        Scanner scanner = new Scanner(System.in);
        System.out.print(" Total number of processes in the system: ");
        int NOP = scanner.nextInt();

        int[] at = new int[NOP];
        int[] bt = new int[NOP];
        int[] temp = new int[NOP];

        for (int i = 0; i < NOP; i++) 
        {
            System.out.println("\n Enter the Arrival and Burst time of the Process[" + (i + 1) + "]");
            System.out.print(" Arrival time is: \t");
            at[i] = scanner.nextInt();
            System.out.print(" \nBurst time is: \t");
            bt[i] = scanner.nextInt();
            temp[i] = bt[i];
        }

        System.out.print("Enter the Time Quantum for the process: \t");
        int quant = scanner.nextInt();

        System.out.println("\n Process No \t\t Burst Time \t\t TAT \t\t Waiting Time ");

        int sum = 0, y = NOP, wt = 0, tat = 0;
        boolean[] visited = new boolean[NOP];

        while (y != 0) 
        {
            for (int i = 0; i < NOP; i++) 
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
                    System.out.println("Process No[" + (i + 1) + "] \t\t " + bt[i] + "\t\t\t\t " + (sum - at[i]) + "\t\t\t " + (sum - at[i] - bt[i]));
                    wt += sum - at[i] - bt[i];
                    tat += sum - at[i];
                    visited[i] = false;
                }
            }
        }

        float avg_wt = (float) wt / NOP;
        float avg_tat = (float) tat / NOP;

        System.out.println("\n Average Turn Around Time: \t" + avg_tat);
        System.out.println("\n Average Waiting Time: \t" + avg_wt);
        scanner.close();
    }
}