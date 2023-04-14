<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Commentaire
 *
 * @ORM\Table(name="commentaire", indexes={@ORM\Index(name="sk_commentaire_interactions", columns={"idinter"})})
 * @ORM\Entity
 */
class Commentaire
{
    /**
     * @var int
     *
     * @ORM\Column(name="idc", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $idc;

    /**
     * @var string
     *
     * @ORM\Column(name="contenu", type="string", length=255, nullable=false)
     */
    private $contenu;

    /**
     * @var \Interactions
     *
     * @ORM\ManyToOne(targetEntity="Interactions")
     * @ORM\JoinColumns({
     *   @ORM\JoinColumn(name="idinter", referencedColumnName="id")
     * })
     */
    private $idinter;


}
