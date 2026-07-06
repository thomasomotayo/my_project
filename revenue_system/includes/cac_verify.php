<?php

function verifyCAC($cac_number) {

    // Simulated valid CAC numbers
    $validCACs = [
        "RC123456" => "Ethon Digital Enterprises",
        "RC654321" => "Ewealth Computer Technology Ltd",
        "RC111222" => "Omotayo Esther Global Enterprise",
        "RC333444" => "Flutterwave Inc"
    ];

    // ✅ Check if CAC exists as KEY
    if (array_key_exists($cac_number, $validCACs)) {
        return [
            "status" => true,
            "company_name" => $validCACs[$cac_number] // ✅ get correct name
        ];
    }

    return ["status" => false];
}
?>