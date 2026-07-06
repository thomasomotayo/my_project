<?php

function calculateCompanyTax($income) {
    // Nigerian Company Income Tax (example)
    if ($income <= 25000000) {
        return $income * 0.20; // 20%
    } else {
        return $income * 0.30; // 30%
    }
}

function calculateIndividualTax($income) {
    // Simple PAYE simulation
    if ($income <= 300000) {
        return $income * 0.07;
    } elseif ($income <= 600000) {
        return $income * 0.11;
    } elseif ($income <= 1100000) {
        return $income * 0.15;
    } else {
        return $income * 0.19;
    }
}
?>