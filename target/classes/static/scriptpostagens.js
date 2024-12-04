(function () {
    let postagemId = null; // Variável para armazenar o ID da postagem
  
    // Evento para abrir o modal ao clicar no ícone de comentário
    $(document).on('click', '.fa-comment', function () {
        let botaoClicado = $(this).closest('button'); // Botão associado ao ícone
        postagemId = botaoClicado.attr('data-id'); // Pega o ID da postagem do atributo data-id
        console.log("Postagem ID: ", postagemId); // Verifique se o valor está correto

        // Configura a ação do formulário e exibe o modal
        $('#formComentario').attr('action', '/comentario/' + postagemId); // Atribui a URL correta no formulário
        $('#modalComentario').modal('show'); // Exibe o modal
    });

    // Evento para enviar o comentário ao clicar no botão de envio
    $(document).on('click', '#btnEnviarComentario', function () {
        const textoComentario = $('#textoComentario').val();

        if (textoComentario.trim() === '') {
            alert('O comentário não pode ser vazio');
            return;
        }

        // Envia o formulário
        $('#formComentario').submit();
    });
})();