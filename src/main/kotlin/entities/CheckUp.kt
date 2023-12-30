package entities

import enumerations.checkupStatus

data class CheckUp (
    var value : Double,
    var status : checkupStatus
);