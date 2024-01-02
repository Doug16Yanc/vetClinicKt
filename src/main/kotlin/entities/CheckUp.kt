package entities

import enumerations.CheckupStatus

data class CheckUp(
    var value: Double,
    var status: CheckupStatus
)