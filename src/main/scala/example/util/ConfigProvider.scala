package example.util

import com.typesafe.config.{Config, ConfigFactory}

/** Provides access to values in the configuration file. */
object ConfigProvider {
  
  /** Config object containing all configuration values. */
  val config: Config = ConfigFactory.load()
}
