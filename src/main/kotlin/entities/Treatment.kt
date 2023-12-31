package entities

import enumerations.treatmentType

data class Treatment (
    var descriptionTreatment: String,
    var treatmentType : treatmentType
);