package com.incedo.notes.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.incedo.notes.models.Notes;

public interface NotesRepository extends JpaRepository<Notes, Long>{

}
