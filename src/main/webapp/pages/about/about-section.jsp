<!DOCTYPE html>
<html>

<head>
    <title>About Us</title>

    <style>
        body {
            margin: 0;
            font-family: Arial, sans-serif;
            background: url('image.png') no-repeat center center/cover;
            height: 100vh;
        }

        .overlay {
        background-color: transparent;
            height: 100vh;

            display: flex;
            justify-content: center;
            align-items: center;
            text-align: center;
        }

        .container {
            max-width: 700px;
        }

        h1 {
            color: #e74c3c;
            font-size: 40px;
        }

        .tagline {
            color: green;
            margin-bottom: 15px;
            font-weight: bold;
        }

        p {
            color: #333;
            line-height: 1.6;
        }

        .btn {
            margin-top: 20px;
            padding: 12px 25px;
            background: #e67e22;
            color: white;
            border-radius: 8px;
            text-decoration: none;
            display: inline-block;
        }

        .btn:hover {
            background: #d35400;
        }
    </style>
</head>

<body>

    <div class="overlay">

        <div class="container">

            <h1>About Us</h1>

            <p class="tagline">
                Built by students who got tired of long canteen queues
            </p>

            <p>
                We are a small team of passionate students who experienced the pain of long
                canteen queues and rushed breaks every single day. So we built “Quick Bite”.
                “Quick Bite” is a platform made by students, for students.
            </p>

            <a href="about-final.html" class="btn">Know More ></a>

        </div>

    </div>

</body>

</html>