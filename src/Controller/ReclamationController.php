<?php

namespace App\Controller;
use App\Entity\Reclamation;
use App\Form\ReclamationType;
use App\Repository\ReclamationRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\Routing\Annotation\Route;

class ReclamationController extends AbstractController
{
    #[Route('/reclamation', name: 'app_reclamation')]
    public function index(): Response
    {
        $data = $this->getDoctrine()->getRepository(Reclamation::class)->findAll();
        return $this->render('reclamation/index.html.twig', [
            'list' => $data
        ]);
    }
    
    /**
     *  @Route("/create", name="create")
     */
    public function create(Request $request){
        $reclamation= new Reclamation(); 
        $form= $this->createForm(ReclamationType::class ,$reclamation);
        $form->handleRequest($request);
        if($form->isSubmitted() && $form->isValid()){
        $em = $this->getDoctrine()->getManager();
        $em->persist($reclamation);
        $em->flush();
   
        $this->addFlash('notice','Submitted Successfully!!');
   
        
     return $this->redirectToRoute('app_reclamation');
        }
        return $this->render('reclamation/create.html.twig',[
           'form' =>$form->createView()
        ]);
       }
       /**
 * @Route("/testimonial", name="testimonial")
 */
public function testimonial(Request $request): Response
{
     $reclamation= new Reclamation(); 
        $form= $this->createForm(ReclamationType::class ,$reclamation);
        $form->handleRequest($request);
        if($form->isSubmitted() && $form->isValid()){
        $em = $this->getDoctrine()->getManager();
        $em->persist($reclamation);
        $em->flush();
   
        $this->addFlash('notice','Submitted Successfully!!');
        return $this->redirectToRoute('app_reclamation');
        }
    return $this->render('front/testimonial.html.twig',[
           'form' =>$form->createView()]);
    }

       /**
     * @Route("/update/{id}" , name="update")
     * 
     */
    public function update(Request $request, $id){
       
        $reclamation= $this->getDoctrine()->getRepository(Reclamation::class)->find($id); 
        $form= $this->createForm(ReclamationType::class ,$reclamation);
        $form->handleRequest($request);
        if($form->isSubmitted() && $form->isValid()){
        $em = $this->getDoctrine()->getManager();
        $em->persist($reclamation);
        $em->flush();
   
        $this->addFlash('notice','update Successfully!!');
   
        return $this->redirectToRoute('app_reclamation');
        }
        return $this->render('reclamation/update.html.twig',[
           'form' =>$form->createView()
        ]);

    }
    /**
     *  @Route("/delete/{id}" , name="delete")
     */
    public function delete($id)
{
$data = $this->getDoctrine()->getRepository(Reclamation::class)->find($id);
$em = $this->getDoctrine()->getManager();
$em->remove($data);
$em->flush();

$this->addFlash('notice','Deleted Successfully!!');

return $this->redirectToRoute('app_reclamation');
}
/**
     * @Route("/reclamations/{id}", name="reclamation_show", methods={"GET"})
     */
    public function show(int $id, ReclamationRepository $reclamationRepository): Response
    {
        $reclamation = $reclamationRepository->find($id);

        if (!$reclamation) {
            throw $this->createNotFoundException('Réclamation non trouvée');
        }

        return $this->render('reclamation/show.html.twig', [
            'reclamation' => $reclamation,
        ]);
    }
     
}
