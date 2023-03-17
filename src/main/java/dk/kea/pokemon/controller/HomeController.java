package dk.kea.pokemon.controller;

import dk.kea.pokemon.repository.PokemonRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    PokemonRepository pokemonRepository;

    public Homecontroller(PokemonRepository pokemonRepository){
        this.pokemonRepository = pokemonRepository;
    }

    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("pokemons", pokemonRepository.getAllPokemons());
        return "index";
    }
}
