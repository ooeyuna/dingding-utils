package ooeyuna.rika.tools

import org.slf4j.Logger
import org.slf4j.LoggerFactory


/**
 * Not Support Java File
 *
 * Created by sanairika on 2017/02/22.
 */
inline fun <reified T> T.log(action: (Logger) -> (Unit)) {
  val logger = LoggerFactory.getLogger(T::class.qualifiedName)
  action.invoke(logger)
}

inline fun <reified T> T.log_debug(msg: String, ex: Throwable? = null) {
  val logger = LoggerFactory.getLogger(T::class.qualifiedName)
  if (logger.isDebugEnabled || logger.isTraceEnabled) {
    if (ex == null) logger.debug(msg) else logger.debug(msg, ex)
  }
}