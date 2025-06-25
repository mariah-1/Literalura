package com.literalura;

import com.literalura.model.Livro;
import com.literalura.repository.AutorRepository;
import com.literalura.repository.LivroRepository;
import com.literalura.service.GutendexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class Principal implements CommandLineRunner {

    @Autowired
    private GutendexService gutendexService;
    @Autowired
    private LivroRepository livroRepository;
    @Autowired
    private AutorRepository autorRepository;

    private final Scanner scanner = new Scanner(System.in);

    @Override
    public void run(String... args) {
        int opcao = -1;

        while (opcao != 0) {
            System.out.println("\n--- MENU ---");
            System.out.println("1 - Buscar livro pelo título");
            System.out.println("2 - Listar livros registrados");
            System.out.println("3 - Listar autores");
            System.out.println("4 - Listar autores vivos em um ano");
            System.out.println("5 - Listar livros por idioma");
            System.out.println("0 - Sair");

            opcao = Integer.parseInt(scanner.nextLine());

            switch (opcao) {
                case 1 -> buscarLivro();
                case 2 -> listarLivros();
                case 3 -> listarAutores();
                case 4 -> autoresPorAno();
                case 5 -> livrosPorIdioma();
            }
        }
    }

    private void buscarLivro() {
        System.out.print("Digite o título do livro: ");
        String titulo = scanner.nextLine().trim();

        // Verifica se o livro já está no banco
        if (livroRepository.findByTituloIgnoreCase(titulo).isPresent()) {
            System.out.println("⚠️ Livro \"" + titulo + "\" já está cadastrado no banco!");
            return; // Sai do método, não salva novamente
        }

        Livro livro = gutendexService.buscarLivroPorTitulo(titulo);
        if (livro != null) {
            livroRepository.save(livro);
            System.out.println("✅ Livro \"" + livro.getTitulo() + "\" salvo com sucesso!");
        } else {
            System.out.println("❌ Livro não encontrado na API.");
        }
    }


    private void listarLivros() {
        var livros = livroRepository.findAll();
        System.out.println("Livros encontrados: " + livros.size());
        if (livros.isEmpty()) {
            System.out.println("Nenhum livro cadastrado.");
            return;
        }
        livros.forEach(l -> {
            System.out.println("Título: " + l.getTitulo());
            if (l.getAutor() != null) {
                System.out.println("Autor: " + l.getAutor().getNome());
            } else {
                System.out.println("Autor: não encontrado");
            }
            System.out.println("Idioma: " + l.getIdioma());
            System.out.println("Downloads: " + l.getNumeroDownloads());
            System.out.println("-------------------------");
        });
    }

    private void listarAutores() {
        var autores = autorRepository.findAll();
        System.out.println("Autores encontrados: " + autores.size());
        if (autores.isEmpty()) {
            System.out.println("Nenhum autor cadastrado.");
            return;
        }
        autores.forEach(a -> {
            System.out.println("Nome: " + a.getNome());
            System.out.println("Nascimento: " + (a.getNascimento() != null ? a.getNascimento() : "Desconhecido"));
            System.out.println("Falecimento: " + (a.getFalecimento() != null ? a.getFalecimento() : "Desconhecido"));
            System.out.println("-------------------------");
        });
    }

    private void autoresPorAno() {
        System.out.print("Digite o ano: ");
        int ano = Integer.parseInt(scanner.nextLine());
        autorRepository.findByNascimentoLessThanEqualAndFalecimentoGreaterThanEqual(ano, ano)
                .forEach(a -> {
                    System.out.println("Nome: " + a.getNome());
                    System.out.println("Nascimento: " + (a.getNascimento() != null ? a.getNascimento() : "Desconhecido"));
                    System.out.println("Falecimento: " + (a.getFalecimento() != null ? a.getFalecimento() : "Desconhecido"));
                    System.out.println("-------------------------");
                });
    }

    private void livrosPorIdioma() {
        System.out.print("Digite o idioma (ex: en, pt, es): ");
        String idioma = scanner.nextLine();
        livroRepository.findByIdioma(idioma).forEach(l -> {
            System.out.println("Título: " + l.getTitulo());
            if (l.getAutor() != null) {
                System.out.println("Autor: " + l.getAutor().getNome());
            } else {
                System.out.println("Autor: não encontrado");
            }
            System.out.println("Idioma: " + l.getIdioma());
            System.out.println("Downloads: " + l.getNumeroDownloads());
            System.out.println("-------------------------");
        });
    }
}
