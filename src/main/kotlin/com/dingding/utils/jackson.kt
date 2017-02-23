package com.dingding.utils

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.KotlinModule

/**
 * Created by sanairika on 2017/02/22.
 */
object Jackson {
  val mapper = ObjectMapper()
      .registerModule(KotlinModule())
      .registerModule(JavaTimeModule())
      .registerModule(Jdk8Module())
//      .registerModule(ParameterNamesModule(JsonCreator.Mode.PROPERTIES))
      .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
      .disable(SerializationFeature.WRITE_NULL_MAP_VALUES)
      .enable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
      .disable(SerializationFeature.WRITE_DATE_TIMESTAMPS_AS_NANOSECONDS)
      .setSerializationInclusion(JsonInclude.Include.NON_NULL)

}