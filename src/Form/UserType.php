<?php

namespace App\Form;

use App\Entity\User;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\Extension\Core\Type\FileType as SymfonyFileType;
use Symfony\Component\Form\Extension\Core\Type\ChoiceType;
use Symfony\Component\Form\Extension\Core\Type\EmailType;
use Symfony\Component\Form\Extension\Core\Type\PasswordType;
use Symfony\Component\Form\Extension\Core\Type\RepeatedType;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\Form\Extension\Core\Type\TextType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;
use Symfony\Component\Validator\Constraints\Email;
use Symfony\Component\Validator\Constraints\File;
use Symfony\Component\Validator\Constraints\Length;
use Symfony\Component\Validator\Constraints\NotBlank;
use Symfony\Component\Validator\Constraints\Regex;
use Symfony\Component\Form\Extension\Core\Type\FileType;

use Symfony\Component\Validator\Constraints as Assert;
use Symfony\Component\Validator\Constraints\Max;

class UserType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options)
{
    $builder
        ->add('username', TextType::class, [
            'label' => 'Nom d\'utilisateur',
            'constraints' => [
                new NotBlank([
                    'message' => 'Veuillez entrer un nom d\'utilisateur.'
                ]),
                new Length([
                    'min' => 3,
                    'max' => 255,
                    'minMessage' => 'Le nom d\'utilisateur doit comporter au moins {{ limit }} caractères.',
                    'maxMessage' => 'Le nom d\'utilisateur doit comporter au maximum {{ limit }} caractères.'
                ]),
                new Regex([
                    'pattern' => '/^[a-zA-Z]+$/',
                    'message' => 'Le nom d\'utilisateur doit contenir uniquement des lettres.'
                ])
            ]
        ])
        ->add('email', EmailType::class, [
            'label' => 'Adresse email',
            'constraints' => [
                new NotBlank([
                    'message' => 'Veuillez entrer une adresse email.'
                ]),
                new Length([
                    'max' => 255,
                    'maxMessage' => 'L\'adresse email doit comporter au maximum {{ limit }} caractères.'
                ]),
                new Email([
                    'message' => 'L\'adresse email n\'est pas valide.'
                ])
            ]
        ])
        ->add('password', RepeatedType::class, [
            'type' => PasswordType::class,
            'invalid_message' => 'Les deux mots de passe ne correspondent pas.',
            'required' => true,
            'first_options' => [
                'label' => 'Mot de passe',
                'constraints' => [
                    new NotBlank([
                        'message' => 'Veuillez entrer un mot de passe.'
                    ]),
                    new Length([
                        'min' => 8,
                        'max' => 255,
                        'minMessage' => 'Le mot de passe doit comporter au moins {{ limit }} caractères.',
                        'maxMessage' => 'Le mot de passe doit comporter au maximum {{ limit }} caractères.'
                    ])
                ]
            ],
            'second_options' => [
                'label' => 'Répéter le mot de passe',
                'constraints' => [
                    new NotBlank([
                        'message' => 'Veuillez répéter le mot de passe.'
                    ])
                ]
            ],
            
        ])



        ->add('userType', ChoiceType::class, [
            'label' => 'userType',
            'choices' => [
                'Utilisateur' => 'ROLE_USER',
                'Administrateur' => 'ROLE_ADMIN',
            ],
            'expanded' => true,
            'multiple' => false,
            'required' => true,
        ])
        ->add('photo', FileType::class, [
            'label' => 'Photo de profil (JPG, PNG ou GIF)',
            'required' => false,
            'mapped' => false,
            'constraints' => [
            new File([
            'maxSize' => '10M',
            'mimeTypes' => [
            'image/jpeg',
            'image/png',
            'image/gif',
            ],
            'mimeTypesMessage' => 'Veuillez télécharger une image au format JPG, PNG ou GIF',
            ])
            ],
            ])
        ->add('session', TextType::class, [
            'label' => 'Session',
            'required' => true,
        ])
        
      ;
}

    public function configureOptions(OptionsResolver $resolver)
    {
        $resolver->setDefaults([
            'data_class' => User::class,
        ]);
    }
}
