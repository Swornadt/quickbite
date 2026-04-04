/**
 * 
 */

const data = [
    {
        "item-image": "../assets/images/Samosa.png",
        "item-name": "Samosa",
        "item-location": "Main Canteen",
        "item-price": 35,
        "item-ingredients": ['Aloo', 'Cumin', 'Maida']
    },
    {
        "item-image": "../assets/images/VegThukpa.png",
        "item-name": "Veg Thukpa",
        "item-location": "Main Canteen",
        "item-price": 100,
        "item-ingredients": ['Mushroom', 'Mustard', 'Carrot']
    },
    {
        "item-image": "../assets/images/VegThukpa.png",
        "item-name": "Veg Thukpa",
        "item-location": "Main Canteen",
        "item-price": 100,
        "item-ingredients": ['Mushroom', 'Mustard', 'Carrot']
    }
]

const cardsDiv = document.getElementById("cardsDiv");

data.forEach(item => {
    let ingredientsList = "";
    item["item-ingredients"].forEach(ingredient => {
        ingredientsList += `<li>${ingredient}</li>`
    });
    cardsDiv.innerHTML += `
    <div class="card">
            <div class="card-image">
                <img src=${item["item-image"]} alt="${item["item-name"]}">
            </div>
            <div class="card-details">
                <div class="item-name">
                    <h2>${item["item-name"]}</h2>
                </div>
                <div class="item-location">
                    <h3>${item["item-location"]}</h3>
                </div>
                <div class="item-price">
                    <h4>Rs.${item["item-price"]}</h4>
                </div>
                <div class="item-ingredients">
                    <div class="ingredients-title">
                        <p>Ingredients</p>
                    </div>
                    <div class="ingredients">
                        <ul>
                            ${ingredientsList}
                        </ul>
                    </div>
                </div>
                <button class="Add-to-Cart">
                    <span class="circle"></span>
                    <span class="btn-text">Add to Cart</span>
                </button>
            </div>
        </div>
    `;
});