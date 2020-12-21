package ChatPlatform;

/**
 *
 * This class is interface that provide producer methods.
 *
 */

public interface StringProducer {
    public void addConsumer(StringConsumer sc);
    public void removeConsumer(StringConsumer sc);
}
