package com.dv.phoenix.brandsafety.utils

import com.dv.phoenix.brandsafety.models.RunningStrategies
import com.dv.phoenix.brandsafety.models.RunningStrategy.{MonitoringAndBlockingInherent, MonitoringAndBlockingRecalculate, RunningStrategy}

import scala.reflect.macros.blackbox

object RunningStrategiesMacros {
  def applyImp(c: blackbox.Context)(strategies: c.Expr[RunningStrategy]*): c.Expr[RunningStrategies] = {
    import c.universe._

    val cond = Seq(MonitoringAndBlockingInherent, MonitoringAndBlockingRecalculate).forall(
      strategies.map(expr => c.eval(c.Expr[RunningStrategy](c.untypecheck(expr.tree)))).contains
    )
    if (cond) {
      c.abort(c.enclosingPosition, "MonitoringAndBlockingInherent and MonitoringAndBlockingRecalculate cannot be used together")
    }

    val runningStrategies = q"RunningStrategies(Set(..$strategies))"
    c.Expr[RunningStrategies](runningStrategies)
  }
}