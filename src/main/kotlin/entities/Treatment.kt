package entities

import enumerations.TreatmentType

data class Treatment (
    var descriptionTreatment: String,
    var treatmentType : TreatmentType
);