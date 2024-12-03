package br.univille.fso2024a;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import br.univille.fso2024a.entity.Usuario;
import br.univille.fso2024a.entity.Postagem;
import br.univille.fso2024a.service.PostagemService;
import br.univille.fso2024a.service.UsuarioService;

@Component
public class Startup {
    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private PostagemService postagemService;

    @EventListener
    public void onApplicationEvent(ContextRefreshedEvent event) {
        String[][] usuarios = {
            {"Felipe Moura", "felipe@email.com"},
            {"Ana Silva", "ana@email.com"},
            {"Carlos Santos", "carlos@email.com"},
            {"Mariana Costa", "mariana@email.com"},
            {"João Pereira", "joao@email.com"},
            {"Fernanda Oliveira", "fernanda@email.com"},
            {"Lucas Almeida", "lucas@email.com"},
            {"Beatriz Souza", "beatriz@email.com"},
            {"Rafael Mendes", "rafael@email.com"},
            {"Larissa Rocha", "larissa@email.com"}
        };
    
        String[] textos = {
            "Hoje é um dia incrível!",
            "Alguém assistiu o último episódio da série X?",
            "Que dia cansativo, mas produtivo.",
            "Preciso de recomendações de livros. Alguma dica?",
            "Finalmente consegui completar meu projeto!",
            "Alguém mais ama pizza tanto quanto eu?",
            "Incrível como o tempo passa rápido.",
            "Feliz por estar cercado de pessoas incríveis.",
            "Quem topa uma viagem espontânea?",
            "Reflexão do dia: sempre há algo para aprender.",
            "O café da manhã de hoje estava maravilhoso.",
            "Nunca subestime o poder de um bom descanso.",
            "Será que vai chover hoje? O céu está estranho.",
            "Aprendi algo novo e quero compartilhar: persistência vale a pena!",
            "Alguém mais está animado para o final de semana?",
            "Acabei de assistir um documentário incrível!",
            "Sinto que hoje é um bom dia para reorganizar a casa.",
            "O mundo precisa de mais gentileza e menos pressa.",
            "Adoro o cheiro de terra molhada depois da chuva.",
            "Quem diria que pequenos gestos fazem tanta diferença?",
            "A vida é cheia de surpresas, basta saber enxergá-las.",
            "Hoje acordei inspirado para cozinhar algo diferente.",
            "A música certa tem o poder de transformar o dia.",
            "Nunca é tarde para começar algo novo.",
            "Acabei de finalizar uma corrida de 5km, sensação incrível!",
            "Relembrando bons momentos da infância.",
            "A simplicidade é a chave para a felicidade.",
            "Planejando minha próxima viagem... sugestões?",
            "Quem aí gosta de plantas? Estou apaixonado pelas minhas.",
            "Compartilhando uma frase que ouvi hoje: 'Você é o autor da sua história.'",
            "Estou lendo um livro fascinante sobre ciência!",
            "Hoje decidi focar na gratidão.",
            "Acordei cedo e já sinto a produtividade no ar.",
            "Aprender a dizer 'não' também é importante.",
            "Estou tentando uma receita nova de bolo. Desejem-me sorte!",
            "O tempo com amigos é sempre bem aproveitado.",
            "Vivendo um dia de cada vez.",
            "A tecnologia facilita a vida, mas às vezes sinto falta de desconectar.",
            "Hoje é um bom dia para começar algo que sempre adiei.",
            "Quem mais acha que dias nublados têm um charme especial?",
            "Reflexão: Tudo é questão de perspectiva.",
            "Passei a tarde ouvindo música dos anos 80. Que vibe!",
            "Comecei a meditar recentemente e já sinto a diferença.",
            "O silêncio às vezes é a melhor resposta.",
            "Todo dia é uma nova chance para recomeçar.",
            "Hoje tirei um tempo só para mim. Recomendo!",
            "Nada como um bom filme para encerrar o dia.",
            "A natureza tem uma forma incrível de nos ensinar coisas.",
            "Estou ansioso para o que o futuro reserva.",
            "Praticar esporte é um hábito que quero manter.",
            "Seja a mudança que você quer ver no mundo.",
            "Minha meta da semana: organizar minhas finanças.",
            "Acabei de provar um prato novo e adorei!",
            "Nunca subestime o poder de um bom abraço.",
            "Quem mais está com saudade de um show ao vivo?",
            "A vida é muito curta para não dizer 'eu te amo' às pessoas importantes.",
            "Hoje é um bom dia para experimentar algo novo.",
            "Relembrando minha viagem favorita de todos os tempos.",
            "Às vezes, tudo o que precisamos é uma pausa para respirar.",
            "O conhecimento é o único bem que ninguém pode tirar de você.",
            "Seja você mesmo, sempre.",
            "Por que as segundas-feiras têm uma reputação tão ruim?",
            "Terminei uma maratona de séries. Alguma recomendação para a próxima?",
            "Nada como um bom livro para viajar sem sair de casa.",
            "A natureza é o melhor lugar para recarregar as energias.",
            "Um dia frio combina com cobertor e chocolate quente.",
            "Aprender algo novo todos os dias é meu objetivo.",
            "Cuidar de si mesmo também é uma prioridade.",
            "Hoje decidi simplificar minha vida.",
            "O sorriso é contagiante. Espalhe o máximo que puder.",
            "A vida é um presente. Aproveite cada momento.",
            "Quem mais está ansioso para o verão?",
            "Trabalhando em um projeto que me deixa muito empolgado!",
            "Às vezes, a melhor decisão é confiar em si mesmo.",
            "Hoje foi um dia cheio, mas gratificante.",
            "Nada como uma caminhada ao ar livre para clarear a mente.",
            "Acabei de adotar um animal de estimação. Estou muito feliz!",
            "Refletindo sobre o que realmente importa na vida.",
            "Estou muito grato pelas pessoas que fazem parte da minha jornada.",
            "Quem mais acredita que o melhor ainda está por vir?",
            "A rotina pode ser desafiadora, mas também traz segurança.",
            "Sinto que hoje é um bom dia para começar algo grande.",
            "Descobri uma música nova que não consigo parar de ouvir.",
            "A paciência é uma virtude que estou aprendendo a valorizar.",
            "Terminei um quebra-cabeça de 1000 peças. Que sensação de conquista!",
            "Todo desafio é uma oportunidade disfarçada.",
            "Alguém mais gosta de escrever listas para organizar o dia?",
            "Acreditar em si mesmo é o primeiro passo para qualquer realização.",
            "O amanhecer de hoje foi especialmente bonito.",
            "Hoje é o dia perfeito para fazer algo gentil por alguém.",
            "A arte tem uma forma única de tocar o coração.",
            "Nada como uma conversa sincera para clarear as coisas.",
            "Se você está lendo isso, lembre-se: você é incrível!"
        };
        
    
        // Criar usuários
        for (String[] dadosUsuario : usuarios) {
            String nome = dadosUsuario[0];
            String email = dadosUsuario[1];
    
            var usuario = usuarioService.findByEmail(email);
            if (usuario == null) {
                usuario = new Usuario();
                usuario.setNome(nome);
                usuario.setEmail(email);
                usuarioService.save(usuario);
            }
        }
    
        // Criar 100 postagens
        var usuariosSalvos = usuarioService.getAll(); // Presume que há um método getAll no serviço de usuários
        int numeroPostagens = 100;
        for (int i = 0; i < numeroPostagens; i++) {
            var usuario = usuariosSalvos.get(i % usuariosSalvos.size()); // Alternar entre os usuários
            var texto = textos[i % textos.length]; // Alternar entre os textos
    
            var postagem = new Postagem();
            postagem.setUsuario(usuario);
            postagem.setTexto(texto);
    
            postagemService.save(postagem);
        }
    }
    
}
