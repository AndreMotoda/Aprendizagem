function somar(valor1, valor2) {
    return valor1 + valor2;
}

document.querySelector("#calcular").addEventListener("click", function () {
    let valor1 = parseInt(document.querySelector("#valor1").value);
    let valor2 = parseInt(document.querySelector("#valor2").value);

    if (valor1 >= 0 && valor2 >= 0) {
        alert(somar(valor1, valor2));
    }
    else {
        alert("Digite os valores para somar");
    }
});