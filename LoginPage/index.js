document.querySelector("#submit").addEventListener("click", function(event) {
    event.preventDefault(); 

    var email = document.querySelector("#email").value;
    var password = document.querySelector("#password").value;

    var emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;

    if (!emailPattern.test(email)) {
        alert("Por favor, insira um email válido.");
        return;
    }

    if (password.length < 6) {
        alert("A senha deve ter pelo menos 6 caracteres.");
        return;
    }

    alert("Login realizado com sucesso!");
});