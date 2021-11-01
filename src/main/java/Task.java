import java.util.concurrent.atomic.AtomicInteger;

public class Task implements Cloneable, Comparable<Task>{
    private int taskId;
    private String name;
    private int priority;
    private int burst;

    private static AtomicInteger atomicInteger = new AtomicInteger();

    public Task(String name, int priority, int burst) {
        this.name = name;
        this.priority = priority;
        this.burst = burst;

        this.taskId = atomicInteger.getAndIncrement();
    }

    public int getTaskId() {
        return taskId;
    }

    public String getName() {
        return name;
    }

    public int getPriority() {
        return priority;
    }

    public int getBurst() {
        return burst;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public void setBurst(int burst) {
        this.burst = burst;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return this.taskId == task.taskId;
    }

    @Override
    public String toString() {
        return "Task{" +
                "taskId=" + taskId +
                ", name='" + name + '\'' +
                ", priority=" + priority +
                ", burst=" + burst +
                '}';
    }

    public Task clone() throws CloneNotSupportedException {
        Task newTask = (Task) super.clone();
        newTask.setTaskId(newTask.getTaskId());
        newTask.setPriority(newTask.getPriority());
        newTask.setName(newTask.getName());
        newTask.setBurst(newTask.getBurst());
        return newTask;
    }

    @Override
    public int compareTo(Task task) {
        int priority=((Task)task).getPriority();
        return this.priority - priority;
    }
}
