import java.util.Scanner;

public class PriorityPreemptive 
{
    static int maxPri(int[] pri, int[] bt, int[] at, int t, int n) 
    {
        int max = -999, max_index = -1;
        for (int i = 0; i < n; i++) 
        {
            if (at[i] <= t && pri[i] > max && bt[i] > 0) 
            {
                max = pri[i];
                max_index = i;
            }
        }
        return max_index;
    }

    public static void main(String[] args) 
    {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of processes: ");
        int n = scanner.nextInt();

        int[] pri = new int[n];
        int[] pro = new int[n];
        int[] at = new int[n];
        int[] bt = new int[n];
        int[] wt = new int[n];
        int[] tat = new int[n];
        int[] ct = new int[n];
        int[] ogbt = new int[n];

        System.out.println("Enter the process IDs, priorities, arrival times, and burst times:");
        for (int i = 0; i < n; i++) 
        {
            System.out.print("Process ID: ");
            pro[i] = scanner.nextInt();
            System.out.print("Priority: ");
            pri[i] = scanner.nextInt();
            System.out.print("Arrival Time: ");
            at[i] = scanner.nextInt();
            System.out.print("Burst Time: ");
            bt[i] = scanner.nextInt();
            ogbt[i] = bt[i];
        }

        int count = 0, t = 0, maxInd=0, ind = 0, ttat = 0, twt = 0;
        float awt, atat;
        int[] gant = new int[100];
        int[] cgant = new int[100];

        while (count != n) 
        {
            maxInd = maxPri(pri, bt, at, t, n);
            if (pro[maxInd] != gant[ind - 1]) 
            {
                gant[ind] = pro[maxInd];
                cgant[ind] = t;
                ind++;
            } 
            else 
            {
                cgant[ind] = t;
            }

            bt[maxInd]--;
            t++;

            if (bt[maxInd] == 0) 
            {
                count++;
                ct[maxInd] = t;
                tat[maxInd] = ct[maxInd] - at[maxInd];
                wt[maxInd] = tat[maxInd] - ogbt[maxInd];
                ttat += tat[maxInd];
                twt += wt[maxInd];
            }
        }

        awt = (float) twt / n;
        atat = (float) ttat / n;

        System.out.println("Process\tprio\tAT\tBT\tCT\tTAT\tWT");
        for (int i = 0; i < n; i++)
        {
            System.out.printf("\n\t%d\t%d\t\t%d\t%d\t%d\t%d\t%d\n", pro[i], pri[i], at[i], bt[i], ct[i], tat[i], wt[i]);
        }

        System.out.printf("\nAvg TAT=%.2f\nAvg WT=%.2f", atat, awt);
        System.out.println("\nGant Chart \n");

        for (int i = 0; i < n; i++) 
        {
            System.out.printf(" [%d|P%d] ", cgant[i], gant[i]);
        }
        System.out.print(" " + ct[maxInd]);
        scanner.close();
    }
}