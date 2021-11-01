import java.util.ArrayList;
import java.util.List;

public class FCFS implements Algoritm {
    List<Task> taskList;
    List<Task> pendingTaskList;
    List<Integer> turnaroundTimeList;
    List<Integer> waitingTimeList;
    Integer waitingTime;

    public FCFS(List<Task> taskList) {
    this.taskList = taskList;
    this.pendingTaskList = taskList;
    this.turnaroundTimeList = new ArrayList<>();
    this.waitingTimeList = new ArrayList<>();
    this.waitingTime = 0;
    }

    @Override
    public void schedule() {
        System.out.println("Priority with FCFS Scheduling:\n");
    while (pendingTaskList.size() > 0) {
        Task task = pickNextTask();
        System.out.println(task.getName() + " waiting time is " + waitingTime);
        waitingTimeList.add(waitingTime);
        System.out.println("Now executing " + task);
        waitingTime += task.getBurst();
        turnaroundTimeList.add(waitingTime);  //as we are adding burst time to waiting time, it becomes turnaround time for the process
        pendingTaskList.remove(0);
    }
    averageWaitingTime();
    averageTurnaroundTime();
    }

    private void averageWaitingTime() {
        int totalWaitingTime = 0;
        for (int time : waitingTimeList) {
            totalWaitingTime += time;
        }
        System.out.println("The average waiting time is: " + totalWaitingTime / (waitingTimeList.size() * 1.0));
    }

    private void averageTurnaroundTime() {
        int totalTurnaroundTime = 0;
        for (int time : turnaroundTimeList) {
            totalTurnaroundTime += time;
        }
        System.out.println("Total turnaround time is: " + totalTurnaroundTime);
        System.out.println("Average turnaround time is: " + totalTurnaroundTime / (turnaroundTimeList.size() * 1.0));
    }

    @Override
    public Task pickNextTask() {
        return pendingTaskList.get(0);
    }
}