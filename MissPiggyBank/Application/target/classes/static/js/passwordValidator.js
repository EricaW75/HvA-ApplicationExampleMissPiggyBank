// Erica - check "herhaal wachtwoord"

var checkPassword = function() {
    if (document.getElementById('password').value ==
        document.getElementById('confirm_password').value) {
        document.getElementById('message').style.color = 'green';
        document.getElementById('message').innerHTML = 'Het wachtwoord is goed';
    } else {
        document.getElementById('message').style.color = 'red';
        document.getElementById('message').innerHTML = '...herhaal het juiste wachwoord...';
    }
}