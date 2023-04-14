<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Interactions
 *
 * @ORM\Table(name="interactions", indexes={@ORM\Index(name="fk", columns={"id_poste"})})
 * @ORM\Entity
 */
class Interactions
{
    /**
     * @var int
     *
     * @ORM\Column(name="id", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $id;

    /**
     * @var string
     *
     * @ORM\Column(name="auteur", type="string", length=200, nullable=false)
     */
    private $auteur;

    /**
     * @var string
     *
     * @ORM\Column(name="type", type="string", length=200, nullable=false)
     */
    private $type;

    /**
     * @var \DateTime
     *
     * @ORM\Column(name="date_creation", type="date", nullable=false)
     */
    private $dateCreation;

    /**
     * @var string
     *
     * @ORM\Column(name="contenu", type="string", length=200, nullable=false)
     */
    private $contenu;

    /**
     * @var int
     *
     * @ORM\Column(name="note", type="integer", nullable=false)
     */
    private $note;

    /**
     * @var \Poste
     *
     * @ORM\ManyToOne(targetEntity="Poste")
     * @ORM\JoinColumns({
     *   @ORM\JoinColumn(name="id_poste", referencedColumnName="id_poste")
     * })
     */
    private $idPoste;


}
