// Nathan - Verander geldbedrag op accountpagina
// zodat decimalen anders gestyled kunnen worden

// Haal het originele bedrag uit de DOM
var amount = document.getElementById("original").innerHTML;

// Zet alles behalve de twee decimalen in de <span> met id "amt"
var voorKomma = amount.substring(0,amount.length-2);
document.getElementById("amt").innerHTML = voorKomma;

// Zet de twee decimalen in de <span> met id "dcm"
var naKomma = amount.substring(amount.length-2);
document.getElementById("dcm").innerHTML = naKomma;