<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Vehicule
 *
 * @ORM\Table(name="vehicule")
 * @ORM\Entity
 */
class Vehicule
{
    /**
     * @var string
     *
     * @ORM\Column(name="matricule", type="string", length=30, nullable=false)
     * @ORM\Id
     */
    private $matricule;

    /**
     * @var string
     *
     * @ORM\Column(name="marque", type="string", length=30, nullable=false)
     */
    private $marque;

    /**
     * @var string
     *
     * @ORM\Column(name="modele", type="string", length=30, nullable=false)
     */
    private $modele;

    /**
     * @var string
     *
     * @ORM\Column(name="couleur", type="string", length=30, nullable=false)
     */
    private $couleur;

    /**
     * @var string
     *
     * @ORM\Column(name="categorieVoiture", type="string", length=30, nullable=false)
     */
    private $categorievoiture;

    public function getMatricule(): ?string
    {
        return $this->matricule;
    }

    public function setMatricule(string $matricule): self
    {
        $this->matricule = $matricule;

        return $this;
    }

    public function getMarque(): ?string
    {
        return $this->marque;
    }

    public function setMarque(string $marque): self
    {
        $this->marque = $marque;

        return $this;
    }

    public function getModele(): ?string
    {
        return $this->modele;
    }

    public function setModele(string $modele): self
    {
        $this->modele = $modele;

        return $this;
    }

    public function getCouleur(): ?string
    {
        return $this->couleur;
    }

    public function setCouleur(string $couleur): self
    {
        $this->couleur = $couleur;

        return $this;
    }

    public function getCategorieVoiture(): ?string
    {
        return $this->categorievoiture;
    }

    public function setCategorieVoiture(string $categorievoiture): self
    {
        $this->categorievoiture = $categorievoiture;

        return $this;
    }
}
