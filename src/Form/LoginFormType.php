<?php
// src/Form/LoginFormType.php

namespace App\Form;

use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\Extension\Core\Type\PasswordType;
use Symfony\Component\Form\Extension\Core\Type\TextType;
use Symfony\Component\Form\FormBuilderInterface;

class LoginFormType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options)
    {
        $builder
            ->add('email', TextType::class, [
                'label' => 'Nom d\'utilisateur',
            ])->add('username', TextType::class, [
                'label' => 'Nom d\'utilisateur',
            ])
            ->add('password', PasswordType::class, [
                'label' => 'Mot de passe',
            ]);
    }
}
