<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Poste
 *
 * @ORM\Table(name="poste")
 * @ORM\Entity
 */
class Poste
{
    /**
     * @var int
     *
     * @ORM\Column(name="id_poste", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $idPoste;

    /**
     * @var string
     *
     * @ORM\Column(name="sujet_poste", type="string", length=200, nullable=false)
     */
    private $sujetPoste;

    /**
     * @var string
     *
     * @ORM\Column(name="nom_auteur", type="string", length=20, nullable=false)
     */
    private $nomAuteur;

    /**
     * @var \DateTime
     *
     * @ORM\Column(name="date", type="date", nullable=false)
     */
    private $date;


}
