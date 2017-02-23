package com.dingding.utils

import com.fasterxml.jackson.databind.JsonNode
import org.powermock.api.mockito.PowerMockito.*
import java.lang.reflect.Modifier
import java.nio.file.Paths


/**
 * Created by sanairika on 2017/02/22.
 */
object TestUtils {

  fun <T> whenDo(mock: T) = `when`(mock)

  inline fun <reified T : Any> mockObject(): T {
    val klass = T::class.java
    val obj = mock<T>()
    val f = field(klass, "INSTANCE");
    f.set(klass, obj)
    return obj
  }


  inline fun <reified T : Any> mockComanionObject(): T {
    val comanionKlass = T::class.java
    val obj = mock<T>()
    whenNew(comanionKlass).withAnyArguments().thenReturn(obj)
    return obj
  }

  inline fun <reified T : Any> mockFinalField(obj: Any, field: String): T {
    val v = mock<T>()
    val f = field(obj.javaClass, field)
    f.isAccessible = true
    val mf = f.javaClass.getDeclaredField("modifiers")
    mf.isAccessible = true
    mf.setInt(f, f.modifiers.and(Modifier.FINAL.inv()))
    f.set(obj, v)
    return v
  }

  inline fun <reified T : Any> mock() = mock(T::class.java)

  fun read_jsonnode(path: String): JsonNode = Jackson.mapper.readTree(Paths.get("src/test/resources").resolve(path).toUri().toURL())

  inline fun <reified T : Any> read_json(path: String): T = Jackson.mapper.treeToValue(read_jsonnode(path), T::class.java)

}
