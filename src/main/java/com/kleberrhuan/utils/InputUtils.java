    package com.kleberrhuan.utils;
    
    import com.kleberrhuan.model.product.Product;
    
    import java.io.IOException;
    import java.math.BigDecimal;
    import java.util.Scanner;
    
    public class InputUtils {
        private static final Scanner scanner;
        
        static {
           scanner = new Scanner(System.in);
        }
        
        private InputUtils(){};
        
        public static Product promptForProductData(){
            String name = promptForString("Insira um nome para o produto:");
            int quantity = promptForInt("Insira uma quantidade para o produto:");
            BigDecimal value = promptForBigDecimal("Insira um valor para o produto:");
            return Product.create(name, quantity, value);
        }
    
        public static int promptForInt(String message){
            System.out.print(message + " ");
    
            while (!scanner.hasNextInt()) {
                System.out.println("Por favor, insira um valor válido.");
                scanner.next();
                System.out.print(message + " ");
            }
    
            int number = scanner.nextInt();
            scanner.nextLine();
            return number;
        }
    
        public static long promptForLong(String message) {
            System.out.print(message + " ");
    
            while (!scanner.hasNextLong()){
                System.out.println("Insira um valor numerico correto: (Ex: 10.10)");
                scanner.next();
                System.out.print(message + " ");
            }
    
            long value = scanner.nextLong();
            scanner.nextLine();
            return value;
        }
    
        public static BigDecimal promptForBigDecimal(String message) {
            System.out.print(message + " ");
            while (true) {
                String input = scanner.nextLine();
                try {
                    return new BigDecimal(input);
                } catch (NumberFormatException e) {
                    System.out.println("Por favor, insira um valor numérico válido. " +
                            "Use ponto como separador decimal (Ex: 10.50)");
                    System.out.print(message + " ");
                }
            }
        }
        
        public static String promptForString(String message){
            System.out.print(message + " ");
            
            while (!scanner.hasNext()){
                System.out.println("Insira um valor valido: ");
                scanner.next();
                System.out.print(message + " ");
            }
    
            return scanner.nextLine();
        }
        
        public static void pressAnyKey() throws IOException {
            System.out.print("Press ENTER to continue...");
            System.in.read();
        }
        
        public static void addDelay(int ms){
            try{
                Thread.sleep(ms);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
