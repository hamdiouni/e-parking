<?php

namespace App\Controller;

use App\Entity\Categorie;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

class CategorieController extends AbstractController
{
    /**
     * @Route("/categorie/ajouter", name="categorie_ajouter")
     */
    public function ajouter(Request $request): Response
    {
        $categorie = new Categorie();
        $form = $this->createFormBuilder($categorie)
            ->add('categorievoiture')
            ->getForm();

        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->persist($categorie);
            $entityManager->flush();

            return $this->redirectToRoute('categorie_afficher');
        }

        return $this->render('categorie/ajouter.html.twig', [
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/categorie/afficher", name="categorie_afficher")
     */
    public function afficher(): Response
    {
        $categories = $this->getDoctrine()
            ->getRepository(Categorie::class)
            ->findAll();

        return $this->render('categorie/afficher.html.twig', [
            'categories' => $categories,
        ]);
    }
}
