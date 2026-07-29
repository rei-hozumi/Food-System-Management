window.onload = function () {

    const cards = document.querySelectorAll(".menu-card");

    cards.forEach(card => {
        card.style.display = "none";
    });

}

function showMenu(id){

    const cards = document.querySelectorAll(".menu-card");

    cards.forEach(card => {
        card.style.display = "none";
    });

    document.getElementById("message").style.display = "none";

    document.getElementById(id).style.display = "block";

}