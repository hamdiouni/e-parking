<?php

namespace App\Entity;

use App\Repository\ParkingSpaceRepository;
use Doctrine\ORM\Mapping as ORM;

/**
 * @ORM\Entity(repositoryClass=ParkingSpaceRepository::class)
 * @ORM\Table(name="ParkingSpace")
 */
class ParkingSpace
{
    /**
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="AUTO")
     * @ORM\Column(type="integer")
     */
    private  $id = null;
      /**
     * @ORM\Column(type="integer")
     */

    private $id_place = null;

    /**
     * @ORM\Column(type="integer")
     */
    private $nombre_place = null;
    
  
    

/**
     * @ORM\Column(type="string", length=255)
     */
        private  $type = null;

    public function getId(): ?int
    {
        return $this->id;
    }

    public function getIdPlace(): ?int
    {
        return $this->id_place;
    }

    public function setIdPlace(int $id_place): self
    {
        $this->id_place = $id_place;

        return $this;
    }

    public function getNombrePlace(): ?int
    {
        return $this->nombre_place;
    }

    public function setNombrePlace(int $nombre_place): self
    {
        $this->nombre_place = $nombre_place;

        return $this;
    }

    public function getType(): ?string
    {
        return $this->type;
    }

    public function setType(string $type): self
    {
        $this->type = $type;

        return $this;
    }
}
