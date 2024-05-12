package processScheduling;
import java.util.*;

class RoundRobin
{
    public static void main(String[] args) 
    {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter the number of processes: ");
        int n=sc.nextInt();

        int pid[]=new int[n];
        int at[]=new int[n];
        int bt[]=new int[n];
        int ct[]=new int[n];
        int tat[]=new int[n];
        int wt[]=new int[n];
        int x[]=new int[n];
        System.out.println("Enter the arrival time and burst time of each process: ");
        for(int i=0;i<n;i++)
        {
            System.out.printf("Enter the arrival time and burst time of process P%d: ", i + 1);
            at[i]=sc.nextInt();
            bt[i]=sc.nextInt();
            x[i]=bt[i]; 
            pid[i]=i+1;
        }
        System.out.println("Enter the time quantum: ");
        int tq=sc.nextInt();
        int time=0,y=n,used[]=new int[n];

        Queue<Integer> queue=new LinkedList<>();
        int index=-1;

        while(y!=0)
        {
            for(int i=0;i<n;i++)
            {
                if(at[i]<=time && used[i]==0)
                {
                    queue.add(i);
                    used[i]=1;
                }
            }
            if(index!=-1 && bt[index]!=0)
            {
                queue.add(index);
            }
            if(queue.isEmpty())
            {
                time++;
            }
            else
            {
                index=queue.peek();
                queue.remove();
                if(bt[index]<=tq)
                {
                    time=time+bt[index];
                    ct[index]=time;
                    bt[index]=0;
                    y--;
                }
                else
                {
                    time=time+tq;
                    bt[index]=bt[index]-tq;
                }   
            }
        }
        for(int i=0;i<n;i++)
        {
            tat[i]=ct[i]-at[i];
            wt[i]=tat[i]-x[i];
        }
        System.out.println("Process\tAT\tBT\tCT\tTAT\tWT");
        for(int i=0;i<n;i++)
        {
            System.out.printf("P%d\t%d\t%d\t%d\t%d\t%d\n",pid[i],at[i],x[i],ct[i],tat[i],wt[i]);
        }

        sc.close();
    }
}