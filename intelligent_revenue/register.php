<?php
require_once 'config.php';

$error = '';
$success = '';

if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    $full_name = trim($_POST['full_name']);
    $email = trim($_POST['email']);
    $phone = trim($_POST['phone'] ?? '');
    $username = trim($_POST['username']);
    $password = password_hash($_POST['password'], PASSWORD_DEFAULT);
    $registration_type = $_POST['registration_type'] ?? '';
    $nin = trim($_POST['nin'] ?? '');
    $business_number = trim($_POST['business_number'] ?? '');

    // Basic phone validation
    if (!empty($phone) && !preg_match('/^\+?[0-9]{10,15}$/', $phone)) {
        $error = 'Invalid phone number format. Use digits only, optionally starting with +.';
    }

    // Ensure a registration type is selected
    if (!in_array($registration_type, ['personal', 'business'])) {
        $error = 'Please select registration type (Personal or Business).';
    }
    // Personal validation
    elseif ($registration_type === 'personal') {
        if (empty($nin)) {
            $error = 'NIN is required for personal registration.';
        } elseif (!verifyNIN($nin)) {
            $error = 'Invalid NIN. Please enter a valid 11‑digit National Identification Number.';
        }
    }
    // Business validation
    elseif ($registration_type === 'business') {
        if (empty($business_number)) {
            $error = 'CAC Business Registration Number is required.';
        } elseif (!verifyCAC($business_number)) {
            $error = 'Invalid Business Registration Number. Expected format: RC‑1234567 or BN‑1234567.';
        }
    }

    // ---------- SERVER-SIDE NAME VERIFICATION ----------
    if (empty($error)) {
        // Look up the official name for the provided ID
        if ($registration_type === 'personal') {
            $officialName = getNINName($nin);
        } else {
            $officialName = getCACName(strtoupper($business_number));
        }

        if ($officialName === null) {
            $error = 'Invalid identification number.'; // shouldn't happen if earlier checks passed, but safety
        } elseif ($officialName !== $full_name) {
            $error = 'Full name does not match the official record for this ID.';
        }
    }
    // ---------------------------------------------------

    // If still no errors, proceed with registration
    if (empty($error)) {
        // Set unused identifier to null
        if ($registration_type === 'personal') {
            $business_number = null;
        } else {
            $nin = null;
        }

        try {
            $stmt = $pdo->prepare("INSERT INTO users (username, password, role, full_name, email, phone, nin, business_number) VALUES (?,?, 'taxpayer', ?,?,?,?,?)");
            $stmt->execute([$username, $password, $full_name, $email, $phone, $nin, $business_number]);
            $success = 'Registration successful! You can now login.';
        } catch (PDOException $e) {
            if ($e->getCode() == 23000) {
                $error = 'Username, NIN, or Business Number already exists.';
            } else {
                $error = 'Registration failed. Please try again.';
            }
        }
    }
}
?>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Register Taxpayer - RevenueGuard</title>
    <link rel="stylesheet" href="style.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
    <style>
        .radio-group {
            display: flex;
            gap: 20px;
            margin-bottom: 20px;
            justify-content: center;
        }
        .radio-group label {
            display: flex;
            align-items: center;
            gap: 8px;
            cursor: pointer;
            font-weight: 500;
        }
        .id-section {
            display: none;
        }
        .id-section.active {
            display: block;
        }
        .input-group input[readonly] {
            background-color: #e9ecef;
            cursor: not-allowed;
        }
    </style>
</head>
<body class="login-body">
    <div class="login-container">
        <div class="login-card">
            <div class="logo-section">
                <div class="logo-section">
                    <img src="images/nigeria2.png" alt="Logo" style="width: 100px; height: auto; margin-bottom: 10px;">
                    <h2>NRS | Nigeria Revenue Service</h2>
                    <p>Intelligent Revenue Collection</p>
                </div>
                            </div>
            <?php if ($error): ?>
                <div class="alert error"><?= htmlspecialchars($error) ?></div>
            <?php endif; ?>
            <?php if ($success): ?>
                <div class="alert success"><?= htmlspecialchars($success) ?></div>
            <?php endif; ?>
            <form method="post" id="registerForm">
                <div class="radio-group">
                    <label>
                        <input type="radio" name="registration_type" value="personal" checked>
                        <i class="fa fa-user"></i> Personal
                    </label>
                    <label>
                        <input type="radio" name="registration_type" value="business">
                        <i class="fa fa-building"></i> Business / Corporate
                    </label>
                </div>

                <!-- Personal ID Section -->
                <div class="id-section active" id="personalSection">
                    <div class="input-group">
                        <i class="fa fa-id-card"></i>
                        <input type="text" name="nin" id="ninInput" placeholder="NIN (11 digits)" pattern="\d{11}" title="11‑digit National Identification Number">
                    </div>
                </div>

                <!-- Business ID Section -->
                <div class="id-section" id="businessSection">
                    <div class="input-group">
                        <i class="fa fa-building"></i>
                        <input type="text" name="business_number" id="cacInput" placeholder="CAC Reg No (RC‑1234567)">
                    </div>
                </div>

                <!-- Full Name (auto‑filled from ID) -->
                <div class="input-group">
                    <i class="fa fa-user"></i>
                    <input type="text" name="full_name" id="fullNameInput" placeholder="Full Name" required readonly>
                </div>

                <!-- Phone Number -->
                <div class="input-group">
                    <i class="fa fa-phone"></i>
                    <input type="tel" name="phone" placeholder="Phone Number (e.g. +2348012345678)">
                </div>

                <div class="input-group">
                    <i class="fa fa-envelope"></i>
                    <input type="email" name="email" placeholder="Email Address" required>
                </div>
                <div class="input-group">
                    <i class="fa fa-user-circle"></i>
                    <input type="text" name="username" placeholder="Username" required>
                </div>
                <div class="input-group">
                    <i class="fa fa-lock"></i>
                    <input type="password" name="password" placeholder="Password" required>
                </div>
                <button type="submit" class="btn primary">Register</button>
            </form>
            <p style="margin-top:15px;"><a href="login.php">Back to Login</a></p>
        </div>
    </div>

    <script>
        // ----- Valid ID ↔ Name mappings (simulated live API) -----
        const ninNames = {
            '12345678901': 'Thomas Omotayo',
            '23456789012': 'Jane Smith',
            '34567890123': 'Michael Johnson',
            '45678901234': 'Emily Brown',
            '56789012345': 'David Wilson'
        };
        const cacNames = {
            'RC-1234567': 'Ethon Digital Enterprises',
            'RC-7654321': 'Tech Solutions Inc',
            'BN-1111111': 'Local Business Name',
            'BN-2222222': 'Alpha Enterprises',
            'RC-9999999': 'Prime Holdings'
        };

        // DOM elements
        const personalRadio = document.querySelector('input[value="personal"]');
        const businessRadio = document.querySelector('input[value="business"]');
        const personalSection = document.getElementById('personalSection');
        const businessSection = document.getElementById('businessSection');
        const ninInput = document.getElementById('ninInput');
        const cacInput = document.getElementById('cacInput');
        const fullNameInput = document.getElementById('fullNameInput');

        function toggleSections() {
            if (personalRadio.checked) {
                personalSection.classList.add('active');
                businessSection.classList.remove('active');
                cacInput.disabled = true;
                cacInput.value = '';
                ninInput.disabled = false;
            } else {
                businessSection.classList.add('active');
                personalSection.classList.remove('active');
                ninInput.disabled = true;
                ninInput.value = '';
                cacInput.disabled = false;
            }
            // Reset full name
            fullNameInput.value = '';
            fullNameInput.readOnly = true;
        }

        function handleNINInput() {
            const nin = ninInput.value.trim();
            if (nin in ninNames) {
                fullNameInput.value = ninNames[nin];
                fullNameInput.readOnly = true;
            } else {
                fullNameInput.value = '';
                fullNameInput.readOnly = true;
            }
        }

        function handleCACInput() {
            const cac = cacInput.value.trim().toUpperCase();
            cacInput.value = cac; // show uppercase
            if (cac in cacNames) {
                fullNameInput.value = cacNames[cac];
                fullNameInput.readOnly = true;
            } else {
                fullNameInput.value = '';
                fullNameInput.readOnly = true;
            }
        }

        personalRadio.addEventListener('change', toggleSections);
        businessRadio.addEventListener('change', toggleSections);
        ninInput.addEventListener('input', handleNINInput);
        cacInput.addEventListener('input', handleCACInput);

        // Initial state
        toggleSections();
    </script>
</body>
</html>