package com.literalura.service;

import com.literalura.model.Autor;
import com.literalura.model.Livro;
import com.literalura.repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class GutendexService {

    private final RestTemplate restTemplate = new RestTemplate();

    @Autowired
    private AutorRepository autorRepository;

    public Livro buscarLivroPorTitulo(String titulo) {
        String url = "https://gutendex.com/books/?search=" + titulo.replace(" ", "+");

        Map<String, Object> resposta = restTemplate.getForObject(url, Map.class);
        List<Map<String, Object>> resultados = (List<Map<String, Object>>) resposta.get("results");

        if (resultados == null || resultados.isEmpty()) {
            return null;
        }

        Map<String, Object> primeiroResultado = resultados.get(0);

        String tituloLivro = (String) primeiroResultado.get("title");
        int downloadCount = (int) primeiroResultado.get("download_count");
        List<String> languages = (List<String>) primeiroResultado.get("languages");

        List<Map<String, Object>> autores = (List<Map<String, Object>>) primeiroResultado.get("authors");
        Map<String, Object> autorMap = autores.get(0);
        String nomeAutor = (String) autorMap.get("name");
        Integer nascimento = (Integer) autorMap.get("birth_year");
        Integer falecimento = (Integer) autorMap.get("death_year");

        // Verifica se autor já existe no banco
        Optional<Autor> autorExistente = autorRepository.findByNomeIgnoreCase(nomeAutor.trim());

        Autor autor;
        if (autorExistente.isPresent()) {
            autor = autorExistente.get();
        } else {
            autor = new Autor();
            autor.setNome(nomeAutor.trim());
            autor.setNascimento(nascimento);
            autor.setFalecimento(falecimento);
        }

        Livro livro = new Livro();
        livro.setTitulo(tituloLivro.trim());
        livro.setNumeroDownloads(downloadCount);
        livro.setIdioma(languages.get(0));
        livro.setAutor(autor);

        return livro;
    }
}
