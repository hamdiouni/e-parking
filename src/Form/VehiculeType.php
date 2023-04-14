<?php

namespace App\Form;

use App\Entity\Vehicule;
use App\Entity\Categorie;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\Extension\Core\Type\ChoiceType;
use Symfony\Component\Form\Extension\Core\Type\TextType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;
use Symfony\Component\Validator\Constraints\Regex;

class VehiculeType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options)
    {
        $builder
        ->add('matricule', TextType::class, [
            'label' => 'Matricule',
            'constraints' => [
                new Regex([
                    'pattern' => '/^\d{3}TUN\d{4}$/',
                    'message' => 'Le format du matricule est invalide. Veuillez entrer un matricule avec 3 chiffres à gauche et à droite de "TUN".',
                ]),
            ],
        ])
            ->add('couleur', ChoiceType::class, [
                'label' => 'Couleur',
                'choices' => [
                    'Blanc' => 'blanc',
                    'Noir' => 'noir',
                    'Gris' => 'gris',
                    'Bleu' => 'bleu',
                    'Rouge' => 'rouge',
                    'Vert' => 'vert',
                ]
            ])
            ->add('marque', ChoiceType::class, [
                'label' => 'Marque',
                'choices' => [
                    'Renault' => 'Renault',
                    'Peugeot' => 'Peugeot',
                    'Citroen' => 'Citroen',
                    'Volkswagen' => 'Volkswagen',
                    'Ford' => 'Ford',
                ]
            ])
            ->add('modele', ChoiceType::class, [
                'label' => 'Modèle',
                'choices' => [
                    'Clio' => 'Clio',
                    'Megane' => 'Megane',
                    '208' => '208',
                    '308' => '308',
                    'C4' => 'C4',
                    'Golf' => 'Golf',
                    'Fiesta' => 'Fiesta',
                ]
            ])
            ->add('categorieVoiture', ChoiceType::class, [
                'label' => 'Catégorie',
                'choices' => $this->getCategorieChoices(),
            ]);
    }

    private $entityManager;

    public function __construct(EntityManagerInterface $entityManager)
    {
        $this->entityManager = $entityManager;
    }

    private function getCategorieChoices()
{
    $categories = $this->entityManager->getRepository(Categorie::class)->findAll();
    $choices = [];
    foreach ($categories as $categorie) {
        $choices[$categorie->getCategorievoiture()] = $categorie->getCategorievoiture();
    }
    return $choices;
}


    public function configureOptions(OptionsResolver $resolver)
    {
        $resolver->setDefaults([
            'data_class' => Vehicule::class,
        ]);
    }
 
}
