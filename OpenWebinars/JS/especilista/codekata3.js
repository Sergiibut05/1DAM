// Ejemplo switch
let myFruit = "apple";

switch (myFruit) {
    case "apple":
        console.log("I love apples!");
        break;
    case "banana":
        console.log("I love bananas!");
        break;
    case "orange":
        console.log("I love oranges!");
        break;
    default:
        console.log("I love all fruits!");
        break;
}

// Ejemplo condicional ternario
let fruit = "apple";
let avalaibleFruits = new Set(["apple", "banana", "orange"]);
let isAvaible = (avalaibleFruits.has(fruit)) ? "Yes" : "No";
console.log(isAvaible);

// Ejemplo in
let myFavoriteFruit = "apple";
let myAvaiableFruits = {lemon: "Citric", watermelon: "Big Fruit", apple: "Red Fruit"};

console.log(myFavoriteFruit in myAvaiableFruits);
console.log(myAvaiableFruits[myFavoriteFruit]);

// Ejemplo in en arrays
let myFavoriteFruitt = "apple";
let myAvaiableFruitss = ["apple","Big Fruit","Red Fruit"];
//Solo pregunta por indices no lo que hay dentro
console.log(myFavoriteFruitt in myAvaiableFruitss);
console.log(2 in myAvaiableFruitss);



