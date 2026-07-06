<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>NRS | Nigeria Revenue Service</title>
    <link rel="stylesheet" href="style.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
    <style>
        /* Additional styles for home page */
        .home-body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            margin: 0;
            background: linear-gradient(135deg, #f5f7fa 0%, #c3cfe2 100%);
            min-height: 100vh;
        }
        .navbar {
            display: flex;
            justify-content: space-between;
            align-items: center;
            background: #fff;
            padding: 10px 30px;
            box-shadow: 0 2px 10px rgba(0,0,0,0.1);
            flex-wrap: wrap;
        }
        .navbar .logo {
            display: flex;
            align-items: center;
            gap: 12px;
        }
        .navbar .logo img {
            height: 50px;
        }
        .navbar .brand {
            font-size: 1.4rem;
            font-weight: 700;
            color: #1a5276;
        }
        .navbar .nav-links {
            display: flex;
            gap: 20px;
            align-items: center;
        }
        .navbar .nav-links a {
            text-decoration: none;
            color: #333;
            font-weight: 500;
            padding: 8px 15px;
            border-radius: 5px;
            transition: 0.2s;
        }
        .navbar .nav-links a:hover {
            background: #1a5276;
            color: #fff;
        }
        .navbar .nav-links .btn-login {
            background: #2e86c1;
            color: #fff;
            padding: 8px 20px;
            border-radius: 20px;
        }
        .hero {
            text-align: center;
            padding: 60px 20px;
        }
        .hero h1 {
            font-size: 2.8rem;
            color: #1a5276;
            margin-bottom: 10px;
        }
        .hero h2 {
            font-size: 2rem;
            color: #2c3e50;
            font-weight: 700;
            margin-bottom: 20px;
        }
        .hero p {
            font-size: 1.1rem;
            color: #555;
            max-width: 700px;
            margin: 0 auto 30px;
            line-height: 1.6;
        }
        .hero .cta-buttons {
            display: flex;
            justify-content: center;
            gap: 20px;
            flex-wrap: wrap;
        }
        .hero .btn {
            padding: 12px 30px;
            border-radius: 30px;
            font-size: 1rem;
            font-weight: 600;
            text-decoration: none;
            display: inline-block;
        }
        .btn-primary {
            background: #1a5276;
            color: #fff;
            border: none;
        }
        .btn-outline {
            border: 2px solid #1a5276;
            color: #1a5276;
            background: transparent;
        }
        .features {
            display: flex;
            flex-wrap: wrap;
            justify-content: center;
            gap: 30px;
            max-width: 1000px;
            margin: 40px auto;
            padding: 0 20px;
        }
        .feature-card {
            background: #fff;
            border-radius: 10px;
            padding: 30px 20px;
            box-shadow: 0 5px 20px rgba(0,0,0,0.08);
            flex: 1 1 250px;
            text-align: center;
        }
        .feature-card i {
            font-size: 2.5rem;
            color: #2e86c1;
            margin-bottom: 15px;
        }
        .feature-card h3 {
            margin-bottom: 10px;
            color: #1a5276;
        }
        footer {
            text-align: center;
            padding: 20px;
            background: #1a5276;
            color: #fff;
            margin-top: 40px;
        }
        @media (max-width: 768px) {
            .hero h1 { font-size: 2rem; }
            .hero h2 { font-size: 1.4rem; }
        }
    </style>
</head>
<body class="home-body">
    <!-- Navbar -->
    <nav class="navbar">
        <div class="logo">
            <!-- Nigerian Coat of Arms (inline SVG placeholder) -->
            <img src="https://upload.wikimedia.org/wikipedia/commons/thumb/b/bc/Coat_of_arms_of_Nigeria.svg/100px-Coat_of_arms_of_Nigeria.svg.png" 
                 alt="Nigeria Coat of Arms" style="height:50px;">
            <span class="brand">NRS | Nigeria Revenue Service</span>
        </div>
        <div class="nav-links">
            <a href="home.php">Home</a>
            <a href="index.php">Login</a>
            <a href="register.php">Register</a>
        </div>
    </nav>

    <!-- Hero Section -->
    <section class="hero">
        <h1>Nigeria Revenue Service</h1>
        <h2>Welcome to the Taxpayer Self‑Service Portal</h2>
        <p>
            File your taxes, make payments securely, download receipts, and track your revenue history – all in one place. 
            Our intelligent system ensures fraud detection and transparent revenue collection for a better Nigeria.
        </p>
        <div class="cta-buttons">
            <a href="register.php" class="btn btn-primary">Get Started</a>
            <a href="index.php" class="btn btn-outline">Login to Your Account</a>
        </div>
    </section>

    <!-- Features -->
    <div class="features">
        <div class="feature-card">
            <i class="fa fa-id-card"></i>
            <h3>Verified Identity</h3>
            <p>Register with your NIN or CAC number for secure taxpayer identification.</p>
        </div>
        <div class="feature-card">
            <i class="fa fa-calculator"></i>
            <h3>Auto Tax Calculation</h3>
            <p>Enter your yearly revenue and let the system compute your tax instantly.</p>
        </div>
        <div class="feature-card">
            <i class="fa fa-shield-alt"></i>
            <h3>Fraud Detection</h3>
            <p>Advanced algorithms flag suspicious payments to keep the system safe.</p>
        </div>
    </div>

    <footer>
        &copy; <?= date('Y') ?> Nigeria Revenue Service. All rights reserved.
    </footer>
</body>
</html>