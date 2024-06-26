package com.vuelos_globales.entities.Revisions.adapters.in;

import java.sql.Date;
import java.util.Scanner;

import com.vuelos_globales.entities.Revisions.application.RevisionsService;
import com.vuelos_globales.entities.Revisions.domain.Revisions;

import java.util.Optional;



public class RevisionsConsoleAdapter {
    Scanner scanner = new Scanner(System.in);

    private final RevisionsService revisionService;

    public RevisionsConsoleAdapter(RevisionsService revisionService) {
        this.revisionService = revisionService;
    }

    public void createRevision(){
        System.out.println("[*]  INGRESE EL ID DE LA REVISION A CREAR");
        String idRevision = scanner.nextLine();

        System.out.println("[*]  INGRESE LA FECHA DE LA REVISION (YYYY-MM-DD)");
        String revisionDate = scanner.nextLine();
        Date revDate = Date.valueOf(revisionDate);

        System.out.println("[*]  INGRESE EL ID DEL AVION");
        String idPlane = scanner.nextLine();

        System.out.println("[*]  INGRESE EL ID DE DETALLES");
        String idDetails = scanner.nextLine();

        Revisions newRevision = new Revisions(idRevision, revDate, idPlane, idDetails);
        revisionService.createRevision(newRevision);
    }


    public void searchRevision(){
        System.out.println("[?]  INGRESE EL ID DE LA REVISION A BUSCAR\n\n");
        String findId = scanner.nextLine();

        Optional<Revisions> revision = revisionService.getRevisionById(findId);
        revision.ifPresentOrElse(
            a -> System.out.println("[*]  ID: "+ a.getId()),
            () -> System.out.println("[!]  REVISION NO ENCONTRADA")
        );
        System.out.println("[*]  PRESIONE CUALQUIER TECLA PARA CONTINUAR...");
        scanner.nextLine();
    }


    public void updateRevision(){
        System.out.println("[*]   INGRESE EL ID DE LA REVISION A EDITAR\n\n");
        String findId = scanner.nextLine();

        Optional<Revisions> revision = revisionService.getRevisionById(findId);
        revision.ifPresentOrElse(
            a -> {
                System.out.println("[*]  ID: " + a.getId());
                System.out.println("[*]  Fecha de la revisión actual: " + a.getRevisionDate());
                System.out.println("[*]  INGRESE LA NUEVA FECHA DE LA REVISION (YYYY-MM-DD)");
                String updateRevisionDate = scanner.nextLine();
                Date updatedDate = Date.valueOf(updateRevisionDate); // Esto de aqui ayuda a convertir el String que digitamos en el scanner y lo convierte en tipo Date
                

                System.out.println("[*]  ID del avión actual: " + a.getIdPlane());
                System.out.println("[*]  INGRESE EL NUEVO ID DEL AVION");
                String updateIdPlane = scanner.nextLine();
    
                System.out.println("[*]  ID de detalles actual: " + a.getIdDetails());
                System.out.println("[*]  INGRESE EL NUEVO ID DE DETALLES");
                String updateIdDetails = scanner.nextLine();
    
                Revisions updatedRevision = new Revisions(a.getId(), updatedDate, updateIdPlane, updateIdDetails);
                revisionService.updateRevisions(updatedRevision);

            },
            () -> System.out.println("[!]  REVISION NO ENCONTRADA")
        );
        System.out.println("[*]  PRESIONE CUALQUIER TECLA PARA CONTINUAR....");
        scanner.nextLine();     
    
    }

    public void deleteRevision(){
        System.out.println("[*]  INGRESE EL ID DE LA REVISION A ELIMINAR\n\n");
        String findId = scanner.nextLine();

        Optional<Revisions> revision = revisionService.getRevisionById(findId);
        revision.ifPresentOrElse(
            a -> {
                revisionService.deleteRevision(findId);
            },
            () -> System.out.println("[!]  REVISION NO ENCONTRADA")
        );
        System.out.println("[*]  PRESIONE CUALQUIER TECLA PARA CONTINUAR...");
        scanner.nextLine();
    }

    public void getAllRevisions(){
        revisionService.getAllRevisions().forEach(a -> {
            System.out.println("[*]  ID: "+ a.getId() + "FECHA DE REVISION: " + a.getRevisionDate() + "ID DE AVION: " + a.getIdPlane() + "DETALLES DE ID: " + a.getIdDetails());
        });
        System.out.println("[*]  PRESIONE CUALQUIER TECLA PARA CONTINUAR...");
        scanner.nextLine();
    }

}
