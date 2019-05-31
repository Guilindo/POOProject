// Fecha os alerts de sucesso e erro 
$(function () {
    $(".close").click(function (e) {
        e.preventDefault();
        el = $('.alert');
        $(el).hide();
    });
});

// Mascara para os campos de valor
function valorProduto() {
    var valorUnitario = document.getElementById('valorUnitario').value;
    if (valorUnitario.length === 0) {
        document.getElementById('valorUnitario').value = 'R$';
    } else if (valorUnitario.length === 4) {
        var maskValor = 'R$' + valorUnitario.substring(2, 5) + ',';
        document.getElementById('valorUnitario').value = maskValor;
    } else if (valorUnitario.length === 7) {
        var maskValor = 'R$' + valorUnitario.substring(2, 4) +
                valorUnitario.substring(5, 6) + ',' +
                valorUnitario.substring(6, 7);
        document.getElementById('valorUnitario').value = maskValor;
    } else if (valorUnitario.length === 8) {
        var maskValor = 'R$' + valorUnitario.substring(2, 5) +
                valorUnitario.substring(6, 7) + ',' +
                valorUnitario.substring(7, 8);
        document.getElementById('valorUnitario').value = maskValor;
    }
}