package com.dingding.utils

import org.junit.Test

/**
 * Created by sanairika on 2017/02/22.
 */
class TestLog {

  @Test
  fun test_log() {
    log {
      it.info("123")
    }
  }

  @Test
  fun test_debug() {
    log_debug("test123")
  }

}

class TestLog2 {
  @Test
  fun test_debug_at_debugdisabled() {
    //will not output
    log_debug("test123")
  }
}