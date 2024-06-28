package com.vuelos_globales.entities.DocumentType.adapters.in;

import java.text.MessageFormat;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import com.vuelos_globales.entities.DocumentType.application.DocumentTypeService;
import com.vuelos_globales.entities.DocumentType.domain.DocumentType;
import com.vuelos_globales.modules.ConsoleUtils;

public class DocumentTypeConsoleAdapter {
    Scanner sc = new Scanner(System.in);

    private final DocumentTypeService docTypeService;

    public DocumentTypeConsoleAdapter(DocumentTypeService docTypeService) {
        this.docTypeService = docTypeService;
    }

    public void createDocType() {
        String rta = "S";

        while (rta.equalsIgnoreCase("S")) {
            ConsoleUtils.limpiarConsola();
            try {
                System.out.println("*************** REGISTRAR TIPO DE DOCUMENTO ***************");
                System.out.println("[*] INGRESE EL ID DEL TIPO DE DOCUMENTO A CREAR: ");
                int newIdDoc = Integer.parseInt(sc.nextLine().trim());

                Optional<DocumentType> docType = docTypeService.getDocumentTypeById(newIdDoc);
                docType.ifPresentOrElse(
                    d -> {
                        System.out.println(MessageFormat.format("[!] EL ID (0) YA ESTA OCUPADO.", d.getId()));
                    },
                    () -> {
                        ConsoleUtils.limpiarConsola();
                        System.out.println("*************** REGISTRAR TIPO DE DOCUMENTO ***************");
                
                        System.out.println("[*] INGRESE EL NOMBRE DEL TIPO DE DOCUMENTO: ");
                        String docTypeName = sc.nextLine();
                
                        DocumentType newDocumentType = new DocumentType(newIdDoc, docTypeName);
                        docTypeService.createDocumentType(newDocumentType);
                    });

                    System.out.println("[?] DESEA AÑADIR OTRO TIPO DE DOCUMENTO? [S] - SI | [INGRESE CUALQUIER TECLA] - NO");
                    rta = sc.nextLine();
            } catch (NumberFormatException e) {
                ConsoleUtils.limpiarConsola();
                System.out.println("[!] Ingresaste una opción inválida.");
                sc.nextLine();
            }
        }
    }

    public void searchDocumentType() {
        List<DocumentType> documentTypes = docTypeService.getAllDocumentTypes();

        if (documentTypes.isEmpty()) {
            ConsoleUtils.limpiarConsola();
            System.out.println("[!] NO HAY NINGUN TIPO DE DOCUMENTO REGISTRADO");
            sc.nextLine();
        } else {
            try {
                ConsoleUtils.limpiarConsola();
                System.out.println("*************** BUSCAR TIPO DE DOCUMENTO ***************");
                getAllDocumentTypes();
                System.out.println("\n[*] INGRESE EL ID DEL TIPO DE DOCUMENTO A BUSCAR: ");
                int id = Integer.parseInt(sc.nextLine().trim());

                Optional<DocumentType> documentType = docTypeService.getDocumentTypeById(id);
                documentType.ifPresentOrElse(
                    d -> {
                        ConsoleUtils.limpiarConsola();
                        System.out.println("*************** TIPO DE DOCUMENTO ***************");
                        System.out.println(MessageFormat.format("[*] ID : {0}\n[*] TIPO DE DOCUMENTO :", d.getId(), d.getDocumentType()));
                        sc.nextLine();
                    },
                    () -> {
                        ConsoleUtils.limpiarConsola();
                        System.out.println("[!]  TIPO DE DOCUMENTO NO ENCONTRADO");
                        sc.nextLine();
                    });
                    ConsoleUtils.limpiarConsola();
                    System.out.println("[*]  PRESIONE CUALQUIER TECLA PARA CONTINUAR...");
                    sc.nextLine();
            } catch (NumberFormatException e) {
                ConsoleUtils.limpiarConsola();
                System.out.println("[!] INGRESASTE UNA OPCION INVALIDA.");
                sc.nextLine();
            }
            
        }
    }

    public void updateDocumentType() {
        List<DocumentType> documentTypes = docTypeService.getAllDocumentTypes();

        if (documentTypes.isEmpty()) {
            ConsoleUtils.limpiarConsola();
            System.out.println("[!] NO HAY NINGUN TIPO DE DATO REGISTRADO");
            sc.nextLine();
        } else {
            try {
                ConsoleUtils.limpiarConsola();
                getAllDocumentTypes();
                System.out.println("[?] INGRESE EL ID DEL TIPO DE DATO A BUSCAR: ");
                int id = Integer.parseInt(sc.nextLine().trim());

                Optional<DocumentType> documentType = docTypeService.getDocumentTypeById(id);
                documentType.ifPresentOrElse(
                        d -> {
                            ConsoleUtils.limpiarConsola();
                            System.out.println("*************** ACTUALIZAR TIPO DE DATO ***************");
                            System.out.println(MessageFormat.format("[*] ID : {0}\n[*] TIPO DE DOCUMENTO :", d.getId(),d.getDocumentType()));

                            ConsoleUtils.limpiarConsola();
                            System.out.println("*************** REGISTRAR TIPO DE DOCUMENTO ***************");

                            System.out.println("[*] INGRESE EL NOMBRE DEL TIPO DE DOCUMENTO: ");
                            String docTypeName = sc.nextLine();

                            DocumentType newDocumentType = new DocumentType(id, docTypeName);
                            docTypeService.updateDocumentType(newDocumentType);

                            ConsoleUtils.limpiarConsola();
                            System.out.println("[*] TIPO DE DOCUMENTO ACTUALIZADO CORRECTAMENTE.");
                            sc.nextLine();
                        },
                        () -> {
                            ConsoleUtils.limpiarConsola();
                            System.out.println("[!] TIPO DE DOCUMENTO NO ENCONTRADO");
                            sc.nextLine();
                        });
                System.out.println("[*]  PRESIONE CUALQUIER TECLA PARA CONTINUAR...");
                sc.nextLine();
            } catch (NumberFormatException e) {
                ConsoleUtils.limpiarConsola();
                System.out.println("[!] INGRESASTE UNA OPCION INVALIDA.");
                sc.nextLine();
            }
        }
    }

    public void deleteDocumentType() {
        List<DocumentType> documentTypes = docTypeService.getAllDocumentTypes();
        
        if (documentTypes.isEmpty()) {
            ConsoleUtils.limpiarConsola();
            System.out.println("[!] NO HAY NINGUN TIPO DE DOCUMENTO REGISTRADO");
            sc.nextLine();
        } else {
            getAllDocumentTypes();
            try {
                ConsoleUtils.limpiarConsola();
                System.out.println("\n[?] INGRESE EL ID DEL TIPO DE DOCUMENTO A ELIMINAR: ");
                int id = Integer.parseInt(sc.nextLine().trim());
    
                Optional<DocumentType> documentType = docTypeService.getDocumentTypeById(id);
                documentType.ifPresentOrElse(
                    a -> {
                        docTypeService.deleteDocumentType(id);
                        System.out.println("[!] TIPO DE DOCUMENTO ELIMINADO CORRECTAMENTE.");
                        sc.nextLine();
                    },
                    () -> {
                        System.out.println("[!]  TIPO DE DOCUMENTO NO ENCONTRADO");
                    }
                );
                System.out.println("[*]  PRESIONE CUALQUIER TECLA PARA CONTINUAR...");
                sc.nextLine();
            } catch (NumberFormatException e) {
                ConsoleUtils.limpiarConsola();
                System.out.println("[!] INGRESASTE UNA OPCION INVALIDA.");
                sc.nextLine();
            }
        }
    }

    public void getAllDocumentTypes() {
        List<DocumentType> documentTypes = docTypeService.getAllDocumentTypes();
        
        if (documentTypes.isEmpty()) {
            ConsoleUtils.limpiarConsola();
            System.out.println("[!] NO HAY NINGUN TIPO DE DOCUMENTO REGISTRADO");
            sc.nextLine();
        } else {
            ConsoleUtils.limpiarConsola();
            docTypeService.getAllDocumentTypes().forEach(d -> {
               System.out.println(MessageFormat.format("[*] ID : {0}\n[*] TIPO DE DOCUMENTO :", d.getId(),d.getDocumentType())); 
            });
            System.out.println("[*]  PRESIONE CUALQUIER TECLA PARA CONTINUAR...");
            sc.nextLine();
        }
    }
}
