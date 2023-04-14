<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Categorie
 *
 * @ORM\Table(name="categorie")
 * @ORM\Entity
 */
class Categorie
{
    /**
     * @var int
     *
     * @ORM\Column(name="id_categorie", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $idCategorie;

    /**
     * @var string
     *
     * @ORM\Column(name="categorieVoiture", type="string", length=30, nullable=false)
     */
    private $categorievoiture;

    /**
     * Get the value of idCategorie
     *
     * @return  int
     */ 
    public function getIdCategorie()
    {
        return $this->idCategorie;
    }

    /**
     * Set the value of idCategorie
     *
     * @param  int  $idCategorie
     *
     * @return  self
     */ 
    public function setIdCategorie(int $idCategorie)
    {
        $this->idCategorie = $idCategorie;

        return $this;
    }

    /**
     * Get the value of categorievoiture
     *
     * @return  string
     */ 
    public function getCategorievoiture()
    {
        return $this->categorievoiture;
    }

    /**
     * Set the value of categorievoiture
     *
     * @param  string  $categorievoiture
     *
     * @return  self
     */ 
    public function setCategorievoiture(string $categorievoiture)
    {
        $this->categorievoiture = $categorievoiture;

        return $this;
    }
}
