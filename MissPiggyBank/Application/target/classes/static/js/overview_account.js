console.log("Overview account script geladen");

// Voeg een click listener toe aan de lijst met transacties
document.getElementById("trlist").addEventListener("click", function(e) {
    // e.target is the clicked element!
    // If it was a list item
    if(e.target && e.target.matches("div.transaction-div")) {
        // Clickable row found!  Output the ID!
        console.log(e.target.id, " was clicked!");
        // Get the child element that needs to be revealed
        var toReveal = e.target.querySelector(".transactionextra");
        // Reveal element
        toReveal.classList.remove("transactionextrahidden");
    } else if(e.target && e.target.nodeName == "SPAN") {
        // Clickable span found! Output the ID!
        console.log("A span child of ", e.target.parentNode.parentNode.id, " was clicked!");
        // Get the child element that needs to be revealed
        var toReveal = e.target.parentNode.parentNode.querySelector(".transactionextra");
        // Reveal element
        toReveal.classList.remove("transactionextrahidden");
    }
});