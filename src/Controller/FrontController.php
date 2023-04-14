<?php

namespace App\Controller;

use App\Entity\Vehicule;
use App\Form\VehiculeType;
use App\Repository\ReclamationRepository;
use Doctrine\DBAL\Exception\UniqueConstraintViolationException;
use App\Entity\Reclamation;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\ParamConverter;
use App\Form\ReclamationType;

use App\Repository\AbonnementRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

class FrontController extends AbstractController
{

 /**
 * @Route("/testimonial", name="testimonial")
 */
public function testimonial(Request $request): Response
{
    $reclamation = new Reclamation(); 
    $form = $this->createForm(ReclamationType::class, $reclamation);
    $form->handleRequest($request);
    
    if ($form->isSubmitted() && $form->isValid()) {
        $em = $this->getDoctrine()->getManager();
        $em->persist($reclamation);
        $em->flush();
   
        $this->addFlash('notice', 'Submitted Successfully!!');
        return $this->redirectToRoute('your_profil2', ['id' => $reclamation->getId()]);
    }
    
    return $this->render('front/testimonial.html.twig', [
        'form' => $form->createView()
    ]);
}
   /**
 * @Route("/front", name="front")
 */
public function front(Request $request): Response
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
    return $this->render('front/front.html.twig', [
        'form' => $form->createView(),
    ]);
}


/**
 * @Route("/about", name="about")
 */
public function about(): Response
{
    return $this->render('front/about.html.twig');
}

/**
 * @Route("/pricing", name="pricing")
 */
public function pricing(): Response
{
    return $this->render('front/pricing.html.twig');
}

/**
 * @Route("/why", name="why")
 */
public function why(): Response
{
    return $this->render('front/why.html.twig');
} 
/**

*@Route("/profile", name="user_profile")
*/
public function profile(Request $request): Response
{
    $session = $request->getSession();
    $userType = $session->get('role'); // récupérer la valeur de la variable de session role
    
    $vehicule = new Vehicule();
    $form = $this->createForm(VehiculeType::class, $vehicule);

    $form->handleRequest($request);
    if ($form->isSubmitted() && $form->isValid()) {
        $matricule = $vehicule->getMatricule();
        if (preg_match('/^\d+[A-Z]{3}\d+$/', $matricule) !== 1) {
            $this->addFlash('error', 'Le matricule doit contenir des chiffres à gauche et à droite de "TUN".');
            return $this->redirectToRoute('user_profile');
        }
        $entityManager = $this->getDoctrine()->getManager();
        try {
            $entityManager->persist($vehicule);
            $entityManager->flush();
            $this->addFlash('success', 'Le véhicule a été ajouté avec succès.');
            return $this->redirectToRoute('your_profil', ['matricule' => $vehicule->getMatricule()]);
        } catch (UniqueConstraintViolationException $e) {
            $this->addFlash('error', 'Le matricule existe déjà dans la base de données.');
            return $this->redirectToRoute('user_profile');
        }
    }

    return $this->render('user/profile.html.twig', [
        'form' => $form->createView(),
        'user' => $this->getUser(),
        'userType' => $userType, // passer la valeur de la variable de session en tant que variable locale
    ]);
} 

/**
 * @Route("/{matricule}/your_profil", name="your_profil")
 */
public function your_profil(Request $request, string $matricule): Response
{
    $vehicule = $this->getDoctrine()->getRepository(Vehicule::class)->findOneBy(['matricule' => $matricule]);

    // vérifier si le véhicule est trouvé ou non
    if (!$vehicule) {
        throw $this->createNotFoundException('Le véhicule n\'existe pas');
    }


    return $this->render('front/profil.html.twig', [
        'vehicule' => $vehicule,
    ]);
}
/**
 * @Route("/{id}/your_profil2", name="your_profil2")
 */
public function your_profil2(int $id): Response
{
    $reclamation = $this->getDoctrine()->getRepository(Reclamation::class)->find($id);

    if (!$reclamation) {
        throw $this->createNotFoundException('La réclamation n\'existe pas');
    }

    return $this->render('reclamation/rec.html.twig', [
        'reclamation' => $reclamation
    ]);
}


   
}