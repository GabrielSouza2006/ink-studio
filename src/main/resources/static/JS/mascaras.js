document.addEventListener('DOMContentLoaded', () => {
    const telefoneInput = document.getElementById('telefone');
    const cpfInput = document.getElementById('cpf');
    const dataInput = document.getElementById('data');

    telefoneInput.addEventListener('input', (e) => {
        e.target.value = formatarTelefone(e.target.value);
    });

    cpfInput.addEventListener('input', (e) => {
        e.target.value = formatarCPF(e.target.value);
    });

    dataInput.addEventListener('input', (e) => {
        e.target.value = formatarData(e.target.value);
    });

    function formatarTelefone(value) {
        // Remove tudo o que não é dígito
        value = value.replace(/\D/g, '');
        // Limita a 11 caracteres
        value = value.substring(0, 11);
        // Adiciona a máscara
        if (value.length <= 10) {
            return value.replace(/(\d{2})(\d{0,5})/, '($1) $2').trim();
        } else {
            return value.replace(/(\d{2})(\d{5})(\d{0,4})/, '($1) $2-$3').trim();
        }
    }

    function formatarCPF(value) {
        // Remove tudo o que não é dígito
        value = value.replace(/\D/g, '');
        // Limita a 11 caracteres
        value = value.substring(0, 11);
        // Adiciona a máscara
        return value.replace(/(\d{3})(\d{3})(\d{3})(\d{0,2})/, '$1.$2.$3-$4').trim();
    }

    function formatarData(value) {
        // Remove tudo o que não é dígito
        value = value.replace(/\D/g, '');
        // Limita a 8 caracteres
        value = value.substring(0, 8);
        // Adiciona a máscara
        return value.replace(/(\d{2})(\d{0,2})(\d{0,4})/, '$1/$2/$3').trim();
    }
});
