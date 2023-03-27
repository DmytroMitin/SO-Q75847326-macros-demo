package com.dv.phoenix.brandsafety

import com.dv.phoenix.brandsafety.models.RunningStrategies
import com.dv.phoenix.brandsafety.models.RunningStrategy.MonitoringOnly
import com.dv.phoenix.brandsafety.models.RunningStrategy.MonitoringAndBlockingInherent
import com.dv.phoenix.brandsafety.models.RunningStrategy.MonitoringAndBlockingRecalculate

object App {
  /*override*/ protected val runningStrategies: RunningStrategies = RunningStrategies.apply(MonitoringOnly, MonitoringAndBlockingInherent, MonitoringAndBlockingRecalculate)

  def main(args: Array[String]): Unit = {
    println(runningStrategies)
  }
}
