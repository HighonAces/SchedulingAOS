import java.util.ArrayList;
import java.util.List;

public class RR implements Algoritm {

    List<Task> taskList;
    List<Task> pendingTaskList;
    List<Integer> turnaroundTimeList;
    List<Integer> waitingTimeList;
    Integer waitingTime;
    Integer taskIndex;
    Integer timeQuantum;
    Integer totalTime;

    public RR(List<Task> taskList) {
        this.taskList = taskList;
        this.pendingTaskList = new ArrayList< >();
        this.turnaroundTimeList = new ArrayList<>();
        this.waitingTimeList = new ArrayList<>();
        this.waitingTime = 0;
        this.taskIndex = 0;
        this.timeQuantum = 10;
        this.totalTime = 0;
    }

    public void deepCopy() throws CloneNotSupportedException {
        for (Task task : taskList) {
            pendingTaskList.add(task.clone());
        }
    }

    @Override
    public void schedule() throws CloneNotSupportedException {
        deepCopy();
        System.out.println("Priority with Roundrobin Scheduling:\n ");
        while (pendingTaskList.size() > 0) {
            Task nextTask = pickNextTask();
            if(nextTask.getBurst() >= timeQuantum) {
                System.out.println("Now executing " + nextTask);
                totalTime += timeQuantum;
                nextTask.setBurst(pendingTaskList.get(taskIndex).getBurst() - timeQuantum);
                if(taskIndex == pendingTaskList.size() - 1) {
                    taskIndex = 0;
                } else {
                    taskIndex++;
                }
            } else {
                totalTime += nextTask.getBurst();
                nextTask.setBurst(0);
                for(Task task : taskList) {
                    if(nextTask.getTaskId() == task.getTaskId()) {
                        waitingTimeList.add(totalTime - task.getBurst());
                        turnaroundTimeList.add(totalTime);

                        break;
                    }
                }
                System.out.println("Removing task" + pendingTaskList.get(taskIndex));
                System.out.println("Waiting time for task" + pendingTaskList.get(taskIndex).getName() + " is " + waitingTimeList.get(waitingTimeList.size() - 1));
                pendingTaskList.remove((int) taskIndex);
                if(taskIndex >= pendingTaskList.size() ) {
                    taskIndex = 0;
                }
            }
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

        return pendingTaskList.get(taskIndex);
    }
}
