<!DOCTYPE html>
<html lang="en">

<head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Contact Us | QuickBite</title>
        <link rel="stylesheet" href="../css/contact.css">
</head>

<body>
        <div class="hero-image">
                <div class="hero-title">Contact Us</div>
        </div>
        <div class="contact-container">
                <div class="info">
                        <h2>Islington College</h2>
                        <p>Kamal Pokhari, Kathmandu , Nepal</p>
                        <p>Phone: +977 - 4433221</p>
                </div>
                <form action="" class="feedback-form">
                        <h2>Send us your feedback</h2>
                        <label for="name">Full Name</label>
                        <input type="text" id="name" placeholder="Your Name">

                        <label for="email">Email</label>
                        <input type="email" id="email" placeholder="Your Email">

                        <div class="rating-row">
                                <label for="rating">Rating:</label>
                                <div class="star-rating">
                                        <input type="radio" id="star5" name="rating" value="5">
                                        <label for="star5">★</label>

                                        <input type="radio" id="star4" name="rating" value="4">
                                        <label for="star4">★</label>

                                        <input type="radio" id="star3" name="rating" value="3">
                                        <label for="star3">★</label>

                                        <input type="radio" id="star2" name="rating" value="2">
                                        <label for="star2">★</label>

                                        <input type="radio" id="star1" name="rating" value="1">
                                        <label for="star1">★</label>
                                </div>
                        </div>

                        <label for="message">Your Message</label>
                        <textarea id="message" placeholder="Your Message"></textarea>

                        <button type="submit">Submit</button>
                </form>
        </div>

        <div class="map">
                <img src="../assets/Location.jpg" alt="Islington College Location">
        </div>

</body>

</html>