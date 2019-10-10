// Nathan - scripts voor uitklapfunctie

// Set elements to folded position by default
var unfoldedHighest = false;
var unfoldedMostActive = false;
var unfoldedAverage = false;

// Declare variables to interactive elements
var highest = document.getElementById('unfold-highest');
var highestIcon = document.getElementById('highest-icon');
var highestTableLeft = document.getElementById('highest-table-left');
var highestTableRight = document.getElementById('highest-table-right');

var mostActive = document.getElementById('unfold-most-active');
var mostActiveIcon = document.getElementById('most-active-icon');
var mostActiveTableLeft = document.getElementById('most-active-table-left');
var mostActiveTableRight = document.getElementById('most-active-table-right');

var average = document.getElementById('unfold-average');
var averageIcon = document.getElementById('average-icon');
var averageTableLeft = document.getElementById('average-table-left');
var averageTableRight = document.getElementById('average-table-right');

// Add event listeners to interactive elements
highest.addEventListener("click", unfoldHighest);
mostActive.addEventListener("click", unfoldMostActive);
average.addEventListener("click", unfoldAverage);



// Functions

function unfoldHighest() {

    if (unfoldedHighest == false) {
        // Make icon change
        highestIcon.className = "fas fa-chevron-down";
        // Make elements unfold
        highestTableLeft.classList.remove("hidden");
        highestTableLeft.classList.add("visible-block");
        highestTableRight.classList.remove("hidden");
        highestTableRight.classList.add("visible-block");
        unfoldedHighest = true;
        return;
    }

    if (unfoldedHighest == true) {
        // Make icon change
        highestIcon.className = "fas fa-chevron-right";
        // Make element fold
        highestTableLeft.classList.add("hidden");
        highestTableLeft.classList.remove("visible-block");
        highestTableRight.classList.add("hidden");
        highestTableRight.classList.remove("visible-block");
        unfoldedHighest = false;
        return;
    }

}

function unfoldMostActive() {

    if (unfoldedMostActive == false) {
        // Make icon change
        mostActiveIcon.className = "fas fa-chevron-down";
        // Make elements unfold
        mostActiveTableLeft.classList.remove("hidden");
        mostActiveTableLeft.classList.add("visible-block");
        mostActiveTableRight.classList.remove("hidden");
        mostActiveTableRight.classList.add("visible-block");
        unfoldedMostActive = true;
        return;
    }

    if (unfoldedMostActive == true) {
        // Make icon change
        mostActiveIcon.className = "fas fa-chevron-right";
        // Make element fold
        mostActiveTableLeft.classList.add("hidden");
        mostActiveTableLeft.classList.remove("visible-block");
        mostActiveTableRight.classList.add("hidden");
        mostActiveTableRight.classList.remove("visible-block");
        unfoldedMostActive = false;
        return;
    }

}

function unfoldAverage() {

    if (unfoldedAverage == false) {
        // Make icon change
        averageIcon.className = "fas fa-chevron-down";
        // Make elements unfold
        averageTableLeft.classList.remove("hidden");
        averageTableLeft.classList.add("visible-block");
        averageTableRight.classList.remove("hidden");
        averageTableRight.classList.add("visible-block");
        unfoldedAverage = true;
        return;
    }

    if (unfoldedAverage == true) {
        // Make icon change
        averageIcon.className = "fas fa-chevron-right";
        // Make element fold
        averageTableLeft.classList.add("hidden");
        averageTableLeft.classList.remove("visible-block");
        averageTableRight.classList.add("hidden");
        averageTableRight.classList.remove("visible-block");
        unfoldedAverage = false;
        return;
    }

}
