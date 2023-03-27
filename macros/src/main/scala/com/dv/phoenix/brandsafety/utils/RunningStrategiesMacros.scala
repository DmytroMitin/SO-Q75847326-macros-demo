package com.dv.phoenix.brandsafety.utils

import com.dv.phoenix.brandsafety.models.RunningStrategies
import com.dv.phoenix.brandsafety.models.RunningStrategy.RunningStrategy

import scala.reflect.macros.blackbox

object RunningStrategiesMacros {
  def applyImp(c: blackbox.Context)(strategies: c.Expr[RunningStrategy]*): c.Expr[RunningStrategies] = {
    import c.universe._

    val strategySet = strategies.map(_.tree).toList
    if (strategySet.contains(q"MonitoringAndBlockingInherent") && strategySet.contains(q"MonitoringAndBlockingRecalculate")) {
      c.abort(c.enclosingPosition, "MonitoringAndBlockingInherent and MonitoringAndBlockingRecalculate cannot be used together")
    }

    val runningStrategies = q"RunningStrategies(Set(..$strategySet))"
    c.Expr[RunningStrategies](runningStrategies)
  }
}