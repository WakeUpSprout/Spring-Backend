package wakeup.sprout.spring.common.excutable;

public interface Executable<T, U> {
    U execute(T request);
}
