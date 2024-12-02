package com.message.myMiddlewareMQ.repository;

import com.message.myMiddlewareMQ.model.PartnerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PartnerRepository extends JpaRepository<PartnerEntity, Long> {}

