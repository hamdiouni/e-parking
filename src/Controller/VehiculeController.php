<?php

namespace App\Controller;

use App\Entity\Vehicule;
use App\Form\VehiculeType;
use Doctrine\DBAL\Exception\UniqueConstraintViolationException;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

class VehiculeController extends AbstractController
{
     /**
     * @Route("/vehicules", name="vehicule_index", methods={"GET"})
     */
    public function index(): Response
    {
        $vehicules = $this->getDoctrine()
            ->getRepository(Vehicule::class)
            ->findAll();

        return $this->render('vehicule/index.html.twig', [
            'vehicules' => $vehicules,
        ]);
    }

   /**
 * @Route("/vehicules/new", name="vehicule_new", methods={"GET","POST"})
 */
public function new(Request $request): Response
{
    $vehicule = new Vehicule();
    $form = $this->createForm(VehiculeType::class, $vehicule);
    $form->handleRequest($request);

    if ($form->isSubmitted() && $form->isValid()) {
        $matricule = $vehicule->getMatricule();
        if (preg_match('/^\d+[A-Z]{3}\d+$/', $matricule) !== 1) {
            $this->addFlash('error', 'Le matricule doit contenir des chiffres à gauche et à droite de "TUN".');
            return $this->redirectToRoute('vehicule_new');
        }
        $entityManager = $this->getDoctrine()->getManager();
        try {
            $entityManager->persist($vehicule);
            $entityManager->flush();
            $this->addFlash('success', 'Le véhicule a été ajouté avec succès.');
            return $this->redirectToRoute('vehicule_index');
        } catch (UniqueConstraintViolationException $e) {
            $this->addFlash('error', 'Le matricule existe déjà dans la base de données.');
            return $this->redirectToRoute('vehicule_new');
        }
    }

    return $this->render('vehicule/new.html.twig', [
        'vehicule' => $vehicule,
        'form' => $form->createView(),
    ]);
}



    /**
     * @Route("/vehicules/{matricule}", name="vehicule_show", methods={"GET"})
     */
    public function show(string $matricule): Response
    {
        $entityManager = $this->getDoctrine()->getManager();
        $vehicule = $entityManager->getRepository(Vehicule::class)->findOneBy(['matricule' => $matricule]);

        if (!$vehicule) {
            throw $this->createNotFoundException(
                'Aucun véhicule trouvé avec le matricule '.$matricule
            );
        }

        return $this->render('vehicule/show.html.twig', [
            'vehicule' => $vehicule,
        ]);
    }

    /**
 * @Route("/vehicules/{matricule<^[A-Za-z0-9]+$>}/edit", name="vehicule_edit", methods={"GET","POST"})
 */
    public function edit(Request $request, string $matricule): Response
    {
        $entityManager = $this->getDoctrine()->getManager();
        $vehicule = $entityManager->getRepository(Vehicule::class)->findOneBy(['matricule' => $matricule]);

        if (!$vehicule) {
            throw $this->createNotFoundException(
                'Aucun véhicule trouvé avec le matricule '.$matricule
            );
        }

        $form = $this->createForm(VehiculeType::class, $vehicule);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager->flush();

            return $this->redirectToRoute('vehicule_index');
        }

        return $this->render('vehicule/edit.html.twig', [
            'vehicule' => $vehicule,
            'form' => $form->createView(),
        ]);
    }

/**
 * @Route("/vehicules/{matricule}", name="vehicule_delete", methods={"POST"})
 */
public function delete(Request $request, string $matricule): Response
{
    $entityManager = $this->getDoctrine()->getManager();
    $vehicule = $entityManager->getRepository(Vehicule::class)->findOneBy(['matricule' => $matricule]);

    if (!$vehicule) {
        throw $this->createNotFoundException(
            'Aucun véhicule trouvé avec le matricule '.$matricule
        );
    }

    $entityManager->remove($vehicule);
    $entityManager->flush();

    return $this->redirectToRoute('vehicule_index');
}






}

