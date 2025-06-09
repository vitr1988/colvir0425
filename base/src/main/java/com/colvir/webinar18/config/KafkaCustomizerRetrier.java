//package com.colvir.webinar18.config;
//
//import org.springframework.kafka.retrytopic.RetryTopicConfigurationSupport;
//import org.springframework.util.backoff.FixedBackOff;
//
//import java.net.SocketException;
//import java.util.List;
//
//public class KafkaCustomizerRetrier extends RetryTopicConfigurationSupport {
//
//    protected void configureBlockingRetries(BlockingRetriesConfigurer blockingRetries) {
//        blockingRetries.backOff(new FixedBackOff(1000, 3)).retryOn(SocketException.class);
//    }
//
//    /**
//     * Override this method to manage non-blocking retries fatal exceptions.
//     * Records which processing throws an exception present in this list will be
//     * forwarded directly to the DLT, if one is configured, or stop being processed
//     * otherwise.
//     * @param nonBlockingRetriesExceptions a {@link List} of fatal exceptions
//     * containing the framework defaults.
//     */
//    protected void manageNonBlockingFatalExceptions(List<Class<? extends Throwable>> nonBlockingRetriesExceptions) {
//        nonBlockingRetriesExceptions.add(NullPointerException.class);
//    }
//}
