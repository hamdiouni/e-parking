<?php

namespace App\Form;

use App\Entity\Abonnement;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\Extension\Core\Type\ChoiceType;
use Symfony\Component\Form\Extension\Core\Type\NumberType;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;

class AbonnementType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options)
    {
        $builder
            ->add('nom', ChoiceType::class, [
                'choices' => [
                    'Gold' => 'gold',
                    'Silver' => 'silver',
                    'Bronze' => 'bronze',
                    'Personnalisé' => 'personalise',
                ],
                'label' => 'Nom',
                'required' => true,
            ])
            ->add('tarif', NumberType::class, [
                'label' => 'Tarif',
                'required' => true,
            ])
           ;
    }

    public function configureOptions(OptionsResolver $resolver)
    {
        $resolver->setDefaults([
            'data_class' => Abonnement::class,
        ]);
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

        return true;
    }
}
