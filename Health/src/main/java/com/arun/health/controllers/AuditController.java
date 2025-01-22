package com.arun.health.controllers;

import com.arun.health.entities.PostEntity;
import jakarta.persistence.EntityManagerFactory;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Reader;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/audit")
public class AuditController {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @GetMapping(path = "/posts/{postId}")
    List<PostEntity> getPostRevisions(@PathVariable Long postId, Reader reader) {
        AuditReader auditReader = AuditReaderFactory.get(entityManagerFactory.createEntityManager());
         List<Number> revisions =  auditReader.getRevisions(PostEntity.class, postId);
         return revisions
                 .stream()
                 .map(revisionNumber-> auditReader.find(PostEntity.class, postId, revisionNumber))
                 .collect(Collectors.toList());
    }
}
