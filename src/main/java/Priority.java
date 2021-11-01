import java.util.ArrayList;
import java.util.List;

public class Priority implements Algoritm {

    List<Task> taskList;
    List<Task> pendingTaskList;
    List<Integer> turnaroundTimeList;
    List<Integer> waitingTimeList;
    Integer waitingTime;

    public Priority(List<Task> taskList) {
        this.taskList = taskList;
        this.pendingTaskList = new ArrayList<>();
        this.turnaroundTimeList = new ArrayList<>();
        this.waitingTimeList = new ArrayList<>();
        this.waitingTime = 0;
    }

    @Override
    public void schedule() {
        System.out.println("Priority with Priority Scheduling:\n ");
        while (taskList.size() > 0) {
            Task task = pickNextTask();
            System.out.println(task.getName() + " waiting time is " + waitingTime);
            waitingTimeList.add(waitingTime);
            System.out.println("Now executing " + task);
            waitingTime += task.getBurst();
            turnaroundTimeList.add(waitingTime);  //as we are adding burst time to waiting time, it becomes turnaround time for the process
        }
        averageWaitingTime();
        averageTurnaroundTime();
    }

    private void averageTurnaroundTime() {
        int totalTurnaroundTime = 0;
        for (int time : turnaroundTimeList) {
            totalTurnaroundTime += time;
        }
        System.out.println("Total turnaround time is: " + totalTurnaroundTime);
        System.out.println("Average turnaround time is: " + totalTurnaroundTime / turnaroundTimeList.size());
    }

    private void averageWaitingTime() {
        int totalWaitingTime = 0;
        for (int time : waitingTimeList) {
            totalWaitingTime += time;
        }
        System.out.println("The average waiting time is: " + totalWaitingTime / waitingTimeList.size());
    }

    @Override
    public Task pickNextTask() {
        int priorityTaskIndex = 0;
        int priority = taskList.get(0).getPriority();
        for(int i = 0; i < taskList.size(); i++) {
            if(priority < taskList.get(i).getPriority()) {
                priority = taskList.get(i).getPriority();
                priorityTaskIndex = i;
            }
        }
        pendingTaskList.add(taskList.get(priorityTaskIndex));
        taskList.remove(taskList.get(priorityTaskIndex));
        return pendingTaskList.get(pendingTaskList.size() - 1);
    }
}
