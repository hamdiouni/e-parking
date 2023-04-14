<?php

namespace App\Entity;

use App\Repository\UserRepository;
use Doctrine\ORM\Mapping as ORM;
use Webmozart\Assert\InvalidArgumentException;
use Symfony\Component\HttpFoundation\File\UploadedFile;
use Symfony\Component\DependencyInjection\ParameterBag\ParameterBagInterface;
use Symfony\Component\Security\Core\User\UserInterface;

/**
 * @ORM\Entity(repositoryClass=UserRepository::class)
 * @ORM\Table(name="user")
 */
class User 
{
    /**
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="AUTO")
     * @ORM\Column(type="integer")
     */
    private $id_user;

    /**
     * @ORM\Column(type="string", length=255)
     */
    private $username;

    /**
     * @ORM\Column(type="string", length=255)
     */
    private $password;

    /**
     * @ORM\Column(type="string", length=255)
     */
    private $email;

  

    /**
     * @ORM\Column(type="string", length=255)
     */
    private $session;

    /**
     * @ORM\Column(type="string", nullable=true)
     */
    private $photo;

    private $params;

    public function getIdUser(): ?int
    {
        return $this->id_user;
    }

    public function getUsername(): ?string
    {
        return $this->username;
    }

    public function setUsername(string $username): self
    {
        $this->username = $username;

        return $this;
    }

    public function getPassword(): ?string
    {
        return $this->password;
    }

    public function setPassword(string $password): self
    {
        $this->password = $password;

        return $this;
    }

    public function getEmail(): ?string
    {
        return $this->email;
    }

    public function setEmail(string $email): self
    {
        $this->email = $email;

        return $this;
    }

   
 /**
     * @ORM\Column(type="string", length=255)
     */
    private $userType;

    // ...

    public function getUserType(): ?string
    {
        return $this->userType;
    }

    public function setUserType(string $userType): self
    {
        $this->userType = $userType;

        return $this;
    }
    public function getSession(): ?string
    {
        return $this->session;
    }

    public function setSession(string $session): self
    {
        $this->session = $session;

        return $this;
    }

    public function __construct(ParameterBagInterface $params)
    {
        $this->params = $params;
    }

    public function setPhoto($photo): self
    {
        // Use the myFunction to define the $params variable before using it
        $params = isset($params) ? $params : [];

        if ($photo instanceof UploadedFile) {
            // Vérifier si le type de fichier est valide
            $fileExtension = pathinfo($photo->getClientOriginalName(), PATHINFO_EXTENSION);
            if (!in_array(strtolower($fileExtension), ['jpg', 'jpeg', 'png', 'gif'])) {
                throw new InvalidArgumentException('Invalid photo type');
            }
            
            // Move the uploaded file to the photos directory
            $filename = uniqid().'.'.$photo->getClientOriginalExtension();
            $photo->move($this->params->get('photos_directory'), $filename);
            $this->photo = $filename;
        } else {
            $this->photo = $photo;
        }
        
        return $this;
    }

    public function getPhoto(): ?string
    {
        return $this->photo;
    }
    

   

    
    // Other methods...
}
