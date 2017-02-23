package ooeyuna.rika.tools

import org.apache.curator.framework.CuratorFramework
import org.apache.curator.framework.recipes.leader.LeaderSelectorListenerAdapter

/**
 * Created by sanairika on 2017/02/22.
 */
interface LeaderSelector {
  fun isleader(): Boolean
}

abstract class LeaderSelectorAdpater(sleep: Long = 250L,
                                     tobe_master: () -> (Boolean) = { true }) : LeaderSelector

class ZkLeaderSelectorImpl(path: String
                           , client: CuratorFramework,
                           sleep: Long,
                           tobe_master: () -> (Boolean) = { true }) : LeaderSelectorAdpater(sleep, tobe_master) {
  private val leader = org.apache.curator.framework.recipes.leader.LeaderSelector(client, path, LeaderSelectorListener(sleep, tobe_master))

  init {
    leader.start()
  }

  override fun isleader(): Boolean = leader.hasLeadership()

}


private class LeaderSelectorListener(val sleep: Long, val tobe_master: () -> (Boolean)) : LeaderSelectorListenerAdapter() {
  override fun takeLeadership(client: CuratorFramework) {
    while (tobe_master.invoke()) {
      Thread.sleep(sleep)
      log {
        it.debug("be master")
      }
    }
  }
}