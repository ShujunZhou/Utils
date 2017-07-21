package cn.smart.test.java8.stream;

import java.util.Arrays;
import java.util.Collection;

/**
 * Created by shu on 2017/7/21.
 * Stream API
 */
public class Streams {
    private enum Status {
        OPEN,
        CLOSED
    };
    private static final class Task {
        private final Status status;
        private final Integer points;

        public Task(Status status, Integer points) {
            this.status = status;
            this.points = points;
        }

        public Status getStatus() {
            return status;
        }

        public Integer getPoints() {
            return points;
        }

        @Override
        public String toString() {
            return String.format("[%s, %d]", status, points);
        }
    }

    public static void main(String[] args) {
        final Collection<Task> tasks = Arrays.asList(
                new Task(Status.OPEN, 5),
                new Task(Status.OPEN, 13),
                new Task(Status.CLOSED, 8)
        );
        final long totalPointsOfTasks = tasks
                .stream()
                .filter(task -> task.getStatus() == Status.OPEN)
                .mapToInt(Task::getPoints)
                .sum();

        System.out.println("TotalPointsOfTasks" + totalPointsOfTasks);

    }
}
