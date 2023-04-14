<?php

namespace App\Controller;

use App\Entity\ParkingSpace;
use App\Form\ParkingSpaceType;
use App\Repository\ParkingSpaceRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

#[Route('/parking/space')]
class ParkingSpaceController extends AbstractController
{
    #[Route('/', name: 'app_parking_space_index', methods: ['GET'])]
    public function index(ParkingSpaceRepository $parkingSpaceRepository): Response
    {
        return $this->render('parking_space/index.html.twig', [
            'parking_spaces' => $parkingSpaceRepository->findAll(),
        ]);
    }

    #[Route('/new', name: 'app_parking_space_new', methods: ['GET', 'POST'])]
    public function new(Request $request, ParkingSpaceRepository $parkingSpaceRepository): Response
    {
        $parkingSpace = new \App\Entity\ParkingSpace();
        $form = $this->createForm(ParkingSpaceType::class, $parkingSpace);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $parkingSpaceRepository->save($parkingSpace, true);

            return $this->redirectToRoute('app_parking_space_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->renderForm('parking_space/new.html.twig', [
            'parking_space' => $parkingSpace,
            'form' => $form,
        ]);
    }

    #[Route('/{id}', name: 'app_parking_space_show', methods: ['GET'])]
    public function show(ParkingSpace $parkingSpace): Response
    {
        return $this->render('parking_space/show.html.twig', [
            'parking_space' => $parkingSpace,
        ]);
    }

    #[Route('/{id}/edit', name: 'app_parking_space_edit', methods: ['GET', 'POST'])]
    public function edit(Request $request, ParkingSpace $parkingSpace, ParkingSpaceRepository $parkingSpaceRepository): Response
    {
        $form = $this->createForm(ParkingSpaceType::class, $parkingSpace);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $parkingSpaceRepository->save($parkingSpace, true);

            return $this->redirectToRoute('app_parking_space_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->renderForm('parking_space/edit.html.twig', [
            'parking_space' => $parkingSpace,
            'form' => $form,
        ]);
    }

    #[Route('/{id}', name: 'app_parking_space_delete', methods: ['POST'])]
    public function delete(Request $request, ParkingSpace $parkingSpace, ParkingSpaceRepository $parkingSpaceRepository): Response
    {
        if ($this->isCsrfTokenValid('delete'.$parkingSpace->getId(), $request->request->get('_token'))) {
            $parkingSpaceRepository->remove($parkingSpace, true);
        }

        return $this->redirectToRoute('app_parking_space_index', [], Response::HTTP_SEE_OTHER);
    }
}
