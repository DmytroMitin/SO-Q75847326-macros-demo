package com.dv.phoenix.brandsafety.utils

import com.dv.phoenix.brandsafety.models.{RunningStrategies, RunningStrategy}
import com.dv.phoenix.brandsafety.models.RunningStrategy.RunningStrategy

import scala.reflect.macros.blackbox

object RunningStrategiesMacros {
  def applyImp(c: blackbox.Context)(strategies: c.Expr[RunningStrategy]*): c.Expr[RunningStrategies] = {
    import c.universe._

    val strategySet = strategies.map(_.tree).toList

    val cond = List("MonitoringAndBlockingInherent", "MonitoringAndBlockingRecalculate")
      .map(name => typeOf[RunningStrategy.type].decl(TermName(name)))
      .forall(strategySet.map(_.symbol).contains)

    if (cond) {
      c.abort(c.enclosingPosition, "MonitoringAndBlockingInherent and MonitoringAndBlockingRecalculate cannot be used together")
    }

    val runningStrategies = q"RunningStrategies(Set(..$strategySet))"
    c.Expr[RunningStrategies](runningStrategies)
  }
}