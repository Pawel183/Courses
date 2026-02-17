package com.pokemonreview.api.service;

import com.pokemonreview.api.dto.*;
import com.pokemonreview.api.models.Pokemon;
import com.pokemonreview.api.repository.PokemonRepository;
import com.pokemonreview.api.service.impl.PokemonServiceImpl;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.*;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PokemonServiceTests {

    @Mock
    private PokemonRepository pokemonRepository;

    @InjectMocks
    private PokemonServiceImpl pokemonService;

    @Test
    public void PokemonService_CreatePokemon_ReturnsPokemonDto() {
        Pokemon pokemon = Pokemon.builder()
                .name("pikachu")
                .type("electric").build();
        PokemonDto pokemonDto = PokemonDto.builder()
                .name("pikachu")
                .type("electric").build();

        when(pokemonRepository.save(Mockito.any(Pokemon.class))).thenReturn(pokemon);

        PokemonDto savedPokemon = pokemonService.createPokemon(pokemonDto);

        Assertions.assertNotNull(savedPokemon);
    }

    @Test
    public void PokemonService_GetAllPokemon_ReturnsResponseDto() {
        Page<Pokemon> pokemons = Mockito.mock(Page.class);

        when(pokemonRepository.findAll(Mockito.any(Pageable.class))).thenReturn(pokemons);

        PokemonResponse savePokemon = pokemonService.getAllPokemon(1,10);

        Assertions.assertNotNull(savePokemon);
    }

    @Test
    public void PokemonService_FindById_ReturnPokemonDto() {
        int pokemonId = 1;
        Pokemon pokemon = Pokemon.builder().id(1).name("pikachu").type("electric").type("this is a type").build();
        when(pokemonRepository.findById(pokemonId)).thenReturn(Optional.ofNullable(pokemon));

        PokemonDto pokemonReturn = pokemonService.getPokemonById(pokemonId);

        Assertions.assertNotNull(pokemonReturn);
    }

    @Test
    public void PokemonService_UpdatePokemon_ReturnUpdatedPokemonDto() {
        int pokemonId = 10;

        Pokemon existingPokemon = Pokemon.builder().id(pokemonId).name("pikachu").type("electric").build();
        PokemonDto pokemonDto = PokemonDto.builder().id(pokemonId).name("raichu").type("electric").build();

        when(pokemonRepository.findById(pokemonId)).thenReturn(Optional.of(existingPokemon));
        when(pokemonRepository.save(Mockito.any(Pokemon.class))).thenAnswer(invocation -> invocation.getArgument(0));

        PokemonDto updatedPokemon = pokemonService.updatePokemon(pokemonDto, pokemonId);

        Assertions.assertNotNull(updatedPokemon);
        Assertions.assertEquals("raichu", updatedPokemon.getName());
    }


    @Test
    public void PokemonService_DeletePokemonById_ReturnVoid() {
        int pokemonId = 10;

        Pokemon existingPokemon = Pokemon.builder().id(pokemonId).name("pikachu").type("electric").build();

        when(pokemonRepository.findById(pokemonId)).thenReturn(Optional.of(existingPokemon));
        doNothing().when(pokemonRepository).delete(Mockito.any(Pokemon.class));

        assertAll(() -> pokemonService.deletePokemonId(pokemonId));
    }
}
