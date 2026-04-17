/**
 * 
 */

const data = [
    {
        "item-image": "../assets/Samosa.png",
        "category": "breakfast",
        "item-name": "Samosa",
        "item-location": "Main Canteen",
        "item-price": 35,
        "item-ingredients": ['Aloo', 'Cumin', 'Maida']
    },
    {
        "item-image": "../assets/VegThukpa.png",
        "category": "snacks",
        "item-name": "Veg Thukpa",
        "item-location": "Main Canteen",
        "item-price": 100,
        "item-ingredients": ['Mushroom', 'Mustard', 'Carrot']
    },
    {
        "item-image": "../assets/PotatoWedges.png",
        "category": "snacks",
        "item-name": "Potato Wedges",
        "item-location": "Main Canteen",
        "item-price": 100,
        "item-ingredients": ['Potato', 'Chives', 'Mayonnaise']
    },
    {
        "item-image": "../assets/BreakfastSet.png",
        "category": "breakfast",
        "item-name": "Breakfast Set",
        "item-location": "Main Canteen",
        "item-price": 175,
        "item-ingredients": ['Sausage', 'Egg', 'Aloo']
    },
    {
        "item-image": "../assets/FruitBowl.png",
        "category": "breakfast",
        "item-name": "Fruit Bowl",
        "item-location": "Main Canteen",
        "item-price": 200,
        "item-ingredients": ['Kiwi', 'Grapes', 'Papaya']
    },
    {
        "item-image": "../assets/ClubSandwich.png",
        "category": "snacks",
        "item-name": "Club Sandwich",
        "item-location": "Main Canteen",
        "item-price": 350,
        "item-ingredients": ['Mayonnaise', 'Chicken', 'Mustard']
    },{
        "item-image": "../assets/SteamedBuffMomo.png",
        "category": "snacks",
        "item-name": "Steamed Buff Momo",
        "item-location": "Main Canteen",
        "item-price": 150,
        "item-ingredients": ['Maida', 'Chicken', 'Peanut']
    }
]

const cardsDiv = document.getElementById("cardsDiv");

data.forEach(item => {
    let ingredientsList = "";
    item["item-ingredients"].forEach(ingredient => {
        ingredientsList += `<li>${ingredient}</li>`
    });
    cardsDiv.innerHTML += `
    <div class="card" data-category="${item.category}">
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
                        <h4>Ingredients</h4>
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