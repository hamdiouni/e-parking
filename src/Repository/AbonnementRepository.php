<?php

namespace App\Repository;

use App\Entity\Abonnement;
use Doctrine\Bundle\DoctrineBundle\Repository\ServiceEntityRepository;
use Doctrine\Persistence\ManagerRegistry;

class AbonnementRepository extends ServiceEntityRepository
{
    public function __construct(ManagerRegistry $registry)
    {
        parent::__construct($registry, Abonnement::class);
    }

    public function ajouter(Abonnement $abonnement)
    {
        if (!$this->isAbonnementValid($abonnement)) {
            throw new \InvalidArgumentException("Nom invalide ou tarif négatif.");
        }

        if ($abonnement->getTarif() > 300.0) {
            throw new \InvalidArgumentException("Tarif ne doit pas dépasser 300.0.");
        }

        $count = $this->createQueryBuilder('a')
            ->select('COUNT(a.id)')
            ->where('a.nom = :nom')
            ->setParameter('nom', $abonnement->getNom())
            ->getQuery()
            ->getSingleScalarResult();

        if ($count > 0) {
            throw new \InvalidArgumentException("Un abonnement avec ce nom existe déjà.");
        }

        $abonnement->setNom($this->setNom($abonnement));

        $entityManager = $this->getEntityManager();
        $entityManager->persist($abonnement);
        $entityManager->flush();
    }

    public function modifier(Abonnement $abonnement)
    {
        $entityManager = $this->getEntityManager();
        $query = $entityManager->createQuery('UPDATE App\Entity\Abonnement a SET a.nom = :nom, a.tarif = :tarif WHERE a.id = :id')
            ->setParameter('nom', $abonnement->getNom())
            ->setParameter('tarif', $abonnement->getTarif())
            ->setParameter('id', $abonnement->getId());
        $query->execute();
    }

    public function supprimer(Abonnement $abonnement)
    {
        $entityManager = $this->getEntityManager();
        $entityManager->remove($abonnement);
        $entityManager->flush();
    }

    public function recuperer()
    {
        return $this->findAll();
    }

    

    private function setNom(Abonnement $abonnement)
    {
        $nom = $abonnement->getNom();
        if (preg_match('/^(gold|silver|bronze)$/', $nom)) {
            return $nom;
        }
        return "personalise";
        }
        public function isAbonnementValid(Abonnement $abonnement)
{
    $nom = $abonnement->getNom();
    $tarif = $abonnement->getTarif();

    // Vérifier que le nom est l'un des noms prédéfinis (gold, silver, bronze) ou un nom personnalisé
    if (!preg_match('/^(gold|silver|bronze|[a-zA-Z]+)$/', $nom)) {
        return false;
    }

    // Vérifier que le tarif est positif
    if ($tarif < 0) {
        return false;
    }

    // Vérifier que le tarif correspond au nom du code d'abonnement
    switch ($nom) {
        case "gold":
            if ($tarif != 200.0) {
                return false;
            }
            break;
        case "silver":
            if ($tarif != 150.0) {
                return false;
            }
            break;
        case "bronze":
            if ($tarif != 80.0) {
                return false;
            }
            break;
    }

    // Si toutes les vérifications sont passées, renvoyer true
    return true;
}
}