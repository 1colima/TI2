package app;

import java.util.List;
import java.util.Scanner;

import dao.DAO;
import dao.UsuarioDAO;
import model.Usuario;

public class Aplicacao {
    
    private static Scanner scanner = new Scanner(System.in);
    private static UsuarioDAO usuarioDAO = new UsuarioDAO();

    public static void main(String[] args) throws Exception {
        int opcao;
        
        do {
            exibirMenu();
            opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer
            
            switch (opcao) {
                case 1:
                    inserirUsuario();
                    break;
                case 2:
                    autenticarUsuario();
                    break;
                case 3:
                    mostrarUsuariosSexoMasculino();
                    break;
                case 4:
                    atualizarSenha();
                    break;
                case 5:
                    invadirComSQLInjection();
                    break;
                case 6:
                    mostrarUsuariosOrdenadosPorCodigo();
                    break;
                case 7:
                    excluirUsuario();
                    break;
                case 8:
                    mostrarUsuariosOrdenadosPorLogin();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida. Por favor, escolha novamente.");
            }
        } while (opcao != 0);
        
        scanner.close();
    }
    
    private static void exibirMenu() {
        System.out.println("\n==== Menu ====");
        System.out.println("1. Inserir usuário");
        System.out.println("2. Autenticar usuário");
        System.out.println("3. Mostrar usuários do sexo masculino");
        System.out.println("4. Atualizar senha");
        System.out.println("5. Invadir usando SQL Injection");
        System.out.println("6. Mostrar usuários ordenados por código");
        System.out.println("7. Excluir usuário");
        System.out.println("8. Mostrar usuários ordenados por login");
        System.out.println("0. Sair");
        System.out.print("Escolha uma opção: ");
    }
    
    private static void inserirUsuario() throws Exception {
        System.out.println("\n==== Inserir usuário ====");
        System.out.print("Informe o código: ");
        int codigo = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer
        System.out.print("Informe o login: ");
        String login = scanner.nextLine();
        System.out.print("Informe a senha: ");
        String senha = scanner.nextLine();
        System.out.print("Informe o sexo (M/F): ");
        char sexo = scanner.nextLine().charAt(0);
        
        Usuario usuario = new Usuario(codigo, login, senha, sexo);
        if (usuarioDAO.insert(usuario)) {
            System.out.println("Inserção com sucesso -> " + usuario.toString());
        }
    }
    
    private static void autenticarUsuario() throws Exception {
        System.out.println("\n==== Testando autenticação ====");
        System.out.print("Informe o login: ");
        String login = scanner.nextLine();
        System.out.print("Informe a senha: ");
        String senha = scanner.nextLine();
        
        System.out.println("Usuário (" + login + "): " + usuarioDAO.autenticar(login, senha));
    }
    
    private static void mostrarUsuariosSexoMasculino() {
        System.out.println("\n==== Mostrar usuários do sexo masculino ====");
        List<Usuario> usuarios = usuarioDAO.getSexoMasculino();
        for (Usuario u : usuarios) {
            System.out.println(u.toString());
        }
    }
    
    private static void atualizarSenha() throws Exception {
        System.out.println("\n==== Atualizar senha ====");
        System.out.print("Informe o código do usuário: ");
        int codigo = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer
        System.out.print("Informe a nova senha: ");
        String novaSenha = scanner.nextLine();
        
        if (usuarioDAO.updateSenha(codigo, novaSenha)) {
            System.out.println("Senha atualizada com sucesso para o usuário com código " + codigo);
        } else {
            System.out.println("Usuário com código " + codigo + " não encontrado.");
        }
    }

    
    private static void invadirComSQLInjection() throws Exception {
        System.out.println("\n==== Invadir usando SQL Injection ====");
        System.out.print("Informe o login: ");
        String login = scanner.nextLine();
        System.out.print("Informe a senha com SQL Injection: ");
        String senha = scanner.nextLine();
        
        System.out.println("Usuário (" + login + "): " + usuarioDAO.autenticar(login, senha));
    }
    
    private static void mostrarUsuariosOrdenadosPorCodigo() {
        System.out.println("\n==== Mostrar usuários ordenados por código ====");
        List<Usuario> usuarios = usuarioDAO.getOrderByCodigo();
        for (Usuario u : usuarios) {
            System.out.println(u.toString());
        }
    }
    
    private static void excluirUsuario() throws Exception {
        System.out.println("\n==== Excluir usuário ====");
        System.out.print("Informe o código do usuário a ser excluído: ");
        int codigo = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer
        
        if (usuarioDAO.delete(codigo)) {
            System.out.println("Usuário com código " + codigo + " excluído com sucesso.");
        } else {
            System.out.println("Usuário com código " + codigo + " não encontrado.");
        }
    }
    
    private static void mostrarUsuariosOrdenadosPorLogin() {
        System.out.println("\n==== Mostrar usuários ordenados por login ====");
        List<Usuario> usuarios = usuarioDAO.getOrderByLogin();
        for (Usuario u : usuarios) {
            System.out.println(u.toString());
        }
    }
}
