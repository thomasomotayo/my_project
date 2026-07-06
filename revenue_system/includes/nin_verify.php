<?php

function verifyNIN($nin) {

    // Simulated valid NIN numbers
    $validNINs = [
        "12345678901" => "Thomas Omotayo Esther",
        "98765432100" => "Peculiar Akanbi",
        "11223344556" => "Racheal Thomas"
        ];

    if (array_key_exists($nin, $validNINs)) {
        return [
            "status" => true,
            "name" => "$validNINs[$nin]"
        ];
    }

    return ["status" => false];
}
?>