package be.reactiveprogrammming.demo.topicprocessor;

import java.time.Duration;

import reactor.core.publisher.Flux;
import reactor.core.publisher.TopicProcessor;

public class TopicProcessorTestApplication {

  public static void main(String[] args) throws InterruptedException {
    topicProcessorMultiSubscribe(50);
  }

  private static void topicProcessorMultiSubscribe(int numberOfSubscriptions) throws InterruptedException {
    final TopicProcessor topicProcessor = TopicProcessor.create();

    Flux.interval(Duration.ofMillis(1000)).subscribe(topicProcessor);

    for(int i = 0; i < numberOfSubscriptions; i++) {
      final int subscriptionNumber = i;
      Flux outputFlux = Flux.from(topicProcessor);
      outputFlux.subscribe(out -> System.out.println("Flux " + subscriptionNumber + " " + out));
    }

    Thread.sleep(1000000000);
  }

}
