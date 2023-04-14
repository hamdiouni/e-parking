<?php

namespace App\Entity;

use App\Repository\ReponseRepository;
use Doctrine\ORM\Mapping as ORM;

/**
 * @ORM\Entity(repositoryClass=ReponseRepository::class)
 * @ORM\Table(name="reponse")
 */
class Reponse
{
    /**
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="AUTO")
     * @ORM\Column(type="integer")
     */    private  $id = null;

/**
     * @ORM\Column(type="string", length=255)
     */
        private  $reponse = null;
/**
 * @ORM\Column(type="datetime")
 */
private $date;
    public function getId(): ?int
    {
        return $this->id;
    }

    public function getReponse(): ?string
    {
        return $this->reponse;
    }

    public function setReponse(?string $reponse): self
    {
        $this->reponse = $reponse;

        return $this;
    }
    public function getDate(): ?\DateTimeInterface
{
    return $this->date;
}

public function setDate(?\DateTimeInterface $date): self
{
    $this->date = $date;

    return $this;
}

}

