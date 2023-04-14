<?php

namespace App\Controller;

use App\Entity\Abonnement;
use App\Form\AbonnementType;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\ParamConverter;

use App\Repository\AbonnementRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

class AbonnementController extends AbstractController
{
    /**
     * @Route("/abonnement", name="abonnement_index")
     */
    public function index(AbonnementRepository $abonnementRepository): Response
    {
        $abonnements = $abonnementRepository->recuperer();

        return $this->render('abonnement/index.html.twig', [
            'abonnements' => $abonnements,
        ]);
    }
    /**
     * @Route("/abonnement2", name="abonnement_front")
     */
    public function index2(AbonnementRepository $abonnementRepository): Response
    {
        $abonnements = $abonnementRepository->recuperer();

        return $this->render('abonnement/index2.html.twig', [
            'abonnements' => $abonnements,
        ]);
    }

    /**
     * @Route("/abonnement/nouveau", name="abonnement_nouveau")
     */
    public function nouveau(Request $request): Response
    {
        $abonnement = new Abonnement();
        $form = $this->createForm(AbonnementType::class, $abonnement);

        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->persist($abonnement);
            $entityManager->flush();

            $this->addFlash('success', 'Abonnement ajouté avec succès.');

            return $this->redirectToRoute('abonnement_index');
        }

        return $this->render('abonnement/nouveau.html.twig', [
            'form' => $form->createView(),
            'errors' => $form->getErrors(true)
        
        ]);
    }
     /**
     * @Route("/abonnement/modifier/{id}", name="abonnement_modifier")
     */
    public function modifier(Request $request, Abonnement $abonnement): Response
    {
        $form = $this->createForm(AbonnementType::class, $abonnement);

        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->persist($abonnement);
            $entityManager->flush();

            $this->addFlash('success', 'Abonnement modifié avec succès.');

            return $this->redirectToRoute('abonnement_index');
        }

        return $this->render('abonnement/modifier.html.twig', [
            'abonnement' => $abonnement,
            'form' => $form->createView(),
        ]);
    }
/**
 * @Route("/abonnement/{id}/supprimer", name="abonnement_supprimer")
 */
public function supprimer(Request $request, Abonnement $abonnement): Response
{
    $entityManager = $this->getDoctrine()->getManager();
    $entityManager->remove($abonnement);
    $entityManager->flush();

    $this->addFlash('success', 'Abonnement supprimé avec succès.');

    return $this->redirectToRoute('abonnement_index');
}
/**
 * @Route("/abonnement/choisir/{id}", name="abonnement_choisir")
 */
public function choisir(Request $request, Abonnement $abonnement): Response
{
    $form = $this->createFormBuilder()
        ->add('submit', SubmitType::class, [
            'label' => 'Choisir cet abonnement',
            'attr' => ['class' => 'btn btn-primary'],
        ])
        ->getForm();
    
    $form->handleRequest($request);
    
    if ($form->isSubmitted() && $form->isValid()) {
        // Ajouter le code pour enregistrer le choix de l'abonnement pour le client ici
        $this->addFlash('success', 'Abonnement choisi avec succès.');
        return $this->redirectToRoute('abonnement_index');
    }
    
    return $this->render('abonnement/choisir.html.twig', [
        'abonnement' => $abonnement,
        'form' => $form->createView(),
    ]);
}
}