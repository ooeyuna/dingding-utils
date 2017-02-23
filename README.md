# Kotlin Tools

## Gradle Dependency

-  add repo

```groovy

repositories {
    mavenLocal()
    maven {
        url "http://dl.bintray.com/ooeyuna/rika"
    }
    jcenter()
    mavenCentral()
}

```

-  dependency

```groovy

    compile "com.dingding.utils:dingding-utils:1.0"

```

## Exceptions

For WebServer

## Jackson

Default ObjectMapper with module `jsr310`,`jdk8`,`kotlin`

version 2.8.7

```kotlin
  
  Jackson.mapper.readValue<SomeObject>("")

```

## Leader

Leader Selector, implement by zk(curator recipes)

version 2.10.0

```kotlin

      val leader = ZkLeaderSelectorImpl("/leader_test", client, 500)

```

## Log

kotlin extension,use logback-classic

version 1.1.7

```kotlin
  
  //at every kotlin class
  log{
    it.info("123")
  }

  log_debug("432") //check logger debug enabled

```

## mock test

mock object

```kotlin

    val f = TestUtils.mockObject<ObForMock>()
    TestUtils.whenDo(f.test()).thenReturn(345)
    val r = ObForMock.test()
    Assert.assertEquals(r, 345)

```

mock class

```kotlin

    val f = TestUtils.mock<ForMock>()
    TestUtils.whenDo(f.test()).thenReturn(345)
    val r = f.test()
    Assert.assertEquals(r, 345)

```

mock companion object

```kotlin

    val f = TestUtils.mockComanionObject<ComObForMock.Companion>()
    TestUtils.whenDo(f.test()).thenReturn(345)
    val r = ComObForMock.test()
    Assert.assertEquals(r, 345)

```

mock object final field

```kotlin

    val oi = ForMockFinal::class.objectInstance!!
    val v = TestUtils.mockFinalField<FinalField>(oi, "ff")
    TestUtils.whenDo(v.test()).thenReturn(345)
    val r = ForMockFinal.ff.test()
    Assert.assertEquals(r, 345)

```

mock class final field

```kotlin

    val oi = ForMockFinalClass()
    val v = TestUtils.mockFinalField<FinalField>(oi, "ff")
    TestUtils.whenDo(v.test()).thenReturn(345)
    val r = oi.ff.test()
    Assert.assertEquals(r, 345)

```

### TODO

1. Mockito.verify cannot work.(kotlin null-safety limit)
