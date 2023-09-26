package jdk21;

import java.time.Duration;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

public class VirtualThreadDemo {

    public static void main(String[] args) {
        Date start = new Date();
        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            IntStream.range(0, 10000).forEach(i -> {
                executor.submit(() -> {
                    try {
                        Thread.sleep(Duration.ofSeconds(1));
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                });
            });
        }

        long virtualThreadExecuteMs = start.getTime() - new Date().getTime();
        System.out.println("Virtual thread execute finished, cost " + virtualThreadExecuteMs);
    }
}
