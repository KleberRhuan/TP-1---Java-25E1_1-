package com.kleberrhuan.view;

import com.kleberrhuan.controller.InventoryController;
import com.kleberrhuan.model.product.Product;
import com.kleberrhuan.utils.InputUtils;
import java.io.IOException;
import java.util.List;


public class ConsoleInterface {
    private final InventoryController inventoryController;
    static final String operatingSystem = System.getProperty("os.name");
    
    public ConsoleInterface(){
        inventoryController = new InventoryController();
    }

    private static void printHeader() {
        String header ="""
        ╔══════════════════════════════╗
        ║      Gestao de Estoque       ║
        ╚══════════════════════════════╝
        """;
        System.out.println(header);
    }
    
    public void printMenu() throws IOException {
        int option;
        do {
            clearScreen();
            printHeader();
            printMenuOptions();
            option = InputUtils.promptForInt("\nEscolha uma opção: ");
            if(option != 0) handleUserChoice(option);
        } while(option != 0);
    }

    private void printMenuOptions() {
        System.out.println("1. Adicionar Produto");
        System.out.println("2. Remover Produto");
        System.out.println("3. Listar Produtos");
        System.out.println("4. Buscar Produto por ID");
        System.out.println("0. Sair");
    }
    
    private void handleUserChoice(int option) throws IOException {
        clearScreen();
        switch (option) {
            case 1 -> printProductCreation();
            case 2 -> printProductRemoval();
            case 3 -> printProducts();
            case 4 -> printProduct();
            default -> System.out.println("Selecione uma opcao valida.");
        }
    }
    
    private void printProducts() throws IOException {
        List<String> products = inventoryController
                .getProducts()
                .stream()
                .map(Product::toString)
                .toList();
        System.out.println("Lista de Produtos: \n");
        products.forEach(System.out::println);
        InputUtils.pressAnyKey();
    }

    private void printProductCreation(){
        Product product = InputUtils.promptForProductData();
        inventoryController.addProduct(product);
        System.out.println("Criado Produto com sucesso: \n\n" + product);
        InputUtils.addDelay(3000);
    }

    private void printProductRemoval() {
        long option = InputUtils.promptForLong("Indique o Id do produto a ser removido:");
        String message;
        inventoryController.removeProduct(option)
                .ifPresentOrElse(
                        product -> System.out.println("Produto removido com sucesso: " + product),
                        () -> System.out.println("Nao encontrado produto no banco de dados.")
                );
        InputUtils.addDelay(3000);
    }
    
    private void printProduct() throws IOException {
        long id = InputUtils.promptForLong("Insira o id do produto:");
        inventoryController.getProduct(id)
                .ifPresentOrElse(
                        System.out::println,
                        () -> System.out.println("Produto não encontrado!")
                );
        InputUtils.pressAnyKey();
    }

    public static void clearScreen() throws IOException {
        if (operatingSystem .contains("Windows")) {
            Runtime.getRuntime().exec("cls");
        }
        Runtime.getRuntime().exec("clear");
    }

}
