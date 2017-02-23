package com.dingding.utils

import org.apache.curator.framework.CuratorFrameworkFactory
import org.apache.curator.framework.imps.CuratorFrameworkImpl
import org.apache.curator.retry.RetryOneTime
import org.junit.Assert
import org.junit.Test
import java.util.concurrent.CountDownLatch
import java.util.concurrent.ThreadLocalRandom

/**
 * Created by sanairika on 2017/02/22.
 */
class ZookeeperTest {

  @Test
  fun assert_true() {
    Assert.assertTrue(true)
  }

  //  @Test
  fun real_test() {
    val cd = CountDownLatch(10)
    10.downTo(1).forEachIndexed { index, i ->
      val t = Thread {
        val client = CuratorFrameworkImpl(CuratorFrameworkFactory
            .builder()
            .namespace("dev_test")
            .connectionTimeoutMs(1000)
            .sessionTimeoutMs(1000)
            .retryPolicy(RetryOneTime(100))
            .connectString("dev:2181"))
        client.start()
        client.blockUntilConnected()
        Thread.sleep(ThreadLocalRandom.current().nextInt(10, 100).toLong())
        val leader = ZkLeaderSelectorImpl("/leader_test", client, 500)
        Thread.sleep(10000)
        log {
          it.info("${Thread.currentThread().name} get master: ${leader.isleader()}")
        }
        cd.countDown()
      }
      t.name = "thread $index"
      t.start()
    }
    cd.await()
  }

}