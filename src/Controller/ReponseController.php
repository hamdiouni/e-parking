<?php

namespace App\Controller;

use App\Entity\Reponse;
use App\Form\ReponseType;
use App\Repository\ReponseRepository;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

class ReponseController extends AbstractController
{
    /**
     * @Route("/reponse", name="reponse_index", methods={"GET"})
     */
    public function index(ReponseRepository $reponseRepository): Response
    {
        $reponses = $reponseRepository->findAll();

        return $this->render('reponse/index.html.twig', [
            'reponses' => $reponses,
        ]);
    }

    public function new(Request $request, EntityManagerInterface $entityManager): Response
    {
        $reponse = new Reponse();
        $form = $this->createForm(ReponseType::class, $reponse);
        $form->handleRequest($request);
    
        if ($form->isSubmitted() && $form->isValid()) {
            $reponse->setDate(new \DateTime()); // Set the date property to the current system datetime
            $entityManager->persist($reponse);
            $entityManager->flush();
    
            return $this->redirectToRoute('reponse_index');
        }
    
        return $this->render('reponse/new.html.twig', [
            'reponse' => $reponse,
            'form' => $form->createView(),
        ]);
    }
    

    /**
     * @Route("/reponse/{id}", name="reponse_show", methods={"GET"})
     */
    public function show(Reponse $reponse): Response
    {
        return $this->render('reponse/show.html.twig', [
            'reponse' => $reponse,
        ]);
    }

    /**
     * @Route("/reponse/{id}/edit", name="reponse_edit", methods={"GET","POST"})
     */
    public function edit(Request $request, Reponse $reponse, EntityManagerInterface $entityManager): Response
    {
        $form = $this->createForm(ReponseType::class, $reponse);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager->flush();

            return $this->redirectToRoute('reponse_index');
        }

        return $this->render('reponse/edit.html.twig', [
            'reponse' => $reponse,
            'form' => $form->createView(),
        ]);
    }

    /**
 * @Route("/reponse/{id}/delete", name="reponse_delete")
 */
public function delete(Reponse $reponse, EntityManagerInterface $entityManager): Response
{
    $entityManager->remove($reponse);
    $entityManager->flush();

    return $this->redirectToRoute('reponse_index');
}

}
