<?php

namespace App\Form;

use App\Entity\Reclamation;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;

use Symfony\Component\Form\Extension\Core\Type\TextType;
use Symfony\Component\Validator\Constraints\NotBlank;
use Symfony\Component\Validator\Constraints\Regex;


class ReclamationType extends AbstractType
{
    
    public function buildForm(FormBuilderInterface $builder, array $options)
    {
        $builder
            ->add('nom_client', TextType::class, [
                'constraints' => [
                    new NotBlank(['message' => 'Le nom du client est requis']),
                    new Regex([
                        'pattern' => '/^[a-zA-Z\s]+$/',
                        'message' => 'Le nom du client ne peut contenir que des lettres'
                        
                    ]),
                ],
            ])
            ->add('sujet', TextType::class, [
                'constraints' => [
                    new NotBlank(['message' => 'Le sujet est requis']),
                    new Regex([
                        'pattern' => '/^[a-zA-Z\s]+$/',
                        'message' => 'Le sujet ne peut contenir que des lettres'
                    ]),
                ],
            ])
            ->add('etat', TextType::class, [
                'constraints' => [
                    new NotBlank(['message' => "L'eta est requis"]),
                    new Regex([
                        'pattern' => '/^[a-zA-Z\s]+$/',
                        'message' => "L'eta ne peut contenir que des lettres"
                    ]),
                ],
            ]);
    }

    public function configureOptions(OptionsResolver $resolver): void
    {
        $resolver->setDefaults([
            'data_class' => Reclamation::class,
        ]);
    }
}
