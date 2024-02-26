package com.hotelerie_api.controller;

import com.hotelerie_api.model.CategorieChambre;
import com.hotelerie_api.repository.CategorieChambreRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/categories")
public class CategorieChambreController {

    private final CategorieChambreRepository categorieChambreRepository;

    public CategorieChambreController(CategorieChambreRepository categorieChambreRepository) {
        this.categorieChambreRepository = categorieChambreRepository;
    }

    @PostMapping
    public CategorieChambre createCategorieChambre(@RequestBody CategorieChambre categorieChambre) {
        return categorieChambreRepository.save(categorieChambre);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategorieChambre> getCategorieChambreById(@PathVariable("id") Integer id) {
        Optional<CategorieChambre> optionalCategorieChambre = categorieChambreRepository.findById(id);
        return optionalCategorieChambre.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }


    @GetMapping
    public List<CategorieChambre> getAllCategoriesChambres() {
        return categorieChambreRepository.findAllCategoriesChambre();
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategorieChambre> updateCategorieChambre(@PathVariable("id") Integer id, @RequestBody CategorieChambre categorieChambreDetails) {
        Optional<CategorieChambre> optionalCategorieChambre = categorieChambreRepository.findById(id);
        return optionalCategorieChambre.map(categorieChambre -> {
            categorieChambre.setNom(categorieChambreDetails.getNom());
            // Mettre à jour d'autres attributs de la catégorie de chambre si nécessaire
            return ResponseEntity.ok(categorieChambreRepository.save(categorieChambre));
        }).orElse(ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCategorieChambre(@PathVariable("id") Integer id) {
        Optional<CategorieChambre> optionalCategorieChambre = categorieChambreRepository.findById(id);
        return optionalCategorieChambre.map(categorieChambre -> {
            categorieChambreRepository.delete(categorieChambre);
            return ResponseEntity.noContent().build();
        }).orElse(ResponseEntity.badRequest().build());
    }
}
