package bravura.sonata.buddy.common.quietperiod;

public interface QuietPeriodListener<T> {
    void quietPeriodFinished(T token);
}
