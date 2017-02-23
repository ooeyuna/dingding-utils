package com.dingding.utils

import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.powermock.core.classloader.annotations.PowerMockIgnore
import org.powermock.core.classloader.annotations.PrepareForTest
import org.powermock.modules.junit4.PowerMockRunner

/**
 * Created by sanairika on 2017/02/22.
 */
@PrepareForTest(fullyQualifiedNames = arrayOf("com.dingding.utils.*"))
@PowerMockIgnore("javax.management.*")
@RunWith(PowerMockRunner::class)
class TestForTestUtils {

  @Test
  fun test_read_jsonnode() {
    val node = TestUtils.read_jsonnode("a.json")
    Assert.assertEquals(node["ttt"].asInt(), 123)
  }

  @Test
  fun test_read_json() {
    val a = TestUtils.read_json<AJson>("a.json")
    Assert.assertEquals(a.ttt, 123)
    Assert.assertEquals(a.hash.tt1, 123)
    Assert.assertEquals(a.arr[0], 123)
    Assert.assertNull(a.ne)
    Assert.assertNull(a.neh)
  }

  @Test
  fun test_when() {
    val f = TestUtils.mock<ForMock>()
    TestUtils.whenDo(f.test()).thenReturn(345)
    val r = f.test()
    Assert.assertEquals(r, 345)
  }

  @Test
  fun test_mock_object() {
    val f = TestUtils.mockObject<ObForMock>()
    TestUtils.whenDo(f.test()).thenReturn(345)
    val r = ObForMock.test()
    Assert.assertEquals(r, 345)
  }

  @Test
  fun test_mock_companion_object() {
    val f = TestUtils.mockComanionObject<ComObForMock.Companion>()
    TestUtils.whenDo(f.test()).thenReturn(345)
    val r = ComObForMock.test()
    Assert.assertEquals(r, 345)
  }

  @Test
  fun test_mock_object_final() {
    val oi = ForMockFinal::class.objectInstance!!
    val v = TestUtils.mockFinalField<FinalField>(oi, "ff")
    TestUtils.whenDo(v.test()).thenReturn(345)
    val r = ForMockFinal.ff.test()
    Assert.assertEquals(r, 345)
  }

  @Test
  fun test_mock_class_final() {
    val oi = ForMockFinalClass()
    val v = TestUtils.mockFinalField<FinalField>(oi, "ff")
    TestUtils.whenDo(v.test()).thenReturn(345)
    val r = oi.ff.test()
    Assert.assertEquals(r, 345)
  }

}

data class AJson(
    val ttt: Int
    , val tt2: String
    , val hash: Hash
    , val arr: List<Int>
    , val ne: String?
    , val neh: Map<String, String>?
)

data class Hash(val tt1: Int)

class ForMock() {

  fun test() = 123
}

object ObForMock {
  fun test() = 123
}

class ComObForMock {
  companion object {
    fun test() = 123
  }
}

object ForMockFinal {
  val ff = FinalField()
}

class FinalField() {
  fun test() = 123
}

class ForMockFinalClass {
  val ff = FinalField()
}