package com.xingchidongli.robot.data


enum class MonthType {
    available, unavailable, current, available_current
}

data class ChooseMonthStateData(
    var month: Int,
    var type: MonthType,
    var lsSelect: Boolean,
)