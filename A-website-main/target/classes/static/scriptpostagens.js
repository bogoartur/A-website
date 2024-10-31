(function(){
    $('#tabelapostagens').on('click','.js-delete',function(){
        let botaoClicado = $(this)
        $('#btnsim').attr('data-id', botaoClicado.attr('data-id'))
        
        $('#modalpostagem').modal('show')
    })

    $(document).on('click', '#btnsim', function(){
        let botaoClicado = $(this)
        let id = botaoClicado.attr('data-id')
        $.ajax({
            url: 'homepage/delete/' + id,
            method: 'GET',
            success: function(){
                window.location.href = '/homepage'
            }
        })
    })
})()