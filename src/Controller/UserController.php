<?php

namespace App\Controller;

use App\Entity\User;
use App\Form\UserType;
use App\Form\FileType;
use App\Repository\UserRepository;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\DependencyInjection\ParameterBag\ParameterBagInterface;
use Symfony\Component\HttpFoundation\File\Exception\FileException;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\Security\Core\Authentication\Token\UsernamePasswordToken;
use Symfony\Component\Security\Core\Encoder\UserPasswordEncoderInterface;
use Symfony\Component\Security\Core\Exception\CustomUserMessageAuthenticationException;
use Symfony\Component\Security\Http\Authentication\AuthenticationUtils;

class UserController extends AbstractController
{
    /**
     * @Route("/users", name="user_index", methods={"GET"})
     */
    public function index(UserRepository $userRepository): Response
    {
        $users = $userRepository->findAll();

        return $this->render('user/index.html.twig', [
            'users' => $users,
        ]);
    }

 /**
 * @Route("/users/new", name="user_new", methods={"GET","POST"})
 */
public function new(Request $request, ParameterBagInterface $params): Response
{
    $user = new User($params);
    $form = $this->createForm(UserType::class, $user);
    $form->handleRequest($request);

    if ($form->isSubmitted() && !$form->isValid()) {
        $this->addFlash('error', 'There was an error with your form submission.');
    }

    if ($form->isSubmitted() && $form->isValid()) {
        // Vérification de la validité de l'email
        if (!filter_var($user->getEmail(), FILTER_VALIDATE_EMAIL) || !strpos($user->getEmail(), '@')) {
            $this->addFlash('error', 'L\'adresse email n\'est pas valide.');
            return $this->redirectToRoute('user_new');
        }
        // Vérification de la validité du nom d'utilisateur
        if (!preg_match('/^[a-zA-Z]+$/', $user->getUsername())) {
            $this->addFlash('error', 'Le nom d\'utilisateur doit contenir uniquement des lettres.');
            return $this->redirectToRoute('user_new');
        }
        // Vérification de la validité du mot de passe
        if (strlen($user->getPassword()) < 8) {
            $this->addFlash('error', 'Le mot de passe doit contenir au moins 8 caractères.');
            return $this->redirectToRoute('user_new');
        }

        $entityManager = $this->getDoctrine()->getManager();

        // Vérification de l'existence de l'utilisateur
        $existingUser = $entityManager->getRepository(User::class)->findOneBy([
            'email' => $user->getEmail(),
            'username' => $user->getUsername(),
        ]);

        if ($existingUser !== null) {
            $this->addFlash('error', 'Un utilisateur avec cet email ou nom d\'utilisateur existe déjà.');
            return $this->redirectToRoute('user_new');
        }

        // Ajout des attributs role, session et photo à l'utilisateur
        $user->setuserType($form->get('userType')->getData());
        $session = $form->get('session')->getData();
        if (!empty($session)) {
            $user->setSession($session);
        }      $photos = $form->get('photo')->getData();
            
        if (is_array($photos)) {
            $filenames = [];
            foreach ($photos as $photo) {
                $filename = uniqid().'.'.$photo->getClientOriginalExtension();
                $photo->move($this->getParameter('photos_directory'), $filename);
                $filenames[] = $filename;
            }
            $user->setPhoto($filenames);
        } else {
            $user->setPhoto($photos);
        }
        
        $entityManager->persist($user);
        $entityManager->flush();

        $this->addFlash('success', 'L\'utilisateur a été créé avec succès.');

        return $this->redirectToRoute('login');
    }

    return $this->render('user/new.html.twig', [
        'user' => $user,
        'form' => $form->createView(),
    ]);
}



    /**
     * @Route("/users/{id_user}", name="user_show", methods={"GET"})
     */
    public function show(User $user): Response
    {
        return $this->render('user/show.html.twig', [
            'user' => $user,
        ]);
    }

   /**
 * @Route("/users/{id_user}/edit", name="user_edit", methods={"GET","POST"})
 */
public function edit(Request $request, User $user): Response
{
    $form = $this->createForm(UserType::class, $user);
    $form->handleRequest($request);

    if ($form->isSubmitted() && $form->isValid()) {
        // Vérification de la validité de l'email
        if (!filter_var($user->getEmail(), FILTER_VALIDATE_EMAIL) || !strpos($user->getEmail(), '@')) {
            $this->addFlash('error', 'L\'adresse email n\'est pas valide.');
            return $this->redirectToRoute('user_edit', ['id_user' => $user->getIdUser()]);
        }
        // Vérification de la validité du nom
        
        $this->getDoctrine()->getManager()->flush();

        $this->addFlash('success', 'L\'utilisateur a été modifié.');

        return $this->redirectToRoute('user_index');
    }

    return $this->render('user/edit.html.twig', [
        'user' => $user,
        'form' => $form->createView(),
    ]);
}

    /**
 * @Route("/users/{id}/delete", name="user_delete")
 */
public function deleteUser(User $user): Response
{
    $entityManager = $this->getDoctrine()->getManager();
    $entityManager->remove($user);
    $entityManager->flush();

    $this->addFlash('success', 'L\'utilisateur a été supprimé avec succès.');

    return $this->redirectToRoute('user_index');
}
/**
 * @Route("/login", name="login")
 */
public function login(Request $request, UserRepository $userRepository): Response
{
    if ($request->isMethod('POST')) {
        $email = $request->request->get('email');
        $password = $request->request->get('password');
        $userType = $request->request->get('user_type'); // récupérer la valeur de l'attribut user_type

        $user = $userRepository->findOneByEmail($email);

        if (!$user) {
            $this->addFlash('danger', 'Email ou mot de passe incorrect.');
            return $this->redirectToRoute('login');
        }

        if ($user->getPassword() !== $password) {
            $this->addFlash('danger', 'Email ou mot de passe incorrect.');
            return $this->redirectToRoute('login');
        }

        // Stocker le nom d'utilisateur et le rôle de l'utilisateur dans des variables de session
        $session = $request->getSession();
        $session->set('username', $user->getUsername());
        $session->set('role', $userType);

        // Rediriger l'utilisateur en fonction de son rôle
        if ($userType === 'admin') {
            return $this->redirectToRoute('welcome');
        } else {
            return $this->redirectToRoute('user_profile');
        }
    }

    return $this->render('user/log.html.twig');
}


/**
 * @Route("/welcome", name="welcome")
 */
public function welcome(): Response
{
    return $this->render('back/welcome.html.twig');
}
/**
 * @Route("/front", name="front")
 */
public function front(): Response
{
    return $this->render('front/front.html.twig');
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
 * @Route("/testimonial", name="testimonial")
 */
public function testimonial(): Response
{
    return $this->render('front/testimonial.html.twig');
}
/**
 * @Route("/back", name="back")
 */
public function back(): Response
{
    return $this->render('user/welcome.html.twig');
}
/**
 * @Route("/dash", name="dash")
 */
public function index3(): Response
{
    return $this->render('user/index3.html.twig');
}

/**
 * @Route("/logout", name="logout")
 */
public function logout(): Response
{
    // Redirige l'utilisateur vers la page de connexion
    return $this->redirectToRoute('login');
}
/**

*@Route("/profile", name="user_profile")
*/
public function profile(Request $request): Response
{
$session = $request->getSession();
$userType = $session->get('role'); // récupérer la valeur de la variable de session role

return $this->render('user/profile.html.twig', [
'user' => $this->getUser(),
'userType' => $userType, // passer la valeur de la variable de session en tant que variable locale
]);
}
/**
     * @Route("/empty", name="empty")
     */
    public function index2()
    {
        return $this->render('user/empty.html.twig');
    }

}



    