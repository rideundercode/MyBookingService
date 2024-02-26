
package com.hotelerie_api.repository;

import com.hotelerie_api.model.CategorieChambre;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategorieChambreRepository extends CrudRepository<CategorieChambre, Integer> {

    @Query(value = "INSERT INTO categorie_chambre (nom) VALUES (?1)", nativeQuery = true)
    CategorieChambre createCategorieChambre(String nom);

    @Query(value = "SELECT * FROM categorie_chambre WHERE id = ?1", nativeQuery = true)
    Optional<CategorieChambre> findById(Integer id);

    @Query(value = "SELECT * FROM categorie_chambre", nativeQuery = true)
    List<CategorieChambre> findAllCategoriesChambre();

    @Query(value = "UPDATE categorie_chambre SET nom = ?1 WHERE id = ?2", nativeQuery = true)
    CategorieChambre updateCategorieChambre(String nom, Integer id);

    @Query(value = "DELETE FROM categorie_chambre WHERE id = ?1", nativeQuery = true)
    void deleteCategorieChambre(Integer id);

    @Query(value = "SELECT * FROM categorie_chambre", nativeQuery = true)
    List<CategorieChambre> findAll();
}

