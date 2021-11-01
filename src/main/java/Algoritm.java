public interface Algoritm {

    public abstract void schedule() throws CloneNotSupportedException;

    public abstract Task pickNextTask();
}
