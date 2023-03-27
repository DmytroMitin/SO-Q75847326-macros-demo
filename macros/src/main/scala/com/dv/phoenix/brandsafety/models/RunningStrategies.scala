package com.dv.phoenix.brandsafety.models

import com.dv.phoenix.brandsafety.models.RunningStrategy.RunningStrategy
import com.dv.phoenix.brandsafety.utils.RunningStrategiesMacros

import scala.language.experimental.macros
case class RunningStrategies private (runningStrategies: Set[RunningStrategy])
object RunningStrategies {
  def apply(strategies: RunningStrategy*): RunningStrategies = macro RunningStrategiesMacros.applyImp
}
