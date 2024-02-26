package com.hotelerie_api.controller;

import com.hotelerie_api.model.CategorieChambre;
import com.hotelerie_api.repository.CategorieChambreRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@WebMvcTest(CategorieChambreController.class)
public class CategorieChambreControllerTest {

    @MockBean
    private CategorieChambreRepository categorieChambreRepository;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testCreateCategorieChambre() throws Exception {
        CategorieChambre categorieChambre = new CategorieChambre();
        categorieChambre.setNom("Test Category");

        Mockito.when(categorieChambreRepository.save(Mockito.any(CategorieChambre.class)))
                .thenReturn(categorieChambre);

        mockMvc.perform(MockMvcRequestBuilders.post("/categories")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nom\":\"Test Category\"}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.nom").value("Test Category"));
    }

    @Test
    public void testGetCategorieChambreById() throws Exception {
        CategorieChambre categorieChambre = new CategorieChambre();
        categorieChambre.setId(1);
        categorieChambre.setNom("Test Category");

        Mockito.when(categorieChambreRepository.findById(1)).thenReturn(Optional.of(categorieChambre));

        mockMvc.perform(MockMvcRequestBuilders.get("/categories/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nom").value("Test Category"));
    }

    @Test
    public void testGetAllCategoriesChambres() throws Exception {
        CategorieChambre categorieChambre1 = new CategorieChambre();
        categorieChambre1.setId(1);
        categorieChambre1.setNom("Category 1");

        CategorieChambre categorieChambre2 = new CategorieChambre();
        categorieChambre2.setId(2);
        categorieChambre2.setNom("Category 2");

        List<CategorieChambre> categories = Arrays.asList(categorieChambre1, categorieChambre2);

        Mockito.when(categorieChambreRepository.findAllCategoriesChambre()).thenReturn(categories);

        mockMvc.perform(MockMvcRequestBuilders.get("/categories"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].nom").value("Category 1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].nom").value("Category 2"));
    }

    @Test
    public void testUpdateCategorieChambre() throws Exception {
        CategorieChambre categorieChambre = new CategorieChambre();
        categorieChambre.setId(1);
        categorieChambre.setNom("Old Category");

        CategorieChambre updatedCategorieChambre = new CategorieChambre();
        updatedCategorieChambre.setId(1);
        updatedCategorieChambre.setNom("New Category");


