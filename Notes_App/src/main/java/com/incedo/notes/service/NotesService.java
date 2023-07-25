package com.incedo.notes.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.incedo.notes.models.Notes;
import com.incedo.notes.persistence.NotesRepository;

@Service
public class NotesService {

	@Autowired
	private NotesRepository notesRepository;

	public Notes insert(Notes note) {
		note.setDate(LocalDate.now());
		System.out.println(note.getDate());
		return notesRepository.save(note);
	}

	public List<Notes> getAll() {
		return notesRepository.findAll();
	}

	public Notes getNoteById(Long id) {
		Optional<Notes> noteFound = notesRepository.findById(id);
		if(noteFound.isEmpty()) {
			return null;
		}
		
		return noteFound.get();
	}

	public void delete(Notes note) {
		notesRepository.delete(note);
	}
}
