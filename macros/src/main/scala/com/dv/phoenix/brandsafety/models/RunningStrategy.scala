package com.dv.phoenix.brandsafety.models

object RunningStrategy extends Enumeration {
  type RunningStrategy = Value

  val
  MonitoringOnly,
  BlockingOnly,
  MonitoringAndBlockingInherent,
  MonitoringAndBlockingRecalculate
  = Value
}
