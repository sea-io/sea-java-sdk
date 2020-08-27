package com.common.utils;

import java.util.concurrent.locks.Lock;

/**
 * AutoClosable Lock wrapper. Use case: try (ALock l = wLock.lock()) { // do smth under lock }
 */
public final class SLock implements AutoCloseable {

  private final Lock lock;

  public SLock(Lock l) {
    this.lock = l;
  }

  public final SLock lock() {
    this.lock.lock();
    return this;
  }

  public final void close() {
    this.lock.unlock();
  }
}